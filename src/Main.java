import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.*;
import java.io.IOException;

import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

public class Main {
    public Buttons bt = new Buttons();
    public JPanel panel = new Otrisovka();
    public static JFrame frame;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Main window = new Main();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public Main() throws IOException {
        initialize();
        bt.buttons();
    }

    public void initialize() {
        frame = new JFrame();
        frame.setBounds(0, 0, 1010, 620);
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.getContentPane().setLayout(null);

        panel.setBorder(new BevelBorder(BevelBorder.LOWERED,
                null, null, null, null));
        panel.setBounds(0, 0, 1500, 1400);
        frame.getContentPane().add(panel);

        bt.Create_button.setBounds(10, 10, 100, 20);
        bt.buttonUp.setBounds(920, 500, 30, 30);
        bt.buttonLeft.setBounds(880, 550, 30, 30);
        bt.buttonDown.setBounds(920, 550, 30, 30);
        bt.buttonRight.setBounds(960, 550, 30, 30);

        frame.getContentPane().add(bt.Create_button);
        frame.getContentPane().add(bt.buttonUp);
        frame.getContentPane().add(bt.buttonLeft);
        frame.getContentPane().add(bt.buttonDown);
        frame.getContentPane().add(bt.buttonRight);
        //comboBox
        bt.countCannonBox.setBounds(900, 20, 90, 20);
        frame.add(bt.countCannonBox);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}