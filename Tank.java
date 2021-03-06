import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.util.*;

enum Direction {U,D,L,R,LU,RU,LD,RD,STOP}

class Tank {
    
    public static final int WIDTH = 30 ;
    public static final int HEIGHT = 30 ;
    public static final List<Tank> TS = new ArrayList<Tank>();
    //public static final List<Missile> MS = new ArrayList<Missile>();

    private int x,y;
    private int oldX,oldY;

    private int speed = 3 ;
    private boolean bU=false,bD=false,bL=false,bR=false;
    private Direction dir = Direction.STOP;
    private Direction barrelDir = Direction.D;
    private Missile m;

    private boolean good;
                        
    private boolean live=true;

    private Random r = new Random();
    //private int step = 6;
    private int step = r.nextInt(8)+6;

    //TankWar tw = null;

    public Tank(int x,int y){
            this.x = x;
            this.y = y;
            this.oldX = x;
            this.oldY = y;
    }
    //public Tank(int x,int y,TankWar tw){
    //        this(x,y);
    //        this.tw = tw;
    //}

    public Tank(int x,int y,boolean good){
            this(x,y);
            this.good = good;
    }


    public void draw(Graphics g){
            if(!live){
                if (!good){ 
                   TS.remove(this);
                }
                return;
            } 

            int x,y;
            Color c = g.getColor();
            if (good) {
                  g.setColor(Color.RED);
            }else {
                  g.setColor(Color.BLUE);
            }
            g.fillOval(this.x,this.y,Tank.WIDTH,Tank.HEIGHT);

            g.setColor(Color.BLACK);
            switch (barrelDir) {
                case U: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x,y-Tank.HEIGHT/2);
                   break;
                case D: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x,y+Tank.HEIGHT/2);
                   break;
                case L: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x-Tank.WIDTH/2,y);
                   break;
                case R: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x+Tank.WIDTH/2,y);
                   break;
                case LU: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x-(int)(Tank.WIDTH/2*0.707),y-(int)(Tank.HEIGHT/2*0.707));
                   break;
                case LD: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x-(int)(Tank.WIDTH/2*0.707),y+(int)(Tank.HEIGHT/2*0.707));;
                   break;
                case RU: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x+(int)(Tank.WIDTH/2*0.707),y-(int)(Tank.HEIGHT/2*0.707));;
                   break;
                case RD: 
                   x = this.x+Tank.WIDTH/2;
                   y = this.y+Tank.HEIGHT/2;
                   g.drawLine(x,y,x+(int)(Tank.WIDTH/2*0.707),y+(int)(Tank.HEIGHT/2*0.707));;
                   break;
            }

            g.setColor(c);
    }

    //使用move方法解决，坦克可以朝八个方向走。同时解决按键移动不均匀的问题,speed由重绘的线程决定。
    //使用locateDirection方法来确认坦克的方向。
    public void move(){
            oldX = x;
            oldY = y;

            int speed1 = (int)(0.707f*speed);
            switch (dir) {
                case U: 
                   y -= speed;
                   break;
                case D: 
                   y += speed;
                   break;
                case L: 
                   x -= speed;
                   break;
                case R: 
                   x += speed;
                   break;
                case LU: 
                   x -= speed1;
                   y -= speed1;
                   break;
                case LD: 
                   x -= speed1;
                   y += speed1;
                   break;
                case RU: 
                   x += speed1;
                   y -= speed1;
                   break;
                case RD: 
                   x += speed1;
                   y += speed1;
                   break;
                case STOP:
                   break;
            }

            if(x<=0) x =0 ;
            if(y<=0) y =0 ;
            if(x>=TankWar.GAME_WIDTH) x =TankWar.GAME_WIDTH ;
            if(y>=TankWar.GAME_HEIGHT) y =TankWar.GAME_HEIGHT;


            if(this.dir != Direction.STOP){ this.barrelDir = this.dir;}

            if (!good){ 
                   if (step==0){ 
                           //step = 6;
                           step = r.nextInt(8)+6;
                           Direction[] dirs = Direction.values();
                           int i = r.nextInt(dirs.length);
                           //this.dir = Direction.D; 
                           this.dir = dirs[i]; 
                   }

                   step--;

                   //this.fire();
                   if(r.nextInt(40)>38) this.fire();
                   
            }
    }
    public void locateDirection(){
            if(!bU && !bD && !bL && !bR){ dir = Direction.STOP;}
            else if(bU && !bD && !bL && !bR){ dir = Direction.U;}
            else if(!bU && bD && !bL && !bR){ dir = Direction.D;}
            else if(!bU && !bD && bL && !bR){ dir = Direction.L;}
            else if(!bU && !bD && !bL && bR){ dir = Direction.R;}
            else if(bU && !bD && bL && !bR){ dir = Direction.LU;}
            else if(bU && !bD && !bL && bR){ dir = Direction.RU;}
            else if(!bU && bD && bL && !bR){ dir = Direction.LD;}
            else if(!bU && bD && !bL && bR){ dir = Direction.RD;}
    }

    public Missile fire(){
            if(!live) return null;

            Missile m = null;
            int x = this.x+Tank.WIDTH/2-Missile.WIDTH/2;
            int y = this.y+Tank.HEIGHT/2-Missile.HEIGHT/2;
            //m = new Missile(x,y,barrelDir);
            m = new Missile(x,y,good,barrelDir);
            Missile.MS.add(m);
            return m;
    }

    public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_CONTROL:
                        //m = fire();
                        //Missile.MS.add(fire());
                        fire();
                        break;
                case KeyEvent.VK_UP:
                        bU=true;
                        break;
                case KeyEvent.VK_DOWN:
                        bD=true;
                        break;
                case KeyEvent.VK_LEFT:
                        bL=true;
                        break;
                case KeyEvent.VK_RIGHT:
                        bR=true;
                        break;
            }
            locateDirection();
    }
    public void keyReleased(KeyEvent e){
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                        bU=false;
                        break;
                case KeyEvent.VK_DOWN:
                        bD=false;
                        break;
                case KeyEvent.VK_LEFT:
                        bL=false;
                        break;
                case KeyEvent.VK_RIGHT:
                        bR=false;
                        break;
            }
            locateDirection();
    }
    public Missile getMissile(){
            return m;
    }
    //public List<Missile> getMissiles(){
    //        return ms;
    //}

    public Rectangle getRect(){
            return new Rectangle(x,y,WIDTH,HEIGHT);
    }
    public void setLive(boolean live){ this.live = live;}
    public boolean isLive(){return live;}
    public boolean isGood(){return good;}

    //public void setDir(Direction dir){ this.dir = dir ;}
    //

    public boolean collidesWith(Wall w){
            if (this.live && this.getRect().intersects(w.getRect())){ 
                    //this.dir = Direction.STOP;
                    //x = oldX;
                    //y = oldY;
                    this.stay();

                    return true;
            }
            return false;
    }
    public boolean collidesWith(Tank t){
            if (this != t && this.getRect().intersects(t.getRect())){ 
                    this.stay();
                    return true;
            }
            return false;
    }
    public void collidesWithTank(){
            for (int i=0;i<TS.size() ;i++ ){
                    Tank t = TS.get(i);
                    this.collidesWith(t);
            } 
    }
    private void stay(){
            x = oldX;
            y = oldY;
    }
}


