import java.awt.*;
import java.awt.event.*;

class TankWar extends Frame {

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
        }

        public void paint(Graphics g){
                Color c = g.getColor();
                g.setColor(Color.RED);
                g.fillOval(50,50,30,30);
                g.setColor(c);

        }

        public static void main (String [] args) {
                new TankWar();
        }
}
