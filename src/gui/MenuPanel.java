package gui;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowEvent;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * Gui menu class
 * @author mikig
 */
public class MenuPanel extends javax.swing.JPanel {
    /** Instance MenuPanel class */
    private static final MenuPanel MENU_PANEL = new MenuPanel();
    /** Background instance class */
    private Image img;
    /**
     * Creates new form gui.MenuPanel
     */
    private MenuPanel() {
        try {
            img = ImageIO.read(getClass().getResource("/gui/Graphics/menuGraphic.jpg"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        initComponents();
        checkScoreBoardActive();

    }
    /**
     * Return MenuPanel instance
     * @return MenuPanel instance
     */
    public static MenuPanel getInstance(){
        return MENU_PANEL;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draws the img to the BackgroundPanel.
        if (img != null) {
            g.drawImage(img, 0, 0, null);
        }
    }


    public Dimension getPreferredSize() {
        return img == null ? super.getPreferredSize() : new Dimension(img.getWidth(this), img.getHeight(this));
    }

    /**
     * Checks database connection and set button enable or not
     */
    private void checkScoreBoardActive(){
        if(!Window.isDataBaseConnected){
            scoreButton.setEnabled(false);
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents() {

        exitButton = new javax.swing.JButton();
        startButton = new javax.swing.JButton();
        scoreButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(1280, 1024));

        exitButton.setBackground(new java.awt.Color(27, 18, 46));
        exitButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        exitButton.setForeground(new java.awt.Color(255, 255, 255));
        exitButton.setText("EXIT");
        exitButton.setActionCommand("scoreButton");
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        startButton.setBackground(new java.awt.Color(74, 49, 125));
        startButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        startButton.setForeground(new java.awt.Color(255, 255, 255));
        startButton.setText("START");
        startButton.setActionCommand("startButton");
        startButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startButtonActionPerformed(evt);
            }
        });
        scoreButton.setBackground(new java.awt.Color(54, 36, 92));
        scoreButton.setFont(new java.awt.Font("Tahoma", 0, 36)); // NOI18N
        scoreButton.setForeground(new java.awt.Color(255, 255, 255));
        scoreButton.setText("SCORE BOARD");
        scoreButton.setActionCommand("scoreButton");
        scoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                scoreButtonActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 48)); // NOI18N
        jLabel1.setText("8K GAME");
        jLabel1.setToolTipText("");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap(360, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(scoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 579, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(341, 341, 341))
                        .addGroup(layout.createSequentialGroup()
                                .addGap(533, 533, 533)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(97, 97, 97)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 164, Short.MAX_VALUE)
                                .addComponent(startButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(93, 93, 93)
                                .addComponent(scoreButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(97, 97, 97)
                                .addComponent(exitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(140, 140, 140))
        );

        exitButton.getAccessibleContext().setAccessibleName("scoreButton");
    }// </editor-fold>

    /**
     * open Scoreboard panel
     */
    private void scoreButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Window.scoreBoard(0);
    }

    /**
     * close application
     */
    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Window window = Window.getInstance();
        window.dispatchEvent(new WindowEvent(window, WindowEvent.WINDOW_CLOSING));
    }

    /**
     * starts game
     */
    private void startButtonActionPerformed(java.awt.event.ActionEvent evt) {
        Window.gameStart();
    }
    // Variables declaration - do not modify
    /** (Generated) */
    private javax.swing.JButton scoreButton;
    /** (Generated) */
    private javax.swing.JLabel jLabel1;
    /** (Generated) */
    private javax.swing.JButton startButton;
    /** (Generated) */
    private javax.swing.JButton exitButton;
    // End of variables declaration
}
