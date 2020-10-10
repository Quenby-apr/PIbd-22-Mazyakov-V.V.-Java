import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Buttons {
    public ITransport ship;
    int countCannon = 2;
    int typeCannon = 1;
    JButton CreateMilShip_button = new JButton("Создать военный корабль");
    JButton CreateCruiser_button = new JButton("Создать крейсер");
    BufferedImage buttonIconup = ImageIO.read(new File("up.png"));
    BufferedImage buttonIconleft = ImageIO.read(new File("left.png"));
    BufferedImage buttonIcondown = ImageIO.read(new File("down.png"));
    BufferedImage buttonIconright = ImageIO.read(new File("right.png"));
    JButton buttonUp = new JButton(new ImageIcon(buttonIconup));
    JButton buttonLeft = new JButton(new ImageIcon(buttonIconleft));
    JButton buttonDown = new JButton(new ImageIcon(buttonIcondown));
    JButton buttonRight = new JButton(new ImageIcon(buttonIconright));
    String[] comboBoxItemsCount = {"Two", "Four", "Six"};
    String[] comboBoxItemsType = {"First", "Second", "Third"};
    JComboBox<String> countCannonBox = new JComboBox(comboBoxItemsCount);
    JComboBox<String> typeCannonBox = new JComboBox(comboBoxItemsType);
    Otrisovka otr = new Otrisovka();

    public Buttons() throws IOException {
    }

    public void buttons() throws IOException {
        CreateMilShip_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship = new MilShip(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), Color.GRAY);
                ship.setPosition(150 + (int) (Math.random() * 50), 150 + (int) (Math.random() * 50));
                otr.setMilShip((MilShip) ship);
                otr.setShip(null);
                Main.frame.repaint();
            }
        });

        CreateCruiser_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship = new Cruiser(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), Color.GRAY,
                        Color.RED, true, true, true, countCannon, typeCannon);
                ship.setPosition(150 + (int) (Math.random() * 50), 150 + (int) (Math.random() * 50));
                otr.setShip((Cruiser) ship);
                otr.setMilShip(null);
                Main.frame.repaint();
            }
        });

        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Up);
                Main.frame.repaint();
            }
        });
        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Left);
                Main.frame.repaint();
            }
        });
        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Down);
                Main.frame.repaint();
            }
        });
        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Right);
                Main.frame.repaint();
            }
        });
        countCannonBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (countCannonBox.getSelectedItem().equals("Two")) {
                    countCannon = 2;
                } else if (countCannonBox.getSelectedItem().equals("Four")) {
                    countCannon = 4;
                } else if (countCannonBox.getSelectedItem().equals("Six")) {
                    countCannon = 6;
                }
            }
        });
        typeCannonBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (typeCannonBox.getSelectedItem().equals("First")) {
                    typeCannon = 1;
                }
                if (typeCannonBox.getSelectedItem().equals("Second")) {
                    typeCannon = 2;
                }
                if (typeCannonBox.getSelectedItem().equals("Third")) {
                    typeCannon = 3;
                }
            }
        });
    }
}
