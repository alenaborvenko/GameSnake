package snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import javax.swing.JButton;
/**
 * Поле для игры в змейку и ее логика
 * @author alenk
 */
public class GameFolder extends JPanel implements ActionListener {

    private final int SIZE = 320;
    private final int DOT_SIZE = 16;
    private final int ALL_DOTS = 400;
    private Image dot;
    private Image apple;
    private int appleX;
    private int appleY;
    private int[] x = new int[ALL_DOTS];
    private int[] y = new int[ALL_DOTS];
    private int dots;
    private Timer timer;
    private boolean left = false;
    private boolean right = true;
    private boolean up = false;
    private boolean down = false;
    private boolean inGame = true;
    
    private  JButton start,exit;


    public GameFolder() {
		//хоть треснию но беграунд не устанавливается!
        //     setBackground(Color.black);
         start = new JButton();
        exit = new JButton();
        start.setText("Start Game");
        exit.setText("Exit");

        add(start, new FlowLayout(FlowLayout.LEFT));
        add(exit, new FlowLayout(FlowLayout.RIGHT));
        start.setVisible(true);
        exit.setVisible(true);
        loadImages();
        initJPanel();


    }

    public void initJPanel() {

        start.setVisible(true);
        exit.setVisible(true);
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
            x[i] = 48 - i * DOT_SIZE;
            y[i] = 48;
        }
        timer = new Timer(250,this);
        timer.start();
        createApple();
    }

    public void createApple() {
        appleX = new Random().nextInt(20) * DOT_SIZE;
        appleY = new Random().nextInt(20) * DOT_SIZE;
    }

    public void loadImages() {
		// почему указывается полный путь????
        ImageIcon iia = new ImageIcon("C:\\Users\\alenk\\OneDrive\\Рабочий стол\\GitHub\\SimpleGame\\New Folder\\Snake\\src\\snake\\apple.png");
        apple = iia.getImage();
        ImageIcon iid = new ImageIcon("C:\\Users\\alenk\\OneDrive\\Рабочий стол\\GitHub\\SimpleGame\\New Folder\\Snake\\src\\snake\\dot.png");
        dot = iid.getImage();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (inGame) {
            g.drawImage(apple, appleX, appleY, this);
            for (int i = 0; i < dots; i++) {
                g.drawImage(dot, x[i], y[i], this);
            }
        } else {
            String str = "Game Over";
            // Font f = new Font("Arial",140,Font.BOLD);
            g.setColor(Color.black);
            //  g.setFont(f);
            g.drawString(str, 125, SIZE / 2);
            int score = 10 * dots;
            str = "Your score is " + score;
            g.drawString(str, 125, SIZE / 4);
            //this.inGame = true;
			//******* вот тут затык!!!! надо разобраться
            initJPanel();
        }
    }

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

    public void checkApple() {
        if (x[0] == appleX && y[0] == appleY) {
            dots++;
            createApple();
        }
    }

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

        }
        else{
            inGame = true;
            initGame();
                addKeyListener(new FieldKeyListener());
                setFocusable(true);
        }
        repaint();
    }

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
