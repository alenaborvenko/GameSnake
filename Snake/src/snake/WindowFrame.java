/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import javax.swing.JFrame;
/**
 *
 * @author alenk
 */
public class WindowFrame extends JFrame{
    private static boolean restartGame = true;

    public WindowFrame(){
        JFrame frame = new JFrame();
        frame.setTitle("Snake");
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        frame.setSize(320,345);
        frame.setLocation(400,400); 
      //  frame.setBackground(Color.black);
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
