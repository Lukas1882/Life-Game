import java.awt.Color;
import java.awt.Graphics;
/**
 * This is the class of my circle button(red one).
 * @author Yaolong Li
 * @version     1.0
 */
@SuppressWarnings("serial")
public class MyButton2 extends MyButton1{
	
	/* 
	 * Resets the color of the button.
	 */
	protected void paintComponent(Graphics g) {  
        if (getModel().isArmed()) {  
            g.setColor(Color.YELLOW);  
        } else {  
            g.setColor(Color.red);  
        }  
        g.fillOval(0, 0, getSize().width - 1, getSize().height - 1);  
        super.paintComponents(g);  
    } 
}
