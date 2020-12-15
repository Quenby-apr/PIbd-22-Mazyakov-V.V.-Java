import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class FrameShipConfig extends JDialog {
    //панелька для отрисовки корабля
    PaintShip paintShip;

    private MilShip ship;
    private Color shipColor;
    private IAddition additions;
    private int numberAdditions;

    public FrameShipConfig(JFrame frame) {
        super(frame, true);
        setSize(1150, 620);
        setLayout(null);
        //тип корабля
        JLabel labelType = new JLabel("Тип корабля");
        labelType.setBounds(50, 10, 100, 20);

        JLabel labelMilShip = new JLabel("Военный корабль");
        labelMilShip.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMilShip.setBounds(10, 50, 190, 55);
        labelMilShip.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelCruiser = new JLabel("Крейсер");
        labelCruiser.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelCruiser.setBounds(10, 150, 190, 55);
        labelCruiser.setHorizontalAlignment(SwingConstants.CENTER);

        //параметры
        JLabel labelTools = new JLabel("Параметры");
        labelTools.setBounds(40, 400, 100, 20);
        JLabel labelMaxSpeed = new JLabel("Макс. скорость");
        labelMaxSpeed.setBounds(60, 440, 100, 20);
        JSpinner spinnerMaxSpeed = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        spinnerMaxSpeed.setBounds(100, 460, 80, 20);
        JLabel labelWeight = new JLabel("Вес корабля");
        labelWeight.setBounds(60, 490, 100, 20);
        JSpinner spinnerWeight = new JSpinner(new SpinnerNumberModel(100, 1, 1000, 1));
        spinnerWeight.setBounds(100, 520, 80, 20);
        JCheckBox checkBoxMissileSystem = new JCheckBox("Ракетная установка");
        checkBoxMissileSystem.setBounds(250, 450, 200, 30);
        JCheckBox checkBoxAntiaircraftComplex = new JCheckBox("ПВО");
        checkBoxAntiaircraftComplex.setBounds(250, 490, 200, 30);
        JCheckBox checkBoxControlSystem = new JCheckBox("Управление огнём");
        checkBoxControlSystem.setBounds(250, 530, 200, 30);

        //число орудий
        JLabel labelCountCannon = new JLabel("число орудий");
        labelCountCannon.setBounds(900, 300, 100, 20);
        JSpinner spinnerCountCannon = new JSpinner(new SpinnerNumberModel(2, 2, 6, 2));
        spinnerCountCannon.setBounds(1000, 300, 50, 30);

        MouseAdapter listenerTypes = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JLabel label = (JLabel) e.getSource();
                switch (label.getText()) {
                    case "Военный корабль":
                        ship = new MilShip(100, 1000, Color.BLACK);
                        break;
                    case "Крейсер":
                        ship = new Cruiser(100, 1000, Color.WHITE, Color.BLACK,
                                checkBoxMissileSystem.isSelected(), checkBoxAntiaircraftComplex.isSelected(), checkBoxControlSystem.isSelected(), (Integer) spinnerCountCannon.getValue(), 0);
                        break;
                }
                repaint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (e.getX() + ((JComponent) e.getSource()).getX() >= paintShip.getX() &&
                        e.getX() + ((JComponent) e.getSource()).getX() <= paintShip.getX() + paintShip.getWidth() &&
                        e.getY() + ((JComponent) e.getSource()).getY() >= paintShip.getY() &&
                        e.getY() + ((JComponent) e.getSource()).getY() <= paintShip.getY() + paintShip.getHeight()) {
                    ship.setPosition(50, 120, paintShip.getWidth(), paintShip.getHeight());
                    paintShip.setShip(ship);
                    repaint();
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };

        getContentPane().add(labelTools);
        getContentPane().add(labelMaxSpeed);
        getContentPane().add(spinnerMaxSpeed);
        getContentPane().add(labelWeight);
        getContentPane().add(spinnerWeight);
        getContentPane().add(checkBoxMissileSystem);
        getContentPane().add(checkBoxAntiaircraftComplex);
        getContentPane().add(checkBoxControlSystem);
        labelCruiser.addMouseListener(listenerTypes);
        labelMilShip.addMouseListener(listenerTypes);
        add(labelType);
        add(labelMilShip);
        add(labelCruiser);

        //панель
        paintShip = new PaintShip();
        getContentPane().add(paintShip);
        paintShip.setBounds(350, 40, 400, 180);
        paintShip.setBorder(BorderFactory.createLineBorder(Color.BLACK, 10));
        paintShip.setShip(null);

        //цвета
        JLabel labelColors = new JLabel("Цвета");
        labelColors.setBounds(500, 310, 100, 20);

        JLabel labelMainColor = new JLabel("Основной цвет");
        labelMainColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelMainColor.setBounds(400, 350, 170, 55);
        labelMainColor.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelAddColor = new JLabel("Доп. цвет");
        labelAddColor.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAddColor.setBounds(580, 350, 170, 55);
        labelAddColor.setHorizontalAlignment(SwingConstants.CENTER);

        MouseListener listenerView = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (ship != null) {
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };
        paintShip.addMouseListener(listenerView);

        MouseAdapter listenerColor = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JPanel panelColor = (JPanel) e.getSource();
                shipColor = panelColor.getBackground();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (paintShip.getMilShip() != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= labelMainColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelMainColor.getX() + labelMainColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelMainColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelMainColor.getY() + labelMainColor.getHeight()) {
                        paintShip.getMilShip().setMainColor(shipColor);
                        ship.setMainColor(shipColor);
                        repaint();
                    } else if (e.getX() + ((JComponent) e.getSource()).getX() >= labelAddColor.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getX() <= labelAddColor.getX() + labelAddColor.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= labelAddColor.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= labelAddColor.getY() + labelAddColor.getHeight() &&
                            ship instanceof Cruiser) {
                        Cruiser ship = paintShip.getShip();
                        (ship).setDopColor(shipColor);
                        if (paintShip.getShip().getAdditions() != null) {
                            switch (numberAdditions) {
                                case 1:
                                    additions = new DrawFirstAddition((Integer) spinnerCountCannon.getValue(), shipColor);
                                    break;
                                case 2:
                                    additions = new DrawSecondAddition((Integer) spinnerCountCannon.getValue(), shipColor);
                                    break;
                                case 3:
                                    additions = new DrawThirdAddition((Integer) spinnerCountCannon.getValue(), shipColor);
                                    break;
                            }
                            paintShip.getShip().setAdditions(additions);
                        }
                        repaint();
                    }
                }
                shipColor = null;
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
            }
        };


        JPanel panelRed = new JPanel();
        panelRed.setBackground(Color.RED);
        panelRed.setBounds(450, 440, 50, 50);
        panelRed.addMouseListener(listenerColor);

        JPanel panelYellow = new JPanel();
        panelYellow.setBackground(Color.YELLOW);
        panelYellow.setBounds(510, 440, 50, 50);
        panelYellow.addMouseListener(listenerColor);

        JPanel panelGray = new JPanel();
        panelGray.setBackground(Color.GRAY);
        panelGray.setBounds(450, 500, 50, 50);
        panelGray.addMouseListener(listenerColor);

        JPanel panelBrown = new JPanel();
        panelBrown.setBackground(new Color(65, 25, 0));
        panelBrown.setBounds(510, 500, 50, 50);
        panelBrown.addMouseListener(listenerColor);

        JPanel panelBlack = new JPanel();
        panelBlack.setBackground(Color.BLACK);
        panelBlack.setBounds(570, 440, 50, 50);
        panelBlack.addMouseListener(listenerColor);

        JPanel panelWhite = new JPanel();
        panelWhite.setBackground(Color.WHITE);
        panelWhite.setBounds(570, 500, 50, 50);
        panelWhite.addMouseListener(listenerColor);

        JPanel panelGreen = new JPanel();
        panelGreen.setBackground(Color.GREEN);
        panelGreen.setBounds(630, 440, 50, 50);
        panelGreen.addMouseListener(listenerColor);

        JPanel panelBlue = new JPanel();
        panelBlue.setBackground(Color.BLUE);
        panelBlue.setBounds(630, 500, 50, 50);
        panelBlue.addMouseListener(listenerColor);

        getContentPane().add(labelColors);
        getContentPane().add(labelMainColor);
        getContentPane().add(labelAddColor);
        getContentPane().add(panelRed);
        getContentPane().add(panelYellow);
        getContentPane().add(panelGray);
        getContentPane().add(panelBrown);
        getContentPane().add(panelBlack);
        getContentPane().add(panelWhite);
        getContentPane().add(panelGreen);
        getContentPane().add(panelBlue);


        MouseListener listenerColorLabel = new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                JLabel labelColor = (JLabel) e.getSource();
                switch (labelColor.getText()) {
                    case "Основной цвет": {
                        if (ship != null) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    break;
                    case "Доп цвет": {
                        if (ship instanceof Cruiser) {
                            setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        }
                    }
                    break;
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        };

        labelMainColor.addMouseListener(listenerColorLabel);
        labelAddColor.addMouseListener(listenerColorLabel);
        //дополнение
        JLabel labelAdditions = new JLabel("Дополнение");
        labelAdditions.setBounds(950, 10, 190, 20);

        JLabel labelAdditionsFirst = new JLabel("первый вид");
        labelAdditionsFirst.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAdditionsFirst.setBounds(900, 50, 190, 55);
        labelAdditionsFirst.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelAdditionsSecond = new JLabel("второй вид");
        labelAdditionsSecond.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAdditionsSecond.setBounds(900, 125, 190, 55);
        labelAdditionsSecond.setHorizontalAlignment(SwingConstants.CENTER);

        JLabel labelAdditionsThird = new JLabel("третий вид");
        labelAdditionsThird.setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));
        labelAdditionsThird.setBounds(900, 200, 190, 55);
        labelAdditionsThird.setHorizontalAlignment(SwingConstants.CENTER);

        getContentPane().add(labelAdditions);
        getContentPane().add(labelAdditionsFirst);
        getContentPane().add(labelAdditionsSecond);
        getContentPane().add(labelAdditionsThird);
        getContentPane().add(labelCountCannon);
        getContentPane().add(spinnerCountCannon);

        MouseAdapter listenerAdditions = new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                JLabel labelAdditions = (JLabel) e.getSource();
                if (checkBoxMissileSystem.isSelected()) {
                    switch (labelAdditions.getText()) {
                        case "первый вид":
                            additions = new DrawFirstAddition((Integer) spinnerCountCannon.getValue(), paintShip.getShip().getDopColor());
                            numberAdditions = 1;
                            break;
                        case "второй вид":
                            additions = new DrawSecondAddition((Integer) spinnerCountCannon.getValue(), paintShip.getShip().getDopColor());
                            numberAdditions = 2;
                            break;
                        case "третий вид":
                            additions = new DrawThirdAddition((Integer) spinnerCountCannon.getValue(), paintShip.getShip().getDopColor());
                            numberAdditions = 3;
                            break;
                    }
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (ship != null) {
                    if (e.getX() + ((JComponent) e.getSource()).getX() >= paintShip.getX() &&
                            e.getX() + ((JComponent) e.getSource()).getAlignmentX() <= paintShip.getX() + paintShip.getWidth() &&
                            e.getY() + ((JComponent) e.getSource()).getY() >= paintShip.getY() &&
                            e.getY() + ((JComponent) e.getSource()).getY() <= paintShip.getY() + paintShip.getHeight() &&
                            ship instanceof Cruiser) {
                        ((Cruiser) ship).setTypeCannon(numberAdditions);
                        ((Cruiser) ship).setAdditions(additions);
                        paintShip.getShip().setAdditions(additions);
                        repaint();
                    }
                }
                setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
                additions = null;
            }
        };
        labelAdditionsFirst.addMouseListener(listenerAdditions);
        labelAdditionsSecond.addMouseListener(listenerAdditions);
        labelAdditionsThird.addMouseListener(listenerAdditions);

        //Добавить и отмена
        JButton buttonAdd = new JButton("Добавить");
        buttonAdd.setBounds(920, 400, 150, 30);
        JButton buttonCancel = new JButton("Отмена");
        buttonCancel.setBounds(920, 450, 150, 30);
        getContentPane().add(buttonAdd);
        getContentPane().add(buttonCancel);

        buttonAdd.addActionListener(e -> {
            dispose();
        });
        buttonCancel.addActionListener(e -> {
            paintShip.setShip(null);
            dispose();
        });
        repaint();
        setVisible(true);
    }

    public MilShip getShip() {
        return ship;
    }
}
