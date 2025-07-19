package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Entity {
    public int x, y, speed;

    public abstract void update();
    public abstract void draw(Graphics2D g2);

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2;

    public String direction;
}