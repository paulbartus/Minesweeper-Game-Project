import javax.swing.JFrame;

public class KMinesMain
{
	public static void main(String[] args) {
		JFrame myFrame = new JFrame("Color Grid");
		
		myFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		myFrame.setLocation(400, 150);
		myFrame.setSize(340, 350);

		KMinesPanel myPanel = new KMinesPanel();
		myFrame.add(myPanel);

		KMinesMouseAdapter myMouseAdapter = new KMinesMouseAdapter();
		myFrame.addMouseListener(myMouseAdapter);

		myFrame.setVisible(true);
	}
}