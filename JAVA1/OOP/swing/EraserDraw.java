package swing;

import java.awt.*;
import java.awt.geom.Path2D;
import java.util.ArrayList;

public class EraserDraw {
    private ArrayList<Point> points;
    private int size;

    public EraserDraw(int size) {
        points = new ArrayList<>();
        this.size = size;
    }

    public void addPoint(int x, int y) {
        points.add(new Point(x, y));
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(size, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
        if (points.size() > 1) {
            for (int i = 1; i < points.size(); i++) {
                Point p1 = points.get(i - 1);
                Point p2 = points.get(i);
                g2d.drawLine(p1.x, p1.y, p2.x, p2.y);
            }
        }
    }
}