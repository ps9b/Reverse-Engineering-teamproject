import java.awt.*;

// Box 클래스 (사각형)
class Box extends TwoPointFigure {
    private boolean _fillFlag;

    public Box(Color color, int x1, int y1, int x2, int y2) {
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

        if (!_fillFlag) {
            g.drawRect(x, y, width, height);
        } else {
            g.fillRect(x, y, width, height);
        }
    }

    public Figure copy() {
        Box newBox = new Box(_color, _x1, _y1, _x2, _y2);
        newBox._fillFlag = _fillFlag;
        newBox._popup = _popup;
        newBox.move(20, 10);
        return newBox;
    }
}
