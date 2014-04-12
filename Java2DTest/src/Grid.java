import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class SurfaceG extends JPanel implements ActionListener
{

	private final int cellWidth;
	private final int cellHeight;
	private final int gridWidth;
	private final int gridHeight;

	private boolean[][] cells;
    private Timer timer;

	public SurfaceG(int gridWidth, int gridHeight, int cellWidth, int cellHeight)
	{

		this.cellWidth = cellWidth;
		this.cellHeight = cellHeight;
		this.gridWidth = gridWidth;
		this.gridHeight = gridHeight;

		cells = new boolean[gridHeight][gridWidth];

		setBackground(Color.WHITE);
		
		addMouseListener(new HitTestAdapter());
		
		initTimer();
	}
	
    private void initTimer() {
        
        timer = new Timer(30, this);
        timer.setInitialDelay(190);
        timer.start();        
    }

	private void doDrawing(Graphics g)
	{

		Graphics2D g2 = (Graphics2D) g;
		
		Point loc = getLocation();

		for (int y = 0; y < gridHeight; y++)
		{
			for (int x = 0; x < gridWidth; x++)
			{
				int realX = x * cellWidth + loc.x;
				int realY = y * cellHeight + loc.y;


				if (cells[y][x])
					g2.setColor(Color.blue);
				else
					g2.setColor(Color.cyan);

				g2.fillRect(realX, realY, cellWidth, cellHeight);
				
				g2.setColor(Color.black);
				g2.drawRect(realX, realY, cellWidth, cellHeight);
			}
		}
	}
		
	    class HitTestAdapter extends MouseAdapter
	    {
	    	@Override
	        public void mousePressed(MouseEvent e) {
	            int x = e.getX() / cellWidth;
	            int y = e.getY() / cellHeight;
	            
	            System.out.println("x: " + x + ", y: " + y);
	            
	            if(x >= 0 && x < gridWidth && y >= 0 && y < gridHeight)
	            {
	            	System.out.println("toggeld!");
	            	cells[y][x] = ! cells[y][x];
	            }
	        }

	    }

	@Override
	public void paintComponent(Graphics g)
	{

		super.paintComponent(g);
		doDrawing(g);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		repaint();
	}
}

public class Grid extends JFrame
{

	public Grid()
	{

		initUI();
	}

	private void initUI()
	{

		setTitle("Grid");

		SurfaceG component = new SurfaceG(5, 4, 100, 50);
		component.setLocation(100, 100);
		
		add(component);

		setSize(800, 800);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
	}

	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable()
		{

			@Override
			public void run()
			{

				Grid bb = new Grid();
				bb.setVisible(true);
			}
		});
	}
}