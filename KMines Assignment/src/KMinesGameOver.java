//import javax.swing.JDialog;
//import javax.swing.JFrame;
import javax.swing.JOptionPane;
//import java.awt.Window;

public class KMinesGameOver {
	
	KMinesMain myframe = new KMinesMain(); 
	JOptionPane gameOver = new JOptionPane();
	public void showGameOverDialog() 
	{
		Object[] options = {"Bring it on!", "I'd rather not blow up again."};
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
			showGameOverDialog();
		}
//		JOptionPane gameOver = new JOptionPane(
//                "Well, you blew yourself up.\nWanna give it another go?",
//                JOptionPane.ERROR_MESSAGE, JOptionPane.YES_NO_OPTION, null, options, options[0]);
//		JDialog gameOverDialog = new JDialog(myFrame, "GAME OVER", true);
//		gameOverDialog.setContentPane(gameOver);
//		gameOverDialog.setDefaultCloseOperation(JDialog.EXIT_ON_CLOSE);
//		int choice = (int) gameOver.getValue();
//		if (choice == JOptionPane.YES_OPTION) {
//			System.out.println("Good.");
//		}
//		else if (choice == JOptionPane.NO_OPTION) {
//			System.exit(0);
//		}
	}	
}