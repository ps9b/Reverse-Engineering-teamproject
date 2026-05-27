import java.awt.*;

// OnePointFigure 추상 클래스
abstract class OnePointFigure extends Figure {
    protected int _x1;
    protected int _y1;

    public OnePointFigure(Color color, int x1, int y1) {
        super(color);
        _x1 = x1;
        _y1 = y1;
    }

    public void drawDots(Graphics g) {
        _dotedFlag = true;
        g.setColor(Color.black);
        g.fillRect(_x1 - DOTSIZE / 2, _y1 - DOTSIZE / 2, DOTSIZE, DOTSIZE);
    }

    public void drawing(Graphics g, int newX, int newY) {
        draw(g);
        _x1 = newX;
        _y1 = newY;
        draw(g);
    }

    public void move(int dx, int dy) {
        _x1 = _x1 + dx;
        _y1 = _y1 + dy;
    }

    public void makeRegion() {
        int xpoints[] = new int[4];
        int ypoints[] = new int[4];

        xpoints[0] = _x1 - 3; ypoints[0] = _y1 - 3;
        xpoints[1] = _x1 + 3; ypoints[1] = _y1 - 3;
        xpoints[2] = _x1 + 3; ypoints[2] = _y1 + 3;
        xpoints[3] = _x1 - 3; ypoints[3] = _y1 + 3;

        _region = new Polygon(xpoints, ypoints, 4);
    }
}

