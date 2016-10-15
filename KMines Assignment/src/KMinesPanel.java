import java.awt.Color;
import java.awt.Graphics;
import java.awt.Insets;
import java.util.Random;
import javax.swing.JPanel;

public class KMinesPanel extends JPanel 
{
	private static final long serialVersionUID = 1L;
	
	public static final int GRID_X = 25;
	public static final int GRID_Y = 25;
	public final int INNER_CELL_SIZE = 29;
	public static final int TOTAL_COLUMNS = 9;
	public static final int TOTAL_ROWS = 9;
	public int x = -1;
	public int y = -1;
	public int mouseDownGridX = 0;
	public int mouseDownGridY = 0;
	Color[][] coverButtons = new Color[TOTAL_COLUMNS][TOTAL_ROWS];
	int[][] buttons = new int[TOTAL_COLUMNS][TOTAL_ROWS];
	public String[][] neighborButtons = new String[TOTAL_COLUMNS][TOTAL_ROWS];
	int EMPTY = 0;
	int MINE = -1;
	int MINEFLAG = -2;
	int DUMMYFLAG = 2;
	int CHECKED = 1;
	private Random generator = new Random();
	
	public KMinesPanel() //This is the constructor... this code runs first to initialize
	{
		if (INNER_CELL_SIZE + (generator).nextInt(1) < 1) //Use of "random" to prevent unwanted Eclipse warning
		{
			throw new RuntimeException("INNER_CELL_SIZE must be positive!");
		}
		if (TOTAL_COLUMNS + (generator).nextInt(1) < 2) //Use of "random" to prevent unwanted Eclipse warning
		{
			throw new RuntimeException("TOTAL_COLUMNS must be at least 2!");
		}
		if (TOTAL_ROWS + (generator).nextInt(1) < 3) //Use of "random" to prevent unwanted Eclipse warning
		{
			throw new RuntimeException("TOTAL_ROWS must be at least 3!");
		}
		for (int x = 0; x < TOTAL_COLUMNS; x++) // All of the Grid
		{
			for (int y = 0; y < TOTAL_ROWS; y++) 
			{
				coverButtons[x][y] = Color.WHITE; 
			}
		}
		randomMines();
		neighborCounter();
	}
	
	public void randomMines() 
	{
		for (int x = 0; x < TOTAL_COLUMNS; x++) // All of the Grid
		{
			for (int y = 0; y < TOTAL_ROWS; y++) 
			{
				buttons[x][y] = EMPTY;
				neighborButtons[x][y] = "";
			}
		}
		for (int i=0; i<10; i++) 
		{
			int xMine = generator.nextInt(9);
			int yMine = generator.nextInt(9);
			buttons[xMine][yMine] = MINE;
//			buttons[5][5] = MINE;
//			buttons[3][3] = MINE;
//			buttons[4][3] = MINE;
//			buttons[7][5] = MINE;
//			buttons[2][7] = MINE;
		}
	}
	
	public String[][] neighborCounter()
	{
		for (int x=0; x<9; x++)
		{
			for (int y=0; y<9; y++) 
			{
				int neighborCount = 0;
				if (buttons[x][y] == EMPTY)
				{
					if (x>0 && x<8 && y>0 && y<8)
					//Inner squares
					{
						for (int i=-1; i<2; i++) 
						{
							for (int j=-1; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==0 && y==0)
						//Top Left
					{
						for (int i=0; i<2; i++) 
						{
							for (int j=0; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==8 && y==0)
						//Top Right
					{
						for (int i=-1; i<1; i++) 
						{
							for (int j=0; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==8 && y==8)
						//Bottom Right
					{
						for (int i=-1; i<1; i++) 
						{
							for (int j=-1; j<1; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==0 && y==8)
						//Bottom Left
					{
						for (int i=0; i<2; i++) 
						{
							for (int j=-1; j<1; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==0 && y>0 && y<8)
						//Left Column
					{
						for (int i=0; i<2; i++) 
						{
							for (int j=-1; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (x==8 && y>0 && y<8)
						//Right Column
					{
						for (int i=-1; i<1; i++) 
						{
							for (int j=-1; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (y==0 && x>0 && x<8)
						//Top Row
					{
						for (int i=-1; i<2; i++) 
						{
							for (int j=0; j<2; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (y==8 && x>0 && x<8)
						//Bottom Row
					{
						for (int i=-1; i<2; i++) 
						{
							for (int j=-1; j<1; j++) 
							{

								if (buttons[x+i][y+j] == MINE || buttons[x+i][y+j] == MINEFLAG) 
								{
									neighborCount++;
								}
							}
						}
					}
					if (neighborCount>0)
					{
					neighborButtons[x][y] = neighborCount+"";
					} else {
						if (neighborCount==0)
						{
							neighborButtons[x][y] = "";
						}
					}
				}
			}
		}
		return neighborButtons;
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
					y1 + GRID_Y + ((INNER_CELL_SIZE + 1) * (TOTAL_ROWS)));
		}

		//Paint cell colors
		for (int x = 0; x < TOTAL_COLUMNS; x++) 
		{
			for (int y = 0; y < TOTAL_ROWS; y++) 
			{
				if ((x == 0) || (y != TOTAL_ROWS)) 
				{
					Color c = coverButtons[x][y];
					g.setColor(c);
					g.fillRect(x1 + GRID_X + (x * (INNER_CELL_SIZE + 1)) + 1, y1 + GRID_Y + (y * (INNER_CELL_SIZE + 1)) + 1, INNER_CELL_SIZE, INNER_CELL_SIZE);
				}
				if (buttons[x][y] == CHECKED)
				{
					
					neighborButtons = neighborCounter();
					g.setColor(Color.BLACK);
					g.drawString(neighborButtons[x][y], x*(INNER_CELL_SIZE+1) + 38, (y*30)+44);
				}
			}
		}
	}
	
	public int getGridX(int x, int y) 
	{
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
		if ((x % (INNER_CELL_SIZE + 1) == 0) || (y % (INNER_CELL_SIZE + 1) == 0)) { //Coordinate is at an edge; not inside a cell
			return -1;
		}
		x = x / (INNER_CELL_SIZE + 1);
		y = y / (INNER_CELL_SIZE + 1);
		if (x < 0 || x > TOTAL_COLUMNS - 1 || y < 0 || y > TOTAL_ROWS - 1) {   //Outside the rest of the grid
			return -1;
		}
		return x;
	}

	public int getGridY(int x, int y) 
	{
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
		if (x < 0 || x > TOTAL_COLUMNS || y < 0 || y > TOTAL_ROWS) {   //Outside the rest of the grid
			return -1;
		}
		return y;
	}
}
