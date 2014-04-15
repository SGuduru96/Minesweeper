/**
 * @author Shashank
 *
 * This is a simple recreation of minesweeper. The gui for the board is simply
 * a bunch of squares from an array called randboard[][]. These are redrawn everytime
 * the user clicks on a sqaure.
 */

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.net.*;
import java.io.*;
import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JTextField;

public class minesweeperSGuduru7 extends javax.swing.JFrame{
    private javax.swing.Timer timer;
    private int seconds;
    private BufferedImage[][] board = new BufferedImage[20][20];
    private BufferedImage[][] randboard = new BufferedImage[20][20];
    private Color mainc = Color.BLUE;
    private static int width = 419;
    private static int height = 419;
    private int boxheight = 400/20;
    private int boxwidth = 400/20;
    private myMouse mouse = new myMouse();
    private BufferedImage mine;
    private BufferedImage m1;
    private BufferedImage m2;
    private BufferedImage m3;
    private BufferedImage m4;
    private BufferedImage m5;
    private BufferedImage m6;
    private BufferedImage m7;
    private BufferedImage m8;
    private BufferedImage blocks;
    private BufferedImage empty;
    private BufferedImage flag;
    private BufferedImage question;
    private int[] vertical = {1,1,1,-1,-1,-1,0,0};
    private int[] horizontal = {1,0,-1,1,0,-1,1,-1};
    private int[] vertical2 = {1,-1,0,0};
    private int[] horizontal2 = {0,0,-1,1};
    private int nummines = 30;
    private int numpresses = 0;
    private JLabel label1;
    private JLabel label3;
    
    public minesweeperSGuduru7() {
        initComponents();
    }

    private void initComponents() {

        try{
            mine = ImageIO.read(new URL("file:mine.png"));
            m1 = ImageIO.read(new URL("file:1.png"));
            m2 = ImageIO.read(new URL("file:2.png"));
            m3 = ImageIO.read(new URL("file:3.png"));
            m4 = ImageIO.read(new URL("file:4.png"));
            m5 = ImageIO.read(new URL("file:5.png"));
            m6 = ImageIO.read(new URL("file:6.png"));
            m7 = ImageIO.read(new URL("file:7.png"));
            m8 = ImageIO.read(new URL("file:8.png"));
            empty = ImageIO.read(new URL("file:empty.png"));
            blocks = ImageIO.read(new URL("file:background.png"));
            flag = ImageIO.read(new URL("file:flag.png"));
            question = ImageIO.read(new URL("file:question.png"));

        }catch(IOException e){
            e.printStackTrace();
            System.out.println("ERROR");
        }

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.Y_AXIS));

        jPanel2 = new MySketchPad();
        jPanel2.setBackground(new Color(160, 82, 45));
        jPanel2.setMaximumSize(new java.awt.Dimension(width, height));
        jPanel2.setMinimumSize(new java.awt.Dimension(width, height));
        jPanel2.setPreferredSize(new java.awt.Dimension(width, height));
        jPanel2.addMouseListener(mouse);
        jPanel2.addMouseMotionListener(mouse);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );

        getContentPane().add(jPanel2);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("Timer"));
        jPanel3.setPreferredSize(new Dimension(150, 150));
        label3 = new JLabel("" + seconds);
        jPanel3.add(label3);
        jPanel3.setVisible(true);

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Mines"));

        jPanel4.setPreferredSize(new Dimension(150, 150));
        label1 = new JLabel("" + nummines);
        jPanel4.add(label1);
        jPanel4.setVisible(true);
        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(36, 36, 36)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1);

        jMenu1.setText("Game");

        jMenuItem1.setText("New Game");
        jMenu1.add(jMenuItem1);
        jMenuItem1.addActionListener((new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem1ActionPerformed(evt);
                    }
                }));

        jMenuItem2.setText("Exit");
        jMenu1.add(jMenuItem2);
        jMenuItem2.addActionListener((new java.awt.event.ActionListener() {
                    public void actionPerformed(java.awt.event.ActionEvent evt) {
                        jMenuItem2ActionPerformed(evt);
                    }
                }));

        jMenuBar1.add(jMenu1);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Help");

        jMenuItem4.setText("How To Play");
        jMenu3.add(jMenuItem4);
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem4ActionPerformed(evt);
                }
            });
        jMenuItem5.setText("About");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    jMenuItem5ActionPerformed(evt);
                }
            });

        jMenu3.add(jMenuItem5);
        jMenuBar1.add(jMenu3);
        setJMenuBar(jMenuBar1);
        pack();
        createBoard();
        randomGen(nummines);
        mineNums();
    }// </editor-fold>

    private void timerActionPerformed(java.awt.event.ActionEvent evt){
        seconds++;
        //System.out.println(seconds);
        label3.setText(""+seconds);
        //jPanel3.revalidate();
        jPanel3.repaint();
    }
    /*
        takes in coordinates are returns the square that those
        coordiantes fall into.
     */
    private position findCorner(int x, int y){
        int xpos = 0;
        int tempx = 0;
        for(int i = 0; i < 20; i++){
            if(xpos <= x){
                tempx++;
            }else{
                break;
            }
            xpos += (boxwidth+1);
        }

        int ypos = 0;
        int tempy = 0;
        for(int i = 0; i < 20; i++){
            if(ypos <= y){
                tempy++;
            }else{
                break;
            }
            ypos += (boxheight+1);
        }
        //System.out.println("X " + tempx + " Y " + tempy);
        position pos = new position(tempx, tempy);
        return pos;
    }

    /*
        checks in all 8 directions around a specified square.
     */
    private int checkAround(int x, int y){
        int count = 0;
        for(int i = 0; i < 8; i++){
            int row = x + horizontal[i];
            int col = y + vertical[i];
            if((row > -1 && row < randboard.length) && (col > -1 && col < randboard[0].length)){
                if(randboard[row][col] == mine){
                    count++;
                }
            }
        }
        return count;
    }

    /*
        Checks every single square in randboard[][]. Then depending on
         the amount of mines around a square a number is assigned(inthe form of a buffered image)
         to the current position on randboard[][].
     */
    private void mineNums(){
        for(int i = 0; i < boxwidth; i++){
            for(int j = 0; j < boxheight; j++){
                int num = checkAround(i, j);
                if(randboard[i][j] != mine){
                    if(num == 1){
                        randboard[i][j] = m1;
                    }else if(num == 2){
                        randboard[i][j] = m2;
                    }else if(num == 3){
                        randboard[i][j] = m3;
                    }else if(num == 4){
                        randboard[i][j] = m4;
                    }else if(num == 5){
                        randboard[i][j] = m5;
                    }else if(num == 6){
                        randboard[i][j] = m6;
                    }else if(num == 7){
                        randboard[i][j] = m7;
                    }else if(num == 8){
                        randboard[i][j] = m8;
                    }
                }
            }
        }
        //System.out.println("Done");
    }

     /*
        This loads up the howtoplay.html file when the user clicks on item
        4 in the menu.
      */
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt){
        try{
            JEditorPane helpContent = new JEditorPane(new URL("file:howtoplay.html"));
            JDialog dialog = new JDialog(this);
            dialog.setTitle("How to play");
            helpContent.setEditable(false);
            JScrollPane helpPane = new JScrollPane(helpContent);
            dialog.add(helpPane);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            Dimension size = new Dimension(800, 600);
            dialog.setPreferredSize(size);
            dialog.setMinimumSize(size);
            dialog.setVisible(true);
        }catch(IOException e){
            System.out.println("error");
        }
    }


    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt){
        System.exit(0);
    }

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt){
        board = new BufferedImage[20][20];
        randboard = new BufferedImage[20][20];

        createBoard();
        
        randomGen(30);
        mineNums();
        
        timer.stop();
        timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            timerActionPerformed(evt);
                        }
                    });
        seconds = 0;
        nummines = 30;
        
        label3.setText(""+seconds);
        //jPanel3.revalidate();
        jPanel3.repaint();
        
        label1.setText(""+nummines);
        jPanel4.repaint();
        numpresses = 0;
        jPanel2.repaint();
    }
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {
        try{
            JEditorPane helpContent = new JEditorPane(new URL("file:about.html"));
            JDialog dialog = new JDialog(this);
            dialog.setTitle("About");
            dialog.add(helpContent);
            helpContent.setEditable(false);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            Dimension size = new Dimension(400, 400);
            dialog.setPreferredSize(size);
            dialog.setMinimumSize(size);
            dialog.setVisible(true);
        }catch(IOException e){
            System.out.println("error");
        }
    }

    public void openSpace(int x, int y){

        if((x > -1 && x < randboard.length) && (y > -1 && y < randboard[0].length)){
            if(randboard[x][y] == null){
                board[x][y] = null;
                randboard[x][y] = empty;
                //jPanel2.repaint();

                for(int i = 0; i < 4; i++){
                    int row = x + horizontal2[i];
                    int col = y + vertical2[i];

                    openSpace(row, col);
                }
            }else if(randboard[x][y] == mine){
                board[x][y] = randboard[x][y];

                JLabel label2 = new JLabel("GAMEOVER");
                JFrame go = new JFrame();
                go.add(label2);
                JDialog dialog = new JDialog(this);
                dialog.setLayout(new FlowLayout());
                dialog.setTitle("GameOver");
                dialog.add(label2);
                dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                Dimension size = new Dimension(100, 100);
                dialog.setPreferredSize(size);
                dialog.setMinimumSize(size);
                dialog.setVisible(true);

            }else{
                board[x][y] = randboard[x][y];
            }
        }

    }

    private void createBoard(){
        //boolean cordFound = false;
        for(int row = 0; row <= board.length-1; row++){
            for(int col = 0; col <= board[0].length-1; col++){
                board[row][col] = blocks;
            }
        }

    }
    class myMouse extends MouseAdapter {
        public void mouseClicked(MouseEvent e){
            int x = e.getX();
            int y = e.getY();

            position bob = null;
            bob = findCorner(x, y);
            x = bob.getX()-1;
            y = bob.getY()-1;
            if(numpresses == 0){
                timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            timerActionPerformed(evt);
                        }
                    });
                timer.start();
            }
            numpresses++;

            //System.out.println("clicked");
            if(e.getButton() == e.BUTTON1){
                if(x <= 20 && x >= 0 && y <= 20 && y >= 0){
                    openSpace(x, y);
                    jPanel2.repaint();
                }
            }else if(e.getButton() == e.BUTTON3){
                if(x <= 20 && x >= 0 && y <= 20 && y >= 0){
                    if(board[x][y] == blocks){
                        board[x][y] = flag;
                        if(randboard[x][y] == mine){
                            nummines--;
                            //System.out.println("nummines" + nummines);
                            label1.setText(""+nummines);
                            //jPanel4.add(label1);
                            //jPanel4.revalidate();
                            jPanel4.repaint();
                        }
                    }else if(board[x][y] == flag){
                        board[x][y] = question;
                        if(randboard[x][y] == mine){
                            nummines++;
                            //System.out.println("nummines" + nummines);
                            label1.setText(""+nummines);
                            //jPanel4.add(label1);
                            jPanel4.revalidate();
                            jPanel4.repaint();
                        }
                    }else if(board[x][y] == question){
                        board[x][y] = blocks;
                    }

                    jPanel2.repaint();
                }
            }
        }

        @Override
        public void mouseDragged(MouseEvent e){
            //System.out.println("Dragging");
            int x = e.getX();
            int y = e.getY();

            position bob = null;
            bob = findCorner(x, y);
            x = bob.getX()-1;
            y = bob.getY()-1;
            if(numpresses == 0){
                timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener(){
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            timerActionPerformed(evt);
                        }
                    });
                timer.start();
            }
            numpresses++;
            //System.out.println(x + " " +  y);
            //System.out.println(e.getButton());
            if((e.getModifiersEx() & MouseEvent.BUTTON1_DOWN_MASK) == MouseEvent.BUTTON1_DOWN_MASK){
                if(x <= 20 && x >= 0 && y <= 20 && y >= 0){
                    openSpace(x, y);
                    jPanel2.repaint();
                }
            }else if((e.getModifiersEx() & MouseEvent.BUTTON3_DOWN_MASK) == MouseEvent.BUTTON3_DOWN_MASK){
                if(x <= 20 && x >= 0 && y <= 20 && y >= 0){
                    if(board[x][y] == blocks){
                        board[x][y] = flag;
                    }else if(board[x][y] == flag){
                        board[x][y] = question;
                    }else if(board[x][y] == question){
                        board[x][y] = blocks;
                    }

                    jPanel2.repaint();
                }
            }

        }
    }

    class MySketchPad extends JPanel {

        public void paintComponent(Graphics g) {
            super.paintComponent(g);

            Graphics2D g2 = (Graphics2D)g;

            g2.setColor(Color.GRAY);

            int xcount = 0;
            int ycount = 0;

            int xpos = 0;
            int ypos = 0;
            for(int i = 0; i < 20; i++){
                for(int j = 0; j < 20; j++){
                    g.drawImage(board[i][j], xpos, ypos, this);
                    ypos += (boxheight+1);
                    if(j == 19){
                        ypos = 0;
                    }
                }
                xpos += (boxwidth+1);
            }
        }
    }

    private void randomGen(int num){
        //System.out.println(num);
        int[] xlist = new int[num];
        int[] ylist = new int[num];
        int i = 0;
        int j = 0;
        int count = num;
        while(i < count && j < count && num > 0){
            int x = (int)(Math.random() * (boxwidth));
            int y = (int)(Math.random() * (boxheight));
            //System.out.println("x " + x + " y " + y);
            boolean cont = true;
            for(int k = 0; k < xlist.length-1; k++){
                for(int l = 0; l < ylist.length-1; l++){
                    if(x == xlist[k] && y == ylist[l]){
                        cont = false;
                        break;
                    }
                }
            }

            if(cont){
                randboard[x][y] = mine;
                //System.out.println("x " + i + " y " + j);
                i++;
                num--;
                if(i >= boxwidth){
                    j++;
                    i = 0;
                }
            }

        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(minesweeperSGuduru7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(minesweeperSGuduru7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(minesweeperSGuduru7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(minesweeperSGuduru7.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    new minesweeperSGuduru7().setVisible(true);
                }
            });
    }

    // Variables declaration - do not modify
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JPanel jPanel1;
    private MySketchPad jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    // End of variables declaration
}

class position{
    private int myX;
    private int myY;

    position(int x, int y){
        myX = x;
        myY = y;
    }

    public int getX(){
        return myX;
    }

    public int getY(){
        return myY;
    }
}

