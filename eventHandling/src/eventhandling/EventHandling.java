/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eventhandling;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.IOException;
import static java.util.Arrays.stream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

public class EventHandling extends JFrame {

    private javax.swing.JTextArea textArea = new JTextArea();
    private javax.swing.JPanel basePanel = new JPanel();
    private JPanel bottomPanel = new JPanel();

    private JLabel lb1l, lbl2, lbl3;
    private Canvas canvas = new Canvas();

    Runnable r2;
    Thread th;

    public Graphics g;
    public BufferStrategy bs;

    GridBagLayout gbl = new GridBagLayout();
    GridBagConstraints gbc = new GridBagConstraints();

    public EventHandling() throws IOException {
        
        this.setIconImage(ImageIO.read(getClass().getResource("/res/boss.png")));
        
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        this.setSize(400, 400);
        this.setResizable(false);
        this.basePanel.setSize(new Dimension(400, 100));
        this.basePanel.setLayout(gbl);

        gbc.anchor = GridBagConstraints.NORTH;
        gbc.fill = GridBagConstraints.BOTH;
        gbc.gridwidth = 50;
        gbc.insets = (new Insets(10, 10, 10, 10));

        lb1l = new JLabel();
        lbl2 = new JLabel();
        lbl3 = new JLabel();

        canvas.setSize(150, 150);
        canvas.setBackground(Color.WHITE);

        this.lb1l.setText("I am one");
        this.lb1l.setBackground(Color.red);
        this.lb1l.setBorder(BorderFactory.createEtchedBorder());
        this.lbl2.setBorder(BorderFactory.createEtchedBorder());
        this.lbl3.setBorder(BorderFactory.createEtchedBorder());
        this.lbl2.setText("Click! HERE!");
        this.lbl3.setText("I am three");
        this.lb1l.setOpaque(true);

        gbl.setConstraints(this.lb1l, gbc);
        gbl.setConstraints(this.lbl2, gbc);
        gbl.setConstraints(this.lbl3, gbc);
        gbc.anchor = GridBagConstraints.SOUTH;
        gbc.fill = GridBagConstraints.REMAINDER;
        gbl.setConstraints(this.canvas, gbc);

        this.basePanel.add(lb1l);
        this.basePanel.add(lbl2);
        this.basePanel.add(lbl3);
        this.basePanel.setBorder(BorderFactory.createTitledBorder("I am basePanel"));

        gbl.setConstraints(canvas, gbc);
        this.canvas.setSize(new Dimension(200, 200));
        this.canvas.setBackground(Color.PINK);

        this.canvas.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                r2 = () -> {
                    try {
                        draw();
                    } catch (Exception ex) {
                        Logger.getLogger(EventHandling.class.getName()).log(Level.SEVERE, null, ex);
                    }
                };
                th = new Thread(r2);
                th.start();
                try {
                    th.join();
                } catch (InterruptedException ex) {
                    Logger.getLogger(EventHandling.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //canvas.setBackground(Color.BLACK);
            }

            @Override
            public void mouseExited(MouseEvent e) {
               // canvas.setBackground(Color.BLUE);
            }
        });
        this.lbl2.addMouseListener(new java.awt.event.MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

                r = 0; 
                try {
                    draw();
//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                } catch (Exception ex) {
                    Logger.getLogger(EventHandling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }

            @Override
            public void mouseExited(MouseEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });

        this.bottomPanel.setLayout(gbl);
        this.bottomPanel.add(canvas);
        this.bottomPanel.setBorder(BorderFactory.createTitledBorder("I am bottomPanel"));

        this.add("Center", basePanel);
        this.add("South", bottomPanel);

    }

    private float r = 0.05f;

    public void draw() throws IOException, InterruptedException {

        Graphics2D g = (Graphics2D) this.canvas.getGraphics();
        BufferedImage b = ImageIO.read(getClass().getResource("/res/boss.png"));

        g.fillRect(0, 0, canvas.getWidth(), canvas.getHeight());
        g.rotate(r);
        g.drawImage(b, 0, 0, null);
        r += 0.005f;

    }

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    new EventHandling().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(EventHandling.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });

    }

}
