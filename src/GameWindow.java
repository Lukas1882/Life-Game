import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import javax.swing.*;
/**
 * This class deals the whole frame.
 * @author Yaolong Li
 * @version     1.0
 */
@SuppressWarnings("serial")
public class GameWindow extends JFrame {
	private int row,col;
	private  int [][]myCells;	
	private CellPanel cellsPix;
	private TopPanel topPanel;
	private CellGrow newCells;
	public GameWindow(int row,int col){
		myCells=new int[row][col];	
		this.row=row;  
		this.col=col;
		// Sets the panel and the frame.
		panelSetting();
		frameSetting();		
	}
	
	/**
	 *  Sets the frame.
	 */
	public void frameSetting(){
		setSize(myWidth(col),120+25*row);
		this.setLocation(800, 50);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		setResizable(false);
		this.setTitle("LifeGame v3.0");   
	}
	
	/**
	 * Sets the panel.
	 */
	public void panelSetting(){
		topPanel=new TopPanel(row,col);
        topPanel.setBounds(0,0,myWidth(col), 80);  
        topPanel.setBackground(Color.yellow);	
		this.add(topPanel,BorderLayout.NORTH);
	    cellsPix=new CellPanel(row,col);
		cellsPix.setBackground(Color.cyan);
		this.add(cellsPix);
	}
	
	/**
	 * Helps the class to resize of cell matrix world.
	 */
	public void changeCells(int row, int col){
		if (myCells[row-1][col-1]==0){
				myCells[row-1][col-1]=1;
			} else{
				myCells[row-1][col-1]=0;
			}				
	}
	
	/**
	 * Remove the old panel and get the new one.
	 */
	public void reBuild(){
		   this.remove(cellsPix);
		   cellsPix=new CellPanel(row,col);				   
		   this.add(cellsPix);
		   this.setVisible(true);
	   }
    
    /**
     * Sets the new size of the frame.
     */
    public void reSize(int row,int col){	
    	myCells=new int[row][col];
    	this.row=row;
    	this.col=col;
    	this.remove(cellsPix);
    	this.remove(topPanel);
    	this.panelSetting();
    	this.frameSetting();
    }
    
    
    /**
     * Gets the update cell world.
     */
    public void nextGeneration(){
    	newCells=new CellGrow(row, col, myCells);
		myCells=newCells.getMyCells();
		reBuild();
    }
    
    /**
     * Returns the value of the width in case of the row number.
     */
    public int myWidth(int row){
    	if (row>9) {
    		return (row+1)*25;
    	}  	else
    		return 250;    	  
    }
  
	/**
	 * This inner class sets the top panel in the frame.
	 */
	private class TopPanel  extends JPanel implements ActionListener{
		private JTextField jtf1;
		private JTextField jtf2;
		public TopPanel(int row, int col){
			panelSetting();	
		}
		
		/**
		 * Sets this panel.
		 */
		public void panelSetting(){
			JPanel subPanel1=new JPanel();
			JPanel subPanel2=new JPanel();			
			JLabel jlb1=new JLabel("Row");
			JLabel jlb2=new JLabel("Column");
			JButton jb1=new JButton("Step");	
			
		    jtf1=new JTextField(String.valueOf(row),3);	
			jtf2=new JTextField(String.valueOf(col),3);
			jb1.addActionListener(this);
			jb1.setActionCommand("step");
			jlb1.setForeground(Color.blue);			
			jlb2.setForeground(Color.blue);			
			subPanel1.add(jlb1);
			subPanel1.add(jtf1);
			subPanel1.add(jlb2);
		    subPanel1.add(jtf2);
		    subPanel2.add(jb1);
		    this.setLayout(new GridLayout(2,1));
			this.add(subPanel1);
			this.add(subPanel2);			
		}

		/**
		 * Sets the action when certain button is kicked.
		 */
		@Override  
		public void actionPerformed(ActionEvent e) {
			if (e.getActionCommand().equals("step"))
			{
				if (Integer.parseInt(jtf1.getText())==row && Integer.parseInt(jtf2.getText())==col){
					nextGeneration();
				} else {
					reSize(Integer.parseInt(jtf1.getText()),Integer.parseInt(jtf2.getText()));
				}				
			}							
		} 
	}
	
	
	/**
	 * this inner class sets the cells world.
	 */
	private class CellPanel extends JPanel{		
		public CellPanel (int row ,int col){			
			putCells(row,col);			
		}		
		public void putCells(int row ,int col){			
			this.setLayout(new GridLayout(row,1));
			// For each row, a new panel will be created.
			for (int i=0;i<row;i++){
				OneRow oneInRow=new OneRow(col,i);			
				this.add(oneInRow);	           
			}			
		}
		
		/**
		 * This class sets one row cells(buttons) in the cells world.
		 */
		private class OneRow extends JPanel  implements ActionListener{
			public OneRow(int col,int thisRow){
				ArrayList<MyButton1>bottons=new ArrayList<>();
				// When the button kicked, the color can be different in the case of the value of the matrix.
				for (int i=0;i<col;i++){
					if (myCells[thisRow][i]==0){						
						MyButton1 oneCell=new MyButton1();
						bottons.add(oneCell);
					}  
					else {
						MyButton2 oneCell=new MyButton2();
						bottons.add(oneCell);
					}
					this.setBackground(Color.cyan);				
					this.add(bottons.get(i));
					bottons.get(i).addActionListener(this);
					bottons.get(i).setActionCommand(String.valueOf(thisRow+1)+";"+String.valueOf(i+1));					
				}
			}
			
			@Override
			public void actionPerformed(ActionEvent e) {
				String[]cellInfo=e.getActionCommand().split(";");
				// When one button is kicked, the cells world can get updated.
				changeCells(Integer.parseInt(cellInfo[0]),Integer.parseInt(cellInfo[1]));
				// Rebuild the frame of panel.
				reBuild();				
			}
		}
	}
}
