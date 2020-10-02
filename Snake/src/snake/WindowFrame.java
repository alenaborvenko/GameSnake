/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Color;
import javax.swing.JFrame;

/**
 * Основной фрейм игры
 *
 * @author alenk
 */
public class WindowFrame extends JFrame {

    public WindowFrame() {
        JFrame frame = new JFrame();
        //****** заполнение основных настроек поля игры
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(320, 345);
        frame.setLocation(400, 400);
        frame.setBackground(Color.black);
        frame.add(new GameFolder());
        frame.setVisible(true);
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        WindowFrame wf = new WindowFrame();
    }
}
