import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

class Surface extends JPanel
        implements ActionListener {

    private Color colors[] = {
        Color.blue, Color.cyan, Color.green,
        Color.magenta, Color.orange, Color.pink,
        Color.red, Color.yellow, Color.lightGray, Color.white
    };
    
    private Ellipse2D.Float[] ellipses;
    private double esize[];
    private float estroke[];
    private double maxSize = 0;
    private Timer timer;

    public Surface() {

        initSurface();
        initEllipses();
        initTimer();
    }
    
    private void initSurface() {
        
        setBackground(Color.black);
        ellipses = new Ellipse2D.Float[25];
        esize = new double[ellipses.length];
        estroke = new float[ellipses.length];           
    }
    
    private void initEllipses() {

        int w = 350;
        int h = 250;
                
        maxSize = w / 10;
        
        for (int i = 0; i < ellipses.length; i++) {
            
            ellipses[i] = new Ellipse2D.Float();
            posRandEllipses(i, maxSize * Math.random(), w, h);
        }
    }    
    
    private void initTimer() {
        
        timer = new Timer(30, this);
        timer.setInitialDelay(190);
        timer.start();        
    }

    private void posRandEllipses(int i, double size, int w, int h) {

        esize[i] = size;
        estroke[i] = 1.0f;
        double x = Math.random() * (w - (maxSize / 2));
        double y = Math.random() * (h - (maxSize / 2));
        ellipses[i].setFrame(x, y, size, size);
    }

    private void doStep(int w, int h) {

        for (int i = 0; i < ellipses.length; i++) {

            estroke[i] += 0.025f;
            esize[i]++;

            if (esize[i] > maxSize) {
                
                posRandEllipses(i, 1, w, h);
            } else {
                
                ellipses[i].setFrame(ellipses[i].getX(), ellipses[i].getY(),
                        esize[i], esize[i]);
            }
        }
    }

    private void drawEllipses(Graphics2D g2) {

        for (int i = 0; i < ellipses.length; i++) {
            
            g2.setColor(colors[i % colors.length]);
            g2.setStroke(new BasicStroke(estroke[i]));
            g2.draw(ellipses[i]);
        }
    }
    
    private void doDrawing(Graphics g) {
        
        Graphics2D g2 = (Graphics2D) g;

        RenderingHints rh =
                new RenderingHints(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        rh.put(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);

        g2.setRenderingHints(rh);
        
        Dimension size = getSize();
        doStep(size.width, size.height);
        drawEllipses(g2);        
    }

    @Override
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        doDrawing(g);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        repaint();
    }
}

public class Bubbles extends JFrame {

    public Bubbles() {

        initUI();
    }

    private void initUI() {

        setTitle("Bubbles");

        add(new Surface());

        setSize(350, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(new Runnable() {
            
            @Override
            public void run() {
                
                Bubbles bb = new Bubbles();
                bb.setVisible(true);
            }
        });
    }
}