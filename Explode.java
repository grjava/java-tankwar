import java.awt.*;
import java.awt.event.*;

class Explode {

        int x,y;
        int[] diameter = {4,6,10,15,20,30,35,20,14,5};
        boolean live = true ;
        int step = 0;

        public Explode(int x,int y){
                this.x = x;
                this.y = y;
        }

        public void draw(Graphics g){
                if (!live){ return;}
                if(step >= diameter.length){
                        live = false;
                        step = 0 ;
                        return;
                }
                Color c = g.getColor();
                g.setColor(Color.ORANGE);
                g.fillOval(x,y,diameter[step],diameter[step]);
                g.setColor(c);

                step++;
        }
        
} 
