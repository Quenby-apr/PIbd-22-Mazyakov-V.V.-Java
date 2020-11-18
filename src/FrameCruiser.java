import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class FrameCruiser {
    private JFrame frame;
    public ITransport ship;
    PaintShip paintShip = new PaintShip();
    int countCannon = 2;
    int typeCannon = 1;
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

    public FrameCruiser() throws IOException {
        frame = new JFrame();
        frame.setBounds(0, 0, 1270, 680);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        buttonUp.setBounds(920, 500, 30, 30);
        buttonLeft.setBounds(880, 550, 30, 30);
        buttonDown.setBounds(920, 550, 30, 30);
        buttonRight.setBounds(960, 550, 30, 30);
        paintShip.setBounds(0, 0, 1270, 680);

        frame.getContentPane().add(buttonUp);
        frame.getContentPane().add(buttonLeft);
        frame.getContentPane().add(buttonDown);
        frame.getContentPane().add(buttonRight);
        frame.getContentPane().add(paintShip);

        //comboBox
        countCannonBox.setBounds(900, 20, 90, 20);
        frame.add(countCannonBox);
        frame.setLayout(null);
        frame.setVisible(true);

        typeCannonBox.setBounds(900, 50, 90, 20);
        frame.add(typeCannonBox);
        buttonsCruiser();
    }

    public void buttonsCruiser() throws IOException {
        buttonUp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Up);
                frame.repaint();
            }
        });
        buttonLeft.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Left);
                frame.repaint();
            }
        });
        buttonDown.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Down);
                frame.repaint();
            }
        });
        buttonRight.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ship.moveTransport(Direction.Right);
                frame.repaint();
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

    private void setMilShip() {
        paintShip.setShip((new MilShip(150, 1500, Color.WHITE)));
        paintShip.getMilShip().setPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 100), 1010, 620);
        frame.repaint();
    }

    private void setCruiser() {
        paintShip.setShip(new Cruiser(150, 1500, Color.WHITE, Color.RED, true, true, true, 4, 2));
        paintShip.getShip().setPosition((int) (Math.random() * 100 + 10), (int) (Math.random() * 100 + 100), 1010, 620);
        frame.repaint();
    }

    public void setShip(ITransport ship) {
        this.ship = ship;
        this.ship.setPosition((int) (150 + Math.random() * 90), (int) (200 + Math.random() * 100), frame.getWidth(), frame.getHeight() - 140);
        paintShip.setShip(this.ship);
        frame.repaint();
    }
}
