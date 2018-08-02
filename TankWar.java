import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


class TankWar extends Frame {

        public static final int GAME_WIDTH = 400 ;
        public static final int GAME_HEIGHT = 300 ;

        Tank tk = new Tank(50,50);

        Image offsetImage = null;

        public TankWar(){
                this.setSize(GAME_WIDTH,GAME_HEIGHT);
                this.setVisible(true);
                this.setBackground(Color.GREEN);

                this.addWindowListener(new WindowAdapter(){
                        public void windowClosing(WindowEvent e){
                                System.exit(0);
                        }
                });
                this.setResizable(false);
                this.setTitle("guorui");

                KeyMonitor km = new KeyMonitor();
                this.addKeyListener(km);

                new Thread(){
                        public void run(){
                                while (true){
                                        repaint();
                                        try {
                                                //must 100,1000 stop
                                                //处理按键后移动不均匀的问题。
                                                Thread.sleep(100);
                                        } catch(Exception e) {
                                                e.printStackTrace();
                                        }
                                }
                        }
                }.start();


        }

        public void paint(Graphics g){
                super.paint(g);

                tk.draw(g);
                tk.move();

                //Missile m = tk.getMissile();
                //if(m!=null){
                //        m.draw(g);
                //        m.move();
                //}
                List<Missile> ms = tk.getMissiles();
                g.drawString("Missile count:"+ms.size(),10,50);
                for (int i=0;i<ms.size();i++ ) {
                        Missile m = ms.get(i);
                        if(!m.isLive()){
                                ms.remove(m);
                        }
                        if(m!=null){
                                m.draw(g);
                                m.move();
                        }
                }
        }

        public void update(Graphics g){
                //Image offsetImage = this.createImage(400,300);
                if (offsetImage == null){ 
                        offsetImage = this.createImage(GAME_WIDTH,GAME_HEIGHT);
                }
                Graphics offsetImageG = offsetImage.getGraphics();

                Color c = offsetImageG.getColor();
                offsetImageG.setColor(Color.GREEN);
                offsetImageG.fillRect(0,0,GAME_WIDTH,GAME_HEIGHT);
                offsetImageG.setColor(c);

                paint(offsetImageG);
                g.drawImage(offsetImage,0,0,null);
        }


        public static void main (String [] args) {
                new TankWar();
        }

        private class KeyMonitor extends KeyAdapter{
                public void keyPressed(KeyEvent e){
                        tk.keyPressed(e);
                }

                public void keyReleased(KeyEvent e){
                        tk.keyReleased(e);
                }
        }

}
