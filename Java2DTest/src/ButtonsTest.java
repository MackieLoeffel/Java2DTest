
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;



public class ButtonsTest extends JFrame
{
	public ButtonsTest()
	{
		setTitle("Buttons!");
		setSize(800, 800);
		
		JPanel p = new JPanel(null);
		
		add(p);
		
		JButton b1 = new JButton();
		b1.setIcon(new ImageIcon("E:\\Daten\\Bilder\\genja.jpg"));
		b1.setBounds(100, 100, 100, 100);
		
		p.add(b1);
	}
	
	
	public static void main(String[] args)
	{
		ButtonsTest b = new ButtonsTest();
		b.setVisible(true);
	}

}
