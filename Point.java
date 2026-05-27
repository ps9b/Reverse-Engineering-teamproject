// Point Å¬·¡½º
import java.awt.*;

class Point extends OnePointFigure {
    public Point(Color color, int x1, int y1) {
        super(color, x1, y1);
    }

    public void draw(Graphics g) {
        g.setColor(_color);
        g.drawOval(_x1 - 3, _y1 - 3, 5, 5);
        g.fillOval(_x1 - 3, _y1 - 3, 6, 6);
    }

    public Figure copy() {
        Point newPoint = new Point(_color, _x1, _y1);
        newPoint._popup = _popup;
        newPoint.move(20, 10);
        return newPoint;
    }
}