import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Buttons {
    public Cruiser ship;
    int countCannon = 2;
    JButton Create_button = new JButton("Создать");
    BufferedImage buttonIconup = ImageIO.read(new File("up.png"));
    BufferedImage buttonIconleft = ImageIO.read(new File("left.png"));
    BufferedImage buttonIcondown = ImageIO.read(new File("down.png"));
    BufferedImage buttonIconright = ImageIO.read(new File("right.png"));
    JButton buttonUp = new JButton(new ImageIcon(buttonIconup));
    JButton buttonLeft = new JButton(new ImageIcon(buttonIconleft));
    JButton buttonDown = new JButton(new ImageIcon(buttonIcondown));
    JButton buttonRight = new JButton(new ImageIcon(buttonIconright));
    String[] comboBoxItems = {"Two", "Four", "Six"};
    JComboBox<String> countCannonBox = new JComboBox(comboBoxItems);
    Otrisovka otr = new Otrisovka();

    public Buttons() throws IOException {
    }

    public void buttons() throws IOException {
        Create_button.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship = new Cruiser(100 + (int) (Math.random() * 200), 500 + (int) (Math.random() * 300), Color.GRAY,
                        Color.RED, true, true, true, countCannon);
                ship.setPosition(150 + (int) (Math.random() * 50), 150 + (int) (Math.random() * 50));
                otr.setShip(ship);
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
    }
}
