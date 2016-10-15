import java.awt.Color;
import java.awt.Component;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.net.MalformedURLException;
import java.net.URL;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class KMinesGameOver {

	KMinesMain myframe = new KMinesMain(); 
	JOptionPane gameOver = new JOptionPane();
	public void showGameOverDialog(MouseEvent e) 
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
		
		for (int i=0; i<9; i++) 
		{
			for (int j=0; j<9; j++) 
			{

				if (myPanel.buttons[i][j] == -1)
				{
					myPanel.coverButtons[i][j] = Color.BLACK;
				}
			}
		}
		myPanel.repaint();
		
		Object[] options = {"What do I have to lose?", "I'd rather NOT blow up again"};
		if (JOptionPane.showOptionDialog(gameOver, "Well, you blew yourself up.\nWanna give it another go?", "GAME OVER",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]) == JOptionPane.YES_OPTION)
		{
			KMinesMain.myFrame.dispose();
			KMinesMain.main(null);
		}
		else if (JOptionPane.showOptionDialog(gameOver, "Well, you blew yourself up.\nWanna give it another go?", "GAME OVER",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]) == JOptionPane.NO_OPTION)
		{
			System.exit(0);
		}
		else if (JOptionPane.showOptionDialog(gameOver, "Well, you blew yourself up.\nWanna give it another go?", "GAME OVER",
				JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, null, options, options[0]) == JOptionPane.CLOSED_OPTION) 
		{
			showGameOverDialog(e);
		}
	}
	public void showWinGameDialog(MouseEvent e) throws MalformedURLException 
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
		final ImageIcon icon = new ImageIcon(new URL("http://findicons.com/files/icons/2643/childish/128/childish_thumb_up.png"));
		int winner = 0;
		
		for (int i=0; i<9; i++) 
		{
			for (int j=0; j<9; j++) 
			{

				if (myPanel.buttons[i][j] == 0 || myPanel.buttons[i][j] == 2)
				{
					winner ++;
				}
			}
		}
		if (winner == 0) 
		{									
			for (int i=0; i<9; i++) 
			{
				for (int j=0; j<9; j++) 
				{

					if (myPanel.buttons[i][j] == -1 || myPanel.buttons[i][j] == -2)
					{
						myPanel.coverButtons[i][j] = Color.RED;
					}
				}
			}
			myPanel.repaint();
			
			Object[] options = {"Bring it on!", "Nah, those mines need a break"};
			if (JOptionPane.showOptionDialog(gameOver, "Nice job! Those mines didn't stand a chance!\nWanna give it another go?", "GAME WON",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon, options, options[0]) == JOptionPane.YES_OPTION)
			{
				KMinesMain.myFrame.dispose();
				KMinesMain.main(null);
			}
			else if (JOptionPane.showOptionDialog(gameOver, "Nice job! Those mines didn't stand a chance!\nWanna give it another go?", "GAME WON",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon, options, options[0]) == JOptionPane.NO_OPTION)
			{
				System.exit(0);
			}
			else if (JOptionPane.showOptionDialog(gameOver, "Nice job! Those mines didn't stand a chance!\nWanna give it another go?", "GAME WON",
					JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE, icon, options, options[0]) == JOptionPane.CLOSED_OPTION) 
			{
				showWinGameDialog(e);
			}
		}
	}
}