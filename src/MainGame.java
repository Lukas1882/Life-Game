import java.util.Scanner;


/**
 * This is the main class my program.
 * @author Yaolong Li
 * @version  1.0
 */
public class MainGame {
	private static int row,column;
	
	public static void main(String[] args){	    
	    command();
		GameWindow myWindow=new GameWindow(row,column);
	}
	
	
	/**
	 * Prints the words in the console.
	 */
	public  static void command(){
		System.out.println("Please give me the number of row:");
	    Scanner in = new Scanner(System.in);
		row= Integer.valueOf(in.nextLine());
		System.out.println("Please give me the number of column:");
		column= Integer.valueOf(in.nextLine());
	}
}
