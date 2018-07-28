import java.awt.*;
import java.awt.event.*;

class Tank {
    private int x,y;
    private int speed = 1 ;

    public Tank(int x,int y){
            this.x = x;
            this.y = y;
    }

    public void draw(Graphics g){
            Color c = g.getColor();
            g.setColor(Color.RED);
            g.fillOval(x,y,30,30);
            g.setColor(c);
    }

    public void keyPressed(KeyEvent e){
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_UP:
                        y -= speed;
                        break;
                case KeyEvent.VK_DOWN:
                        y += speed;
                        break;
                case KeyEvent.VK_LEFT:
                        x -= speed;
                        break;
                case KeyEvent.VK_RIGHT:
                        x += speed;
                        break;
            }
    }
}
