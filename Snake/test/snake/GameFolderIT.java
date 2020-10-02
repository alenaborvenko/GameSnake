/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snake;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author alenk
 */
public class GameFolderIT {
    
    public GameFolderIT() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of initGame method, of class GameFolder.
     */
    @Test
    public void testInitGame() {
        System.out.println("initGame");
        GameFolder instance = new GameFolder();
        instance.initGame();
    }

    /**
     * Test of createApple method, of class GameFolder.
     */
    @Test
    public void testCreateApple() {
        System.out.println("createApple");
        GameFolder instance = new GameFolder();
        instance.createApple();
    }

    /**
     * Test of loadImages method, of class GameFolder.
     */
    @Test
    public void testLoadImages() {
        System.out.println("loadImages");
        GameFolder instance = new GameFolder();
        instance.loadImages();
    }


    /**
     * Test of move method, of class GameFolder.
     */
    @Test
    public void testMove() {
        System.out.println("move");
        GameFolder instance = new GameFolder();
        instance.move();
    }

    /**
     * Test of checkApple method, of class GameFolder.
     */
    @Test
    public void testCheckApple() {
        System.out.println("checkApple");
        GameFolder instance = new GameFolder();
        instance.checkApple();
    }

    /**
     * Test of checkCollisions method, of class GameFolder.
     */
    @Test
    public void testCheckCollisions() {
        System.out.println("checkCollisions");
        GameFolder instance = new GameFolder();
        instance.checkCollisions();
    }

    
}
