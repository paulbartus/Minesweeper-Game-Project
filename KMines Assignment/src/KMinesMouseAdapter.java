import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import javax.swing.JFrame;

public class KMinesMouseAdapter extends MouseAdapter 
{
	final int EMPTY = 0;
	final int MINE = -1;
	final int MINEFLAG = -2;
	final int DUMMYFLAG = 2;
	final int CHECKED = 1;
	Color whiteNotChecked = Color.WHITE;
	Color grayChecked = Color.LIGHT_GRAY;
	Color blackMine = Color.BLACK;
	Color redFlag = Color.RED;
	int neighborCount = 0;
	String localMines;

	public void mousePressed(MouseEvent e) 
	{
		switch (e.getButton()%2) 
		{
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) 
			{
				c = c.getParent();
				if (c == null) 
				{
					return;
				}
			}
			JFrame myFrame = (JFrame) c;
			KMinesPanel myPanel = (KMinesPanel) myFrame.getContentPane().getComponent(0);
			Insets myInsets = myFrame.getInsets();
			int x1 = myInsets.left;
			int y1 = myInsets.top;
			e.translatePoint(-x1, -y1);
			int x = e.getX();
			int y = e.getY();
			myPanel.x = x;
			myPanel.y = y;
			myPanel.mouseDownGridX = myPanel.getGridX(x, y);
			myPanel.mouseDownGridY = myPanel.getGridY(x, y);
			myPanel.repaint();
			break;
		case 3:		//Right mouse button
			//Included in the '%2' part of the switch
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}

	public void mouseReleased(MouseEvent e) 
	{
		Component c = e.getComponent();
		while (!(c instanceof JFrame)) 
		{
			c = c.getParent();
			if (c == null) 
			{
				return;
			}
		}
		JFrame myFrame = (JFrame) c;
		KMinesPanel myPanel = (KMinesPanel) myFrame.getContentPane().getComponent(0);  
		//Can also loop among components to find MyPanel
		Insets myInsets = myFrame.getInsets();
		int x1 = myInsets.left;
		int y1 = myInsets.top;
		e.translatePoint(-x1, -y1);
		int x = e.getX();
		int y = e.getY();
		myPanel.x = x;
		myPanel.y = y;
		int gridX = myPanel.getGridX(x, y);
		int gridY = myPanel.getGridY(x, y);

		switch (e.getButton()) 
		{
		case 1:	//Left mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) 
			{
				//Had pressed outside do nothing
			} else {
				if ((gridX == -1) || (gridY == -1)) 
				{
					//Is releasing outside
					//Do nothing	
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) 
					{
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						//On all the grids
						if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINEFLAG || 
								myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == DUMMYFLAG) 
						{
							//Do nothing
						} else {

							if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINE) 
							{
								myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = blackMine;
								myPanel.repaint();
								KMinesGameOver blownMine = new KMinesGameOver();
								blownMine.showGameOverDialog(e);
								//Mine boom
							} else {
								//Initialize NEIGHBOR COUNTS.
								if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == EMPTY) 
								{
									neighborCount = 0;
									if (myPanel.mouseDownGridX>0 && myPanel.mouseDownGridX<8 && 
											myPanel.mouseDownGridY>0 && myPanel.mouseDownGridY<8)
										//Inner squares
									{
										for (int i=-1; i<2; i++) 
										{
											for (int j=-1; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j] 
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==0 && myPanel.mouseDownGridY==0)
										//Top Left
									{
										for (int i=0; i<2; i++) 
										{
											for (int j=0; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==8 && myPanel.mouseDownGridY==0)
										//Top Right
									{
										for (int i=-1; i<1; i++) 
										{
											for (int j=0; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==8 && myPanel.mouseDownGridY==8)
										//Bottom Right
									{
										for (int i=-1; i<1; i++) 
										{
											for (int j=-1; j<1; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==0 && myPanel.mouseDownGridY==8)
										//Bottom Left
									{
										for (int i=0; i<2; i++) 
										{
											for (int j=-1; j<1; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==0 && myPanel.mouseDownGridY>0 && myPanel.mouseDownGridY<8)
										//Left Column
									{
										for (int i=0; i<2; i++) 
										{
											for (int j=-1; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridX==8 && myPanel.mouseDownGridY>0 && myPanel.mouseDownGridY<8)
										//Right Column
									{
										for (int i=-1; i<1; i++) 
										{
											for (int j=-1; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridY==0 && myPanel.mouseDownGridX>0 && myPanel.mouseDownGridX<8)
										//Top Row
									{
										for (int i=-1; i<2; i++) 
										{
											for (int j=0; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									if (myPanel.mouseDownGridY==8 && myPanel.mouseDownGridX>0 && myPanel.mouseDownGridX<8)
										//Bottom Row
									{
										for (int i=-1; i<2; i++) 
										{
											for (int j=-1; j<1; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE || myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
																== MINEFLAG) 
												{
													neighborCount++;
												}
											}
										}
									}
									System.out.println("Neighbor: "+neighborCount);
									myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = grayChecked;
									myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = CHECKED;
								}
							}
						}
						System.out.println("Identity: "+myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY]);
						if (neighborCount != 0) {
							localMines = Integer.toString(neighborCount);
							System.out.println("Local mines: "+localMines);
						}
					}
				}
			}
			myPanel.repaint();
			KMinesGameOver winGame = new KMinesGameOver();
			try {
				winGame.showWinGameDialog(e);
			} catch (MalformedURLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			break;
		case 3:		//Right mouse button 
			if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == EMPTY) 
			{
				myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = redFlag;
				myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = DUMMYFLAG;
				//Empty slot flagged
			} else {
				if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINE) 
				{
					myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = redFlag;
					myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = MINEFLAG;
					//Mine flagged
				} else {
					if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == CHECKED) 
					{
						//Do nothing
					} else {
						if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == DUMMYFLAG) 
						{
							myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = whiteNotChecked;
							myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = EMPTY;
							//Removes flag on empty slot
						} else {
							if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINEFLAG) 
							{
								myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = whiteNotChecked;
								myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = MINE;
								//Remove flag on mine
							}
						}
					}
				}
			}
			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}