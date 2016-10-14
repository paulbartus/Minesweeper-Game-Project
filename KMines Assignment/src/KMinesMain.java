import javax.swing.JFrame;

public class KMinesMain
{
	public static JFrame myFrame;

	public static void main(String[] args) 
	{
		myFrame = new JFrame("KMines Operation");
		
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(350, 375);
		
		KMinesPanel myPanel = new KMinesPanel();
		myFrame.add(myPanel);
		
		KMinesMouseAdapter myMouseAdapter = new KMinesMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}