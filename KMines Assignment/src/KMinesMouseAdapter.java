import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

import javax.swing.JFrame;

public class KMinesMouseAdapter extends MouseAdapter 
{
	private Random generator = new Random();
	Color newColor = null;
	Color otherColor = null;
	final int MINE =-1;
	Color blackMine = Color.BLACK;
//	public void randomNumbers() {
//		for (int i=0; i<10; i++) {
//			for (int j=0; j<10; j++) {
//				int choice = generator.nextInt(9);
//				int Kchoice = generator.nextInt(9);
//			}
//		}
//	}


	//provide distinct variables with color to 
	//	avoid the repetitions of color when clicked a grid 
	
	public void RandomMinesCount(MouseEvent e) {
//		ArrayList<Integer> list = new ArrayList <Integer>();
//		Component c = e.getComponent();
//		while (!(c instanceof JFrame)) {
//			c = c.getParent();
//			if (c == null) {
//				return;
//			}
//		}
//		JFrame myFrame = (JFrame) c;
//		KMinesPanel myPanel = (KMinesPanel) myFrame.getContentPane().getComponent(0);
////		Insets myInsets = myFrame.getInsets();
////		int x1 = myInsets.left;
////		int y1 = myInsets.top;
////		e.translatePoint(-x1, -y1);
////		int x = e.getX();
////		int y = e.getY();
////		myPanel.x = x;
////		myPanel.y = y;
//		//Initialize list of random pairs
//		for (int i=0; i<myPanel.counts.length; i++) {
//			for (int j=0; j<myPanel.counts.length; j++) {
//				list.add(i*100+j);
//			}
//		}
//		//pickup Random MINES
//		myPanel.counts = new int[9][9];
//		for (int i=0; i<10; i++) {
//			int choice = (int) (Math.random()*10);
//			myPanel.counts[list.get(choice)/100][list.get(choice)%100] = MINE;
//			list.remove(choice);  //Removes the element at the specified position in this list.
//		}
//		//Initialize NEIGHBOR COUNTS.
//		for (int i=0; i<myPanel.counts.length; i++) {
//			for (int j=0; j<myPanel.counts.length; j++) {
//				if (myPanel.counts[i][j] != MINE) {
//					int neighborCount = 0;
//					if (i>0 && j>0 && myPanel.counts[i-1][j-1] == MINE) {   //Up Left
//						neighborCount++;
//					}
//					if (j>0 && myPanel.counts[i][j-1] == MINE) {   //Up
//						neighborCount++;
//					}
//					if (i<myPanel.counts.length - 1 && j<myPanel.counts.length - 1 && myPanel.counts[i+1][j+1] == MINE) { //Down Right
//						neighborCount++;
//					}
//					myPanel.counts[i][j] = neighborCount;
//				}
//			}
//		}
//		for (int i=0; i<myPanel.buttons.length; i++) {
//			for (int j=0; j<myPanel.buttons.length; j++) {
////				if (e.getSource().equals(myPanel.buttons[x][y])) {   // Kevin...  Nosé si está bién
//					myPanel.buttons[i][j] = Color.GREEN;
////				}
//			}
//		}
	}

	public void mousePressed(MouseEvent e) {
		switch (e.getButton()) {
		case 1:		//Left mouse button
			Component c = e.getComponent();
			while (!(c instanceof JFrame)) {
				c = c.getParent();
				if (c == null) {
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
		while (!(c instanceof JFrame)) {
			c = c.getParent();
			if (c == null) {
				return;
			}
		}
		JFrame myFrame = (JFrame)c;
		KMinesPanel myPanel = (KMinesPanel) myFrame.getContentPane().getComponent(0);  //Can also loop among components to find MyPanel
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
		
		switch (e.getButton()) {
		case 1:	//Left mouse button
			if ((myPanel.mouseDownGridX == -1) || (myPanel.mouseDownGridY == -1)) {
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
						
//						ArrayList<Integer> list = new ArrayList <Integer>();
						//Initialize list of random pairs
//						for (int i=0; i<myPanel.counts.length; i++) {
//							for (int j=0; j<myPanel.counts.length; j++) {
//								list.add(i*100+j);
//							}
//						}
//						//pickup Random MINES
////						myPanel.counts = new int[9][9];
//						for (int i=0; i<10; i++) {
//							int choice = (int) (Math.random()*10);
//							myPanel.mines[list.get(choice)/100][list.get(choice)%100] = MINE;
//							list.remove(choice);  //Removes the element at the specified position in this list.
//						}
//						for (int i=0; i<2; i++) {
//							int choice = generator.nextInt(9);
//							int Kchoice = generator.nextInt(9);
//							myPanel.buttons[choice][Kchoice] = MINE;
//						}
						//Pickup random Mines
						
//						for (int i=0; i<3; i++) {
//							int choice = generator.nextInt(9);
//							int Kchoice = generator.nextInt(9);
//							myPanel.buttons[choice][Kchoice] = MINE;
////							for (int j=0; j<myPanel.buttons.length; j++) {
//								
////							}
//						}
						if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINE) {
							myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = blackMine;
						}
						
						//Initialize NEIGHBOR COUNTS.
						for (int i=0; i<myPanel.buttons.length; i++) {
							for (int j=0; j<myPanel.buttons.length; j++) {
								if (myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] != MINE) {
									int neighborCount = 0;
									if (i>0 && j>0 && myPanel.buttons[i-1][j-1] == MINE) {   //Up Left
										neighborCount++;
										myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = neighborCount;
									}
									else if (j>0 && myPanel.buttons[i][j-1] == MINE) {   //Up
										neighborCount++;
										myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = neighborCount;
									}
//									else if (i<myPanel.coverButtons.length - 1 && j<myPanel.coverButtons.length - 1 && myPanel.buttons[myPanel.mouseDownGridX-1][myPanel.mouseDownGridY-1] == MINE) { //Down Right
//										neighborCount++;
//										myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.YELLOW;
//									}
//									else if (i<myPanel.coverButtons.length - 1 && j<myPanel.coverButtons.length - 1 && myPanel.buttons[myPanel.mouseDownGridX+1][myPanel.mouseDownGridY+1] == MINE) { //Up Left
//										neighborCount++;
//										myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.GREEN;
//									}
									myPanel.buttons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = neighborCount;
								}
							}
						}
//						for (int i=0; i<myPanel.coverButtons.length; i++) {
//							for (int j=0; j<myPanel.coverButtons.length; j++) {
//								if (myPanel.mines[myPanel.mouseDownGridX][myPanel.mouseDownGridY] == MINE) {
//									myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = Color.BLACK;
//								}
//							}
//						}
						
//						newColor = Color.RED;
//						if ((myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY]).equals(newColor)) {
//							newColor = Color.CYAN;
//						}
//						myPanel.coverButtons[myPanel.mouseDownGridX][myPanel.mouseDownGridY] = newColor;
//						myPanel.repaint();

					}
				}
			}
			myPanel.repaint();
			break;
		case 3:		//Right mouse button 
			//Click out the grid with right click
			
			break;
		default:    //Some other button (2 = Middle mouse button, etc.)
			//Do nothing
			break;
		}
	}
}