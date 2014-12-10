
/**
 * This class deals with the change of the cells in int array.
 * @author Yaolong Li
 * @version     1.0
 */
public class CellGrow {
	int [][]myCells;
	public CellGrow(int row,int col,int[][]cells){
		myCells=futureCell( row, col,newCell (row,col,cells));		
	}	
	
	/**
	 * Change the original array into a bigger one.
	 * @param cells  the original array
	 * @return
	 */
	public int[][]newCell(int row,int col,int[][]cells){
		int[][] bigCells=new int[row+2][col+2];
		for (int x=1;x<row+1;x++){
			for (int y=1;y<col+1;y++){
				bigCells[x][y]=cells[x-1][y-1];				
			}
		}		
		return bigCells;
	}
	
	
	
	/**
	 * Gets the next generation of the cell matrix.
	 * @param bigCells    the cells which will be changed into a next generation
	 * @return
	 */
	public int [][] futureCell(int row,int col,int[][]bigCells){
		int[][]tempCells=new int[row][col];
		for (int i=1;i<row+1;i++)                                                    /** Gets the future matrixes and add them into sum*/
        {
            for (int j=1;j<col+1;j++)
                 {	                                                                /** level means the number of alive neighbor cell*/
                     int level=bigCells[i-1][j]+bigCells[i+1][j]+bigCells[i][j+1]+bigCells[i][j-1]+bigCells[i-1][j-1]+bigCells[i-1][j+1]+bigCells[i+1][j+1]+bigCells[i+1][j-1];
                     if (level==3 )
                    {                    	 
                    	 tempCells[i-1][j-1]=1;                                       /** "1" means next generation this cell will be alive*/
                    }else if ( bigCells[i][j]==1 && level==2)
                    {	                    	
                    	tempCells[i-1][j-1]=1; 	                      
                    } else
                    {	                    	                        
                    	tempCells[i-1][j-1]=0;                                        /**otherwise, "0" means dead*/
                    }                     
                }	           
        }
		return tempCells;		
	}
	
	/**
	 * @return   the result of the next generation.
	 */
	public int[][] getMyCells() {
		return myCells;
	}
}
