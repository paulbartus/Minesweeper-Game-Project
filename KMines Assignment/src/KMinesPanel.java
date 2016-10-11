import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JPanel;

public class KMinesPanel extends JPanel 
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
	Color[][] coverButtons = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	int[][] buttons = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	int[][] mines = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	int[][] counts = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	int MINE = -1;
	
	private Random generator = new Random();
	public void randomMines() {
		for (int i=0; i<8; i++) {
			int choice = generator.nextInt(9);
			int Kchoice = generator.nextInt(9);
//			buttons[choice][Kchoice] = MINE;
			buttons[5][5] = MINE;
			buttons[3][3] = MINE;
			buttons[4][3] = MINE;
		}
	}
	
	public KMinesPanel() {   //This is the constructor... this code runs first to initialize
		//  KEVINIIIIIIIIIIIIIIIIIIIIINNN....  pienso que no se necesitan los "if"s
		if (INNER_CELL_SIZE + (new Random()).nextInt(1) < 1) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (new Random()).nextInt(1) < 2) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (new Random()).nextInt(1) < 3) {	//Use of "random" to prevent unwanted Eclipse warning
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   // All of the Grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				coverButtons[x][y] = Color.WHITE; 
			}
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) {   // All of the Grid
			for (int y = 0; y < TOTAL_ROWS; y++) {
				buttons[x][y] = 0; 
			}
		}
		randomMines();
	}
	
	public void paintComponent(Graphics g) 
	{
		super.paintComponent(g);

		//Compute interior coordinates
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		int x2 = getWidth() - myInsets.right - 1;
		int y2 = getHeight() - myInsets.bottom - 1;
		int width = x2 - x1;
		int height = y2 - y1;

		//Paint the background
		g.setColor(Color.GRAY);
		g.fillRect(x1, y1, width + 1, height + 1);

		//Draw the grid minus the bottom row (which has only one cell)
		//By default, the grid will be 9x9 (see above: TOTAL_COLUMNS and TOTAL_ROWS) 
		g.setColor(Color.BLUE);
		for (int y = 0; y <= TOTAL_ROWS; y++) 
		{
			g.drawLine(x1 + GRID_X, 
					y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)), 
					x1 + GRID_X + ((INNER_CELL_SIZE + 1) * TOTAL_COLUMNS), 
					y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)));
		}
		for (int x = 0; x <= TOTAL_COLUMNS; x++) 
		{
			g.drawLine(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), 
					y1 + GRID_Y,
					x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)), 
					y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));     //Aqui le borr� el - 1 de "TOTAL_ROWS - 1"
		}

//		Draw an additional cell at the bottom left
//		g.drawRect(x1 + GRID_X, y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS - 1)), INNER_CELL_SIZE + 1, INNER_CELL_SIZE + 1);

		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) {
			for (int y = 0; y < TOTAL_ROWS; y++) {
//				if ((x == 0) || (y != TOTAL_ROWS - 1)) {  // esto era lo que ten�a antes
				if ((x == 0) || (y != TOTAL_ROWS)) {
					Color c = coverButtons[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				}
			}
		}
	}
	
	public int getGridX(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
//		if (x == 0 && y == TOTAL_ROWS) {    //The lower left extra cell
//			return x;
//		}
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;				// Aqui fue:  ten�a anteriormente "TOTAL_ROWS - 2"
		}
		return x;
	}
	
	public int getGridY(int x, int y) {
		Insets myInsets = getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		x = x - x1 - GRID_X;
		y = y - y1 - GRID_Y;
		if (x < 0) {   //To the left of the grid
			return -1;
		}
		if (y < 0) {   //Above the grid
			return -1;
		}
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) {   //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
//		if (x == 0 && y == TOTAL_ROWS - 1) {    //The lower left extra cell
//			return y;
//		}
		if (x < 0 || x > TOTAL_COLUMNS || y < 0 || y > TOTAL_ROWS) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}
