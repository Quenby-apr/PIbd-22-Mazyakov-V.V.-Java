import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.KeyException;
import java.security.Provider;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;


import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameDocks {
    Logger logger;
    private JFrame frame;
    private Queue<MilShip> shipQueue;
    private DocksCollection docksCollection;
    private JButton CreateShip_button = new JButton("Причалить корабль");
    private JButton sortShip_button = new JButton("Сортировать");
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

    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("Файл");
    JMenuItem saveData = new JMenuItem("Сохранить");
    JMenuItem loadData = new JMenuItem("Загрузить");
    JMenu docksMenu = new JMenu("Отдельный док");
    JMenuItem saveDocks = new JMenuItem("Сохранить док");
    JMenuItem loadDocks = new JMenuItem("Загрузить док");

    public FrameDocks() {
        logger = Logger.getLogger(String.valueOf(FrameDocks.class));
        PropertyConfigurator.configure("log4j2.properties");
        shipQueue = new LinkedList<>();
        docksCollection = new DocksCollection(900, 620);
        paintDocks = new PaintDocks(docksCollection);
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(0, 0, 1100, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        CreateShip_button.setBounds(870, 30 + 300, 210, 20);
        sortShip_button.setBounds(870, 30 + 330, 210, 20);
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


        frame.getContentPane().add(CreateShip_button);
        frame.getContentPane().add(sortShip_button);
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

        fileMenu.add(saveData);
        fileMenu.add(loadData);
        docksMenu.add(saveDocks);
        docksMenu.add(loadDocks);
        menuBar.add(fileMenu);
        menuBar.add(docksMenu);
        frame.setJMenuBar(menuBar);
    }

    public void buttonsDocks() throws IOException {
        CreateShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (listBoxDocks.getSelectedIndex() >= 0) {
                    try {
                        FrameShipConfig shipConfig = new FrameShipConfig(frame);
                        MilShip ship = shipConfig.getShip();
                        if (ship == null) {
                            return;
                        }
                        if (docksCollection.get(listBoxDocks.getSelectedValue()).add(shipConfig.getShip())) {
                            logger.info("В док " + listBoxDocks.getSelectedValue() + " был добавлен корабль " + ship);
                            frame.repaint();
                        }
                    } catch (DocksOverflowException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Док переполнен", JOptionPane.ERROR_MESSAGE);
                        logger.warn(ex.getMessage());
                    }catch (DocksAlreadyHaveException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Дублирование", JOptionPane.ERROR_MESSAGE);
                        logger.warn(ex.getMessage());
                    }
                    catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
            }
        });

        sortShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                    if (listBoxDocks.getSelectedValue() == null) {
                        JOptionPane.showMessageDialog(frame, "Док не выбран", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    }
                    docksCollection.get(listBoxDocks.getSelectedValue()).sort();
                    frame.repaint();
                    logger.info("Корабли в доке " + listBoxDocks.getSelectedValue() + " отсортированы");
            }
        });

        moveToQueue_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fieldIndex.getText().equals("")) {
                    try {
                        MilShip ship = docksCollection.get(listBoxDocks.getSelectedValue()).delete(Integer.parseInt(fieldIndex.getText()));
                        if (ship != null) {
                            shipQueue.add(ship);
                            logger.info("Из дока " + listBoxDocks.getSelectedValue() + " корабль " + ship + " помещен в очередь");
                            frame.repaint();
                        }
                    } catch (DocksNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Док не найден", JOptionPane.ERROR_MESSAGE);
                        logger.warn(ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
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
                    MilShip ship = shipQueue.poll();
                    frameCruiser.setShip(ship);
                    logger.info("Корабль " + ship + " был изъят из очереди");
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

        saveData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileSaveDialog = new JFileChooser();
                fileSaveDialog.setFileFilter(new FileNameExtensionFilter(null, "txt"));
                int result = fileSaveDialog.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (docksCollection.saveData(fileSaveDialog.getSelectedFile().getPath())) {
                            JOptionPane.showMessageDialog(frame, "Файл сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                            logger.info("Данные сохранены в файл " + fileSaveDialog.getSelectedFile().getPath());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
            }
        });

        loadData.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpenDialog = new JFileChooser();
                fileOpenDialog.setFileFilter(new FileNameExtensionFilter(null, "txt"));
                int result = fileOpenDialog.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (docksCollection.loadData(fileOpenDialog.getSelectedFile().getPath())) {
                            JOptionPane.showMessageDialog(frame, "Файл загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                            reloadLevels();
                            frame.repaint();
                        }
                    } catch (DocksOverflowException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Док переполнен", JOptionPane.ERROR_MESSAGE);
                        logger.warn(ex.getMessage());
                    } catch (FileNotFoundException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Файл не найден", JOptionPane.ERROR_MESSAGE);
                        logger.error(ex.getMessage());
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                        logger.error(ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }
                }
            }
        });

        saveDocks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileSaveDialog = new JFileChooser();
                fileSaveDialog.setFileFilter(new FileNameExtensionFilter(null, "txt"));
                if (listBoxDocks.getSelectedValue() == null) {
                    return;
                }
                int result = fileSaveDialog.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (docksCollection.saveDocks(fileSaveDialog.getSelectedFile().getPath(), listBoxDocks.getSelectedValue())) {
                            JOptionPane.showMessageDialog(frame, "Док сохранился", "Результат", JOptionPane.INFORMATION_MESSAGE);
                            logger.info("Док " + listBoxDocks.getSelectedValue() + " был записан в файл " + fileSaveDialog.getSelectedFile().getPath());
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка при сохранении", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }

                }
            }
        });

        loadDocks.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileOpenDialog = new JFileChooser();
                fileOpenDialog.setFileFilter(new FileNameExtensionFilter(null, "txt"));
                int result = fileOpenDialog.showSaveDialog(frame);
                if (result == JFileChooser.APPROVE_OPTION) {
                    try {
                        if (docksCollection.loadDocks(fileOpenDialog.getSelectedFile().getPath())) {
                            JOptionPane.showMessageDialog(frame, "Док загружен", "Результат", JOptionPane.INFORMATION_MESSAGE);
                            logger.info("Из файла " + fileOpenDialog.getSelectedFile().getPath() + " был выгружен отдельный док");
                            reloadLevels();
                            frame.repaint();
                        }
                    } catch (IllegalArgumentException ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Некорректные данные", JOptionPane.ERROR_MESSAGE);
                        logger.error(ex.getMessage());
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(frame, ex.getMessage(), "Неизвестная ошибка", JOptionPane.ERROR_MESSAGE);
                        logger.fatal(ex.getMessage());
                    }

                }
            }
        });


        listBoxDocks.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent listSelectionEvent) {
                paintDocks.setDefinedDocks(listBoxDocks.getSelectedValue());
                logger.info("Док " + listBoxDocks.getSelectedValue() + " выбран как текущий");
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


