import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class ButtonsDocks {
    private JFrame frame;
    JButton CreateMilShip_button = new JButton("Причалить военный корабль");
    JButton CreateCruiser_button = new JButton("Причалить крейсер");
    JButton TakeShip_button = new JButton("Забрать корабль");
    JTextField fieldIndex = new JTextField("");
    JLabel labelPlace = new JLabel("место");
    public JPanel panel = new JPanel();
    private Docks<ITransport, IAddition> docks;

    public ButtonsDocks() {
        docks = new Docks(900, 620);
        PaintDocks paintDocks = new PaintDocks(docks);
        frame = new JFrame();
        frame.setVisible(true);
        frame.setBounds(0, 0, 1100, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        CreateMilShip_button.setBounds(870, 30, 210, 20);
        CreateCruiser_button.setBounds(870, 90, 210, 20);
        TakeShip_button.setBounds(870, 200, 210, 20);
        paintDocks.setBounds(0, 0, 860, 620);
        fieldIndex.setBounds(910, 160, 130, 20);
        labelPlace.setBounds(910, 130, 130, 20);

        frame.getContentPane().add(CreateMilShip_button);
        frame.getContentPane().add(CreateCruiser_button);
        frame.getContentPane().add(TakeShip_button);
        frame.getContentPane().add(paintDocks);
        frame.getContentPane().add(fieldIndex);
        frame.getContentPane().add(labelPlace);
    }

    public void buttonsDocks() throws IOException {
        CreateMilShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, colorDialog);
                if (colorDialog.getColor() != null) {
                    MilShip ship = new MilShip(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), colorDialog.getColor());
                    if (docks.add(ship)) {
                        frame.repaint();
                    } else {
                        JOptionPane.showMessageDialog(frame, "Доки переполнены");
                    }
                }
            }
        });

        CreateCruiser_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JColorChooser colorDialog = new JColorChooser();
                JOptionPane.showMessageDialog(frame, colorDialog);
                if (colorDialog.getColor() != null) {
                    JColorChooser otherColorDialog = new JColorChooser();
                    JOptionPane.showMessageDialog(frame, otherColorDialog);
                    if (otherColorDialog.getColor() != null) {
                        Cruiser ship = new Cruiser(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), colorDialog.getColor(), otherColorDialog.getColor(),
                                true, true, true, 4, 2);
                        if (docks.add(ship)) {
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Доки переполнены");
                        }
                    }
                }
            }
        });

        TakeShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!fieldIndex.getText().equals("")) {
                    try {
                        ITransport ship = docks.delete(Integer.parseInt(fieldIndex.getText()));
                        if (ship != null) {
                            ButtonsCruiser windowCruiser = new ButtonsCruiser();
                            windowCruiser.setShip(ship);
                            frame.repaint();
                        } else {
                            JOptionPane.showMessageDialog(frame, "Корабля на таком месте нет");

                        }
                    } catch (NumberFormatException | IOException ex) {
                        JOptionPane.showMessageDialog(frame, "Корабля с таким местом нет");
                    }
                }
            }
        });
    }
}

