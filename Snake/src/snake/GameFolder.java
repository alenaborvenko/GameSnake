package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
import java.awt.Font;


/**
 * Поле для игры в змейку и ее логика
 *
 * @author alenk
 */
public class GameFolder extends JPanel implements ActionListener {

    private final int SIZE = 320; // размер экрана
    private final int DOT_SIZE = 16; // размер ячейки
    private final int ALL_DOTS = 400; //количество ячеек макс
    private Image dot;
    private Image apple;
    private int appleX; // координаты яблока
    private int appleY;
    private int[] x = new int[ALL_DOTS]; // координаты змейки
    private int[] y = new int[ALL_DOTS];
    private int dots; //текущее количество яблок
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;

    private JButton start, exit;
//*********       Конструктор класса
    public GameFolder() {

        setBackground(Color.black);
        start = new JButton();
        exit = new JButton();
        start.setText("Start Game");
        exit.setText("Exit");

        add(start, new FlowLayout(FlowLayout.LEFT));
        add(exit, new FlowLayout(FlowLayout.RIGHT));
        // с этой частотой перересовывается поле и создается новое яблоко
        timer = new Timer(250, this);
        loadImages();
        initJPanel();

    }

    public void initJPanel() {

        start.setVisible(true);
        exit.setVisible(true);
        // если нажали на старт иницилизируем игру
        start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                start.setVisible(false);
                exit.setVisible(false);
                initGame();
                addKeyListener(new FieldKeyListener());
                setFocusable(true);
            }
        });
        exit.addActionListener(e -> System.exit(0));

    }

    public void initGame() {

        dots = 3;
        inGame = true;
        for (int i = 0; i < dots; i++) {
            //расположим змейку по оси х
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }

        timer.start();
        createApple();
    }

    public void createApple() {
        // рандомно создадим координаты яблоко
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages() {

        ImageIcon iia = new ImageIcon("C:\\Users\\alenk\\OneDrive\\Рабочий стол\\GitHub\\Snake\\GameSnake\\Snake\\src\\snake\\apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("C:\\Users\\alenk\\OneDrive\\Рабочий стол\\GitHub\\Snake\\GameSnake\\Snake\\src\\snake\\dot.png");
        dot = iid.getImage();
    }

    @Override
    @SuppressWarnings("empty-statement")
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str = "Game Over";
            Font f = new Font("Arial", Font.BOLD, 15);
            g.setColor(Color.white);
            g.setFont(f);
            g.drawString(str, 115, SIZE / 4);
            int score = 10 * dots;
            str = "Your score is " + score;
            g.drawString(str, 100, SIZE / 2);
            //this.inGame = true;
            timer.stop();;
            initJPanel();
        }
    }
// описание движения
    public void move() {
        for (int i = dots; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }
        if (left) {
            x[0] -= DOT_SIZE;
        }
        if (right) {
            x[0] += DOT_SIZE;
        }
        if (up) {
            y[0] -= DOT_SIZE;
        }
        if (down) {
            y[0] += DOT_SIZE;
        }
    }
// съели ли яблоко
    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
    }
// проверка на столкновения
    public void checkCollisions() {
        for (int i = dots; i > 0; i--) {
            if (i > 4 && x[0] == x[i] && y[0] == y[i]) {
                inGame = false;
            }
        }

        if (x[0] > SIZE) {
            inGame = false;
        }
        if (x[0] < 0) {
            inGame = false;
        }
        if (y[0] > SIZE) {
            inGame = false;
        }
        if (y[0] < 0) {
            inGame = false;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (inGame) {
            checkApple();
            checkCollisions();
            move();

        } else {
            inGame = true;
            initGame();
            addKeyListener(new FieldKeyListener());
            setFocusable(true);
        }
        repaint();
    }
// обработка нажатия клавиш
    class FieldKeyListener extends KeyAdapter {

        @Override
        public void keyPressed(KeyEvent e) {
            super.keyPressed(e);
            int key = e.getKeyCode();
            if (key == KeyEvent.VK_LEFT && !right) {
                left = true;
                up = false;
                down = false;
            }
            if (key == KeyEvent.VK_RIGHT && !left) {
                right = true;
                up = false;
                down = false;
            }

            if (key == KeyEvent.VK_UP && !down) {
                right = false;
                up = true;
                left = false;
            }
            if (key == KeyEvent.VK_DOWN && !up) {
                right = false;
                down = true;
                left = false;
            }
        }
    }

}
