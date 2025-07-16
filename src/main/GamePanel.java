package main;

import javax.swing.JPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

public class GamePanel extends JPanel implements Runnable{
    //Screen Settings
    final int originalTileSize = 16; //16x16 caracter
    final int scale = 3; //4x o tamanho original
    final int titleSize = originalTileSize * scale; //48x48 title

    //tamanho da janela
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int width = maxScreenCol * titleSize; // 768 px
    final int height = maxScreenRow * titleSize; // 576 px

    //FPS
    int FPS = 60;

    KeyInput keyI = new KeyInput();
    Thread gameThread; //multifunção em updates e atualizações do loop

    //default player settings
    int playerX = 100;
    int playerY = 100;
    int PlayerSpeed = 5;


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
        double drawInterval = 1000000000/FPS; //0.01666s
        double nextDrawTime = System.nanoTime() + drawInterval;
        //Aqui será o game lopp
        while(gameThread != null){

            update();
            repaint(); // como chamamos o metodo paintComponent
            try{
                double remaningTime = nextDrawTime - System.nanoTime();
                remaningTime = remaningTime/100000; //miliseconds
                if(remaningTime < 0){
                    remaningTime = 0;
                }
                Thread.sleep((long)remaningTime);
                nextDrawTime += drawInterval;
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
    }


    public void update(){
        //atualizaçao de teclas
        if(keyI.upPressed == true){
            playerY -= PlayerSpeed; 
        }
        if(keyI.downPressed == true){
            playerY += PlayerSpeed;
        }
        if(keyI.leftPressed == true){
            playerX -= PlayerSpeed;
        }
        if(keyI.rightPressed == true){
            playerX += PlayerSpeed;
        }
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g); //necessário usar se usar Graphics 

        Graphics2D g2 = (Graphics2D)g; // g é um novo objeto de Graphics2D

        g2.setColor(Color.white);
        g2.fillRect(playerX, playerY, titleSize, titleSize);
        g2.dispose();
    }




}