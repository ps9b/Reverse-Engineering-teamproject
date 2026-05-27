import java.awt.*;
// Circle 클래스 (원)
class Circle extends TwoPointFigure {
    private boolean _fillFlag;

    public Circle(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1, x2, y2);
        _fillFlag = false;
    }

    public void setFill() {
        _fillFlag = !_fillFlag;
    }

    public void draw(Graphics g) {
        g.setColor(_color);
        int x = Math.min(_x1, _x2);
        int y = Math.min(_y1, _y2);
        int width = Math.abs(_x2 - _x1);
        int height = Math.abs(_y2 - _y1);

        g.drawOval(x, y, width, height);
        if (_fillFlag) {
            g.fillOval(x, y, width + 1, height + 1);
        }
    }

    public Figure copy() {
        Circle newCircle = new Circle(_color, _x1, _y1, _x2, _y2);
        newCircle._fillFlag = _fillFlag;
        newCircle._popup = _popup;
        newCircle.move(20, 10);
        return newCircle;
    }
}
