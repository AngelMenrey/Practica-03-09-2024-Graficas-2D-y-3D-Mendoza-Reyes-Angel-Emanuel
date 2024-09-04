import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Eventos extends JFrame implements ActionListener, MouseListener, MouseMotionListener {
    private JPanel panelPpal;
    private JLabel jlbCoord;
    private JLabel jlbTitulo;
    private JButton jbnLinea;
    private JButton jbnRectangulo;
    private JButton jbnCuadrado;
    private Image buffer;
    private Image temporal;

    private int figura = 0;
    private int posX = 50;
    private int x, y;

    public Eventos() {
        setTitle("Eventos del mouse");
        setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setBounds(0, 0, 800, 600);

        panelPpal = new JPanel();
        panelPpal.setBorder(new EmptyBorder(5, 5, 5, 5));
        panelPpal.setLayout(null);

        jlbTitulo = new JLabel("Elige una figura:");
        jlbTitulo.setBounds(50, 30, 100, 20);
        jlbCoord = new JLabel("Coordenadas: x,y");
        jlbCoord.setBounds(600, 50, 200, 20);

        jbnLinea = new JButton("Linea");
        jbnRectangulo = new JButton("Rectangulo");
        jbnCuadrado = new JButton("Cuadrado");

        jbnLinea.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                figura = 1;
                repaint();
            }
        });
        jbnLinea.setBounds(50, 50, 100, 30);

        jbnRectangulo.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                figura = 2;
                repaint();
            }
        });
        jbnRectangulo.setBounds(170, 50, 100, 30);

        jbnCuadrado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                figura = 3;
                repaint();
            }
        });
        jbnCuadrado.setBounds(290, 50, 100, 30);

        panelPpal.addMouseListener(this);
        panelPpal.addMouseMotionListener(this);

        panelPpal.add(jlbTitulo);
        panelPpal.add(jbnLinea);
        panelPpal.add(jbnRectangulo);
        panelPpal.add(jbnCuadrado);
        panelPpal.add(jlbCoord);

        setContentPane(panelPpal);

        setLocationRelativeTo(null);
        setVisible(true);

        buffer = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
        temporal = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics bufferGraphics = buffer.getGraphics();
        switch (figura) {
            case 0:
                bufferGraphics.setColor(Color.blue);
                bufferGraphics.drawOval(50, 150, 100, 100);
                break;
            case 1:
                bufferGraphics.setColor(Color.blue);
                bufferGraphics.drawLine(50, 300, 200, 200);
                break;
            case 2:
                bufferGraphics.setColor(Color.red);
                bufferGraphics.drawRect(300, 150, 100, 140);
                break;
            case 3:
                bufferGraphics.setColor(Color.gray);
                bufferGraphics.drawRect(posX, 350, 100, 100);
                if (posX < 600) {
                    posX += 50;
                } else {
                    posX = 50;
                }
                break;
            default:
                break;
        }
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Graphics g = temporal.getGraphics();
        g.drawImage(buffer, 0, 0, this);
        g.setColor(Color.black);
        g.drawRect(x, y, e.getX() - x, e.getY() - y);
        panelPpal.getGraphics().drawImage(temporal, 0, 0, this);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        jlbCoord.setText("Coordenadas: X=" + e.getX() + ", y=" + e.getY());
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        temporal = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
        Graphics g = temporal.getGraphics();
        g.drawImage(buffer, 0, 0, this);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Graphics g = buffer.getGraphics();
        g.drawImage(temporal, 0, 0, this);
        repaint();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
        setCursor(Cursor.getDefaultCursor());
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Graphics g = panelPpal.getGraphics();
        g.clearRect(0, 0, panelPpal.getWidth(), panelPpal.getHeight());
        buffer = panelPpal.createImage(panelPpal.getWidth(), panelPpal.getHeight());
        repaint();
    }
}
