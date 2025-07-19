package main;

import javax.swing.JPanel;

import entity.Player;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16x16 caracter
    final int scale = 3; //4x o tamanho original
    public final int titleSize = originalTileSize * scale; //48x48 title

    //tamanho da janela
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int width = maxScreenCol * titleSize; // 768 px
    final int height = maxScreenRow * titleSize; // 576 px

    //FPS
    int FPS = 60;
   

    KeyInput keyI = new KeyInput();
    Player player = new Player(this, keyI);
    Thread gameThread; //multifunção em updates e atualizações do loop
    
    // //default player settings
    // int playerX = 100;
    // int playerY = 100;
    // int PlayerSpeed = 5;


    //construtor
    public GamePanel (){
        this.setPreferredSize(new Dimension (width,height));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true); //melhor performace
        this.addKeyListener(keyI);
        this.setFocusable(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this); // passando o jogo como paramento
        gameThread.start();

    }

    //sobreposição do método
    @Override
    public void run() {
        //delta método - mais smooth
        double drawInterval = 1000000000/FPS; //0.01666s
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        int drawCount = 0;
        //Aqui será o game lopp
        while(gameThread != null){
            currentTime = System.nanoTime();
            delta += (currentTime - lastTime)/drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;
            if(delta >= 1){
                update();
                repaint();
                delta--;
                drawCount++;
            }

            if(timer >= 1000000000){
                System.out.println("FPS: " + drawCount );
                drawCount = 0;
                timer = 0;
            }
            
        }
    }


    public void update(){
       player.update();
       
       
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //necessário usar se usar Graphics 

        Graphics2D g2 = (Graphics2D)g; // g é um novo objeto de Graphics2D

        player.draw(g2);
        g2.dispose();
    }




}