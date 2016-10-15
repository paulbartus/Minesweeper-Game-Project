//import java.awt.Color;
//import java.awt.Font;
//import java.awt.Graphics;
//import java.awt.Insets;
//import javax.swing.JPanel;
//
//public class KMinesNeighbors extends JPanel
//{
//	private static final long serialVersionUID = 1L;
//
//	public void neighborPainter(Graphics g)
//	{
//		KMinesPanel buttons = new KMinesPanel();
//		for (int i=0; i<9; i++)
//		{
//			for (int j=0; j<9; j++)
//			{
//				if (buttons.buttons[i][j] == 1 || buttons.neighborButtons[i][j] != 0)
//				{
//					super.paintComponent(g);
//
//					String localMines = Integer.toString(buttons.neighborButtons[i][j]);
//					Insets myInsets = getInsets();
//					int x1 = myInsets.left;
//					int y1 = myInsets.top;
//
//					g.setColor(Color.BLUE);
//					g.setFont(new Font("TimesRoman", Font.BOLD, 10));
//					g.drawString(localMines, x1+(i*(buttons.INNER_CELL_SIZE+1))+15, y1+(j*(buttons.INNER_CELL_SIZE+1))+15);
//
//					repaint();
//				}
//			}
//		}
//	}
//}
