import java.awt.*;
import java.awt.event.*;

class TankWar extends Frame {

        int x=50,y=50;
        Image offsetImage = null;

        public TankWar(){
                this.setSize(400,300);
                this.setVisible(true);
                this.setBackground(Color.GREEN);

                this.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                System.exit(0);
                        }
                });
                this.setResizable(false);
                this.setTitle("guorui");

                //
                this.addKeyListener(new KeyAdapter(){
                        public void keyPressed(KeyEvent e){
                                int key = e.getKeyCode();
                                if (key == e.VK_J){ 
                                        x += 5;
                                }
                        }
                });

                new Thread(){
                        public void run(){
                                while (true){
                                        repaint();
                                        try {
                                                Thread.sleep(500);
                                        } catch(Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                }.start();


        }

        public void paint(Graphics g){
                Color c = g.getColor();
                g.setColor(Color.RED);
                g.fillOval(x,y,30,30);
                g.setColor(c);

        }

        public void update(Graphics g){
                //Image offsetImage = this.createImage(400,300);
                if (offsetImage == null){ 
                        offsetImage = this.createImage(400,300);
                }
                Graphics offsetImageG = offsetImage.getGraphics();

                Color c = offsetImageG.getColor();
                offsetImageG.setColor(Color.GREEN);
                offsetImageG.fillRect(0,0,400,300);
                offsetImageG.setColor(c);

                paint(offsetImageG);
                g.drawImage(offsetImage,0,0,null);
        }

        public void repaint(){
                //x += 5;
                super.repaint();
        }

        public static void main (String [] args) {
                new TankWar();
        }

}
