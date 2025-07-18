package main;

import javax.swing.JFrame;

public class Main {
    //Main
    public static void main(String[] args) {
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false); //não podemos move-la
        window.setTitle("Snake Run");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel); //adicionando configurações a janela

        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);
        gamePanel.startGameThread();

    }
}

