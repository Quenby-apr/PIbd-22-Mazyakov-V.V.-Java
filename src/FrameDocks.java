import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameDocks {
    private JFrame frame;
    private Queue<MilShip> shipQueue;
    private DocksCollection docksCollection;
    private JButton CreateMilShip_button = new JButton("Причалить военный корабль");
    private JButton CreateCruiser_button = new JButton("Причалить крейсер");
    private JButton moveToQueue_button = new JButton("Поместить в очередь");
    private JButton takeFromQueue_button = new JButton("Взять из очереди");
    private JTextField fieldIndex = new JTextField("");
    private JLabel labelPlace = new JLabel("место");
    PaintDocks paintDocks;

    private JLabel labelDocks = new JLabel("Имя дока");
    private DefaultListModel<String> docksList = new DefaultListModel<>();
    private JList<String> listBoxDocks = new JList<>(docksList);
    private JTextField fieldDocks = new JTextField();
    private JButton addDock_button = new JButton("Добавить док");
    private JButton removeDock_button = new JButton("Удалить док");

    public FrameDocks() {
        shipQueue = new LinkedList<>();

        docksCollection = new DocksCollection(900, 620);
        paintDocks = new PaintDocks(docksCollection);
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(0, 0, 1100, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        CreateMilShip_button.setBounds(870, 30 + 300, 210, 20);
        CreateCruiser_button.setBounds(870, 90 + 300, 210, 20);
        moveToQueue_button.setBounds(870, 200 + 300, 210, 20);
        takeFromQueue_button.setBounds(870, 230 + 300, 210, 20);
        paintDocks.setBounds(0, 0, 860, 620);
        fieldIndex.setBounds(910, 160 + 300, 130, 20);
        labelPlace.setBounds(910, 130 + 300, 130, 20);
        labelDocks.setBounds(870, 30, 210, 20);
        fieldDocks.setBounds(870, 60, 210, 20);
        addDock_button.setBounds(870, 100, 210, 20);
        listBoxDocks.setBounds(870, 130, 210, 150);
        removeDock_button.setBounds(870, 290, 210, 20);


        frame.getContentPane().add(CreateMilShip_button);
        frame.getContentPane().add(CreateCruiser_button);
        frame.getContentPane().add(moveToQueue_button);
        frame.getContentPane().add(takeFromQueue_button);
        frame.getContentPane().add(paintDocks);
        frame.getContentPane().add(fieldIndex);
        frame.getContentPane().add(labelPlace);
        frame.getContentPane().add(labelDocks);
        frame.getContentPane().add(fieldDocks);
        frame.getContentPane().add(addDock_button);
        frame.getContentPane().add(listBoxDocks);
        frame.getContentPane().add(removeDock_button);
    }

    public void buttonsDocks() throws IOException {
        CreateMilShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listBoxDocks.getSelectedIndex() >= 0) {
                    JColorChooser colorDialog = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, colorDialog);
                    if (colorDialog.getColor() != null) {
                        MilShip ship = new MilShip(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), colorDialog.getColor());
                        if (docksCollection.get(listBoxDocks.getSelectedValue()).add(ship)) {
                            System.out.println("added");
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Доки переполнены");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Док не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        CreateCruiser_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listBoxDocks.getSelectedIndex() >= 0) {
                    JColorChooser colorDialog = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, colorDialog);
                    if (colorDialog.getColor() != null) {
                        JColorChooser otherColorDialog = new JColorChooser();
                        JOptionPane.showMessageDialog(frame, otherColorDialog);
                        if (otherColorDialog.getColor() != null) {
                            Cruiser ship = new Cruiser(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), colorDialog.getColor(), otherColorDialog.getColor(),
                                    true, true, true, 4, 2);
                            if (docksCollection.get(listBoxDocks.getSelectedValue()).add(ship)) {
                                frame.repaint();
                            } else {
                                JOptionPane.showMessageDialog(frame, "Доки переполнены");
                            }
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Док не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        moveToQueue_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fieldIndex.getText().equals("")) {
                    try {
                        MilShip ship = docksCollection.get(listBoxDocks.getSelectedValue()).delete(Integer.parseInt(fieldIndex.getText()));
                        if (ship != null) {
                            shipQueue.add(ship);
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Корабля на таком месте нет");

                        }
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(frame, "Корабля с таким местом нет");
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Док не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        takeFromQueue_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!shipQueue.isEmpty()) {
                    FrameCruiser frameCruiser = null;
                    try {
                        frameCruiser = new FrameCruiser();
                    } catch (IOException ex) {
                        ex.printStackTrace();
                    }
                    assert frameCruiser != null;
                    frameCruiser.setShip(Objects.requireNonNull(shipQueue.poll()));
                    frame.repaint();
                }
            }
        });

        addDock_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fieldDocks.getText().equals("")) {
                    docksCollection.addDocks(fieldDocks.getText());
                    reloadLevels();
                    frame.repaint();
                } else {
                    JOptionPane.showMessageDialog(frame, "Введите название дока", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        removeDock_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listBoxDocks.getSelectedIndex() >= 0) {
                    int result = JOptionPane.showConfirmDialog(frame, "Удалить док " + listBoxDocks.getSelectedValue() + "?", "Удаление",
                            JOptionPane.YES_NO_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        docksCollection.deleteDocks(listBoxDocks.getSelectedValue());
                        reloadLevels();
                        frame.repaint();
                    }
                } else {
                    JOptionPane.showMessageDialog(frame, "Док не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        listBoxDocks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                paintDocks.setDefinedDocks(listBoxDocks.getSelectedValue());
                frame.repaint();
            }
        });
    }

    private void reloadLevels() {
        int index = listBoxDocks.getSelectedIndex();

        docksList.removeAllElements();
        int i = 0;
        for (String name : docksCollection.keySet()) {
            docksList.add(i, name);
            i++;
        }

        int itemsCount = docksList.size();
        if (itemsCount > 0 && (index < 0 || index >= itemsCount)) {
            listBoxDocks.setSelectedIndex(0);
        } else if (index >= 0 && index < itemsCount) {
            listBoxDocks.setSelectedIndex(index);
        }
    }
}


