import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class KMinesMouseAdapter extends MouseAdapter 
{
	final int EMPTY = 0;
	final int MINE = -1;
	final int FLAG = 1;
	final int FLAGGED = 2;
	final int CHECKED = -2;
	Color blackMine = Color.BLACK;
	Color redFlag = Color.RED;

	public void mousePressed(MouseEvent e) 
	{
		switch (e.getButton()) 
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
			//Do nothing
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
		JFrame myFrame = (JFrame)c;
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
				if ((gridX == -1) || (gridY == -1)) {
					//Is releasing outside
					//Do nothing	
				} else {
					if ((myPanel.mouseDownGridX != gridX) || (myPanel.mouseDownGridY != gridY)) {
						//Released the mouse button on a different cell where it was pressed
						//Do nothing
					} else {
						//Released the mouse button on the same cell where it was pressed
						//On all the grids
						if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == FLAG) {
							//Do nothing
						} else {

							if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINE) {
								myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = blackMine;
								//Mine boom
							} else {
								//Initialize NEIGHBOR COUNTS.
								if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] != MINE ) 
								{
									int neighborCount = 0;
									if (myPanel.mouseDownGridX>0 && myPanel.mouseDownGridX<8 && 
											myPanel.mouseDownGridY>0 && myPanel.mouseDownGridY<8)
										//Inner squares
									{
										for (int i=-1; i<2; i++) 
										{
											for (int j=-1; j<2; j++) 
											{

												if (myPanel.buttons[myPanel.mouseDownGridX+i][myPanel.mouseDownGridY+j]
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
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
														== MINE) 
												{
													neighborCount++;
												}
											}
										}
									}
									System.out.println(neighborCount);
								}
							}
						}
					}

				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button 
			if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == EMPTY) {
				myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = redFlag;
				//Mine boom
			}
			myPanel.repaint();
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}