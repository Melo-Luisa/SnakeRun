package entity;

//import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyInput;

public class Player extends Entity{
    GamePanel gp;
    KeyInput keyI;

    public Player(GamePanel gp, KeyInput keyI){
        this.gp = gp;
        this.keyI = keyI;
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues(){
        x = 100;
        y = 100;
        speed = 5;
        direction = "down";
    }
    
    public void getPlayerImage(){
        try{
            up1 = ImageIO.read(getClass().getResourceAsStream("res/player/up_snake_1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("res/player/up_snake_2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("res/player/down_snake_1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("res/player/down_snake_2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("res/player/left_snake_1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("res/player/left_snake_2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("res/player/right_snake_1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("res/player/right_snake_2.png"));

        }catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
         //atualizaçao de teclas
        if(keyI.upPressed == true){
            direction = "up";
            y -= speed; 
        }
        if(keyI.downPressed == true){
             direction = "down";
            y += speed;
        }
        if(keyI.leftPressed == true){
             direction = "left";
            x -= speed;
        }
        if(keyI.rightPressed == true){
             direction = "right";
            x += speed;
        }
    }

    @Override
    public void draw(Graphics2D g2){
        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.titleSize, gp.titleSize);

        BufferedImage image = null;

        switch(direction){
            case "up":
                image = up1;
                break;
            case "down":
                image = down1;
                break;
            case "left":
                image = left1;
                break;
            case "right":
                image = right1;
                break;
        }
        g2.drawImage(image, x, y, gp.titleSize, gp.titleSize, null);
    }

   
    
}
