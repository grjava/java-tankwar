import java.awt.*;

class Wall {
        private int x,y,w,h;
        
        public Wall(int x,int y,int w,int h){
                this.x = x;
                this.y = y;
                this.w = w;
                this.h = h;
        }

        public void draw(Graphics g){
                Color c = g.getColor();
                g.setColor(Color.BLACK);
                g.fillRect(x,y,w,h);
                g.setColor(c);
        }

        public Rectangle getRect(){
                return new Rectangle(x,y,w,h);
        }

}
