import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JPanel;

public class otherMines extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	private static final int GRID_X = 25;
	private static final int GRID_Y = 25;
	private static final int INNER_CELL_SIZE = 29;
	private static final int TOTAL_COLUMNS = 9;
	private static final int TOTAL_ROWS = 9;   //Last row has only one cell
	
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	Color[][] countsAmines = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	int[][] counts = new int[TOTAL_COLUMNS][TOTAL_ROWS];
}