import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;


class TankWar extends Frame {

        public static final int GAME_WIDTH = 400 ;
        public static final int GAME_HEIGHT = 300 ;

        Tank tk = new Tank(20,50,true);

        //Tank enemyTank = new Tank(100,100,false);
        Tank enemyTank = null;

        //Explode e = new Explode(50,50);

        Image offsetImage = null;
        Wall w1 = new Wall(250,50,100,20);
        Wall w2 = new Wall(60,80,20,200);

        public TankWar(){

                for (int i=0;i<7;i++ ){
                        enemyTank = new Tank(100*(i+3)/3,100,false);
                        //enemyTank.setDir(Direction.D);
                        Tank.TS.add(enemyTank);
                } 

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

                w1.draw(g);
                w2.draw(g);

                //enemyTank.draw(g);
                //enemyTank.move();
                Tank enemyTank = null;
                for (int i=0;i<Tank.TS.size() ; i++) {
                        enemyTank = Tank.TS.get(i);
                        enemyTank.draw(g);
                        enemyTank.move();

                        enemyTank.collidesWith(w1);
                        enemyTank.collidesWith(w2);
                }


                //Missile m = tk.getMissile();
                //if(m!=null){
                //        m.draw(g);
                //        m.move();
                //}
                //List<Missile> ms = tk.getMissiles();

                //修改Missile为静态类属性。
                List<Missile> ms = Missile.MS; 
                for (int i=0;i<ms.size();i++ ) {
                        Missile m = ms.get(i);
                        //可以专门起一个线程处理炮弹

                        //用炮弹打坦克
                        //m.hit(enemyTank);
                        //打中坦克炮弹消失
                        //if (m.hit(enemyTank)){ 
                        //        m.setLive(false);
                        //}
                        for (int j=0;j<Tank.TS.size() ; j++) {
                                enemyTank = Tank.TS.get(j);
                                if (m.hit(enemyTank)){ 
                                        m.setLive(false);
                                }
                                if (m.hit(tk)){ 
                                        m.setLive(false);
                                }
                        }
                        if(m.hit(w1) || m.hit(w2)){
                                m.setLive(false);
                        }

                        if(!m.isLive()){
                                ms.remove(m);
                        }
                        if(m!=null){
                                m.draw(g);
                                m.move();
                        }
                }
                //e.draw(g);
                for (int i=0;i<Explode.es.size() ;i++ ) {
                       Explode e = Explode.es.get(i);
                       e.draw(g);
                       //Explode.es.remove(i);
                }

                g.drawString("Missile count:"+ms.size(),10,35);
                g.drawString("Explode count:"+Explode.es.size(),10,50);
                g.drawString("Tank count:"+Tank.TS.size(),10,65);
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
