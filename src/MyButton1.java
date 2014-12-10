
import java.awt.*;  
import java.awt.event.MouseEvent;  
import java.awt.geom.Ellipse2D;  
import javax.swing.JButton;  
 
/**
 * This is the class of my circle button(green one). 
 * @author Yaolong Li
 * @version     1.0
 */
public class MyButton1 extends JButton { 
	// to store in the info about the shape
    private Shape shape = null;      
    public  MyButton1() {          
        this.addMouseListener(new java.awt.event.MouseAdapter(){   
            public void mouseEntered(MouseEvent e) {  
                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.HAND_CURSOR));  
            }  
            public void mouseExited(MouseEvent e) {  
                ((JButton)e.getSource()).setCursor(new Cursor(Cursor.MOVE_CURSOR));  
            }  
        });        
        Dimension size = getPreferredSize();
        // Changes the size of the button.
        size.width = size.height =20;  
        setPreferredSize(size);  
        setContentAreaFilled(false);  
    }  
    
    /* (non-Javadoc)
     * Paints the button.
     */
    protected void paintComponent(Graphics g) {  
        if (getModel().isArmed()) {  
            g.setColor(Color.YELLOW);  
        } else {  
            g.setColor(Color.green);  
        }  
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);  
        super.paintComponents(g);  
    }  
    protected void paintBorder(Graphics g) {  
        g.setColor(getForeground());  
        g.drawOval(0, 0, getSize().width - 1, getSize().height - 1);  
    }  
    
    /**
     * Checks if the mouse is on the top.
     */
    public boolean contains(int x, int y) {    
        if ((shape == null) || (!shape.getBounds().equals(getBounds()))) {  
            shape = new Ellipse2D.Float(0, 0, getWidth(), getHeight());  
        }  
        return shape.contains(x, y);  
    }  
   
}  