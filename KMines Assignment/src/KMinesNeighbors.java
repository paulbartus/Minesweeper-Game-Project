public class KMinesNeighbors extends KMinesPanel
{
	private static final long serialVersionUID = 1L;

	public int[][] neighborCounter()
	{
		int neighborCount = 0;
		for (int x=0; x<9; x++)
		{
			for (int y=0; y<9; y++) 
			{
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
					neighborButtons[x][y] = neighborCount;
				}
			}
		}
		return neighborButtons;
	}
}