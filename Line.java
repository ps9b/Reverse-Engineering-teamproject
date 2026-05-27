import java.awt.*;

class Line extends TwoPointFigure {
    public Line(Color color, int x1, int y1, int x2, int y2) {
        super(color, x1, y1, x2, y2);
    }

    public void draw(Graphics g) {
        g.setColor(_color);
        g.drawLine(_x1, _y1, _x2, _y2);
    }

    public void drawDots(Graphics g) {
        _dotedFlag = true;
        g.setColor(Color.black);
        g.fillRect(_x1 - DOTSIZE / 2, _y1 - DOTSIZE / 2, DOTSIZE, DOTSIZE);
        g.fillRect(_x2 - DOTSIZE / 2, _y2 - DOTSIZE / 2, DOTSIZE, DOTSIZE);
    }

    public Figure copy() {
        Line newLine = new Line(_color, _x1, _y1, _x2, _y2);
        newLine._popup = _popup;
        newLine.move(20, 10);
        return newLine;
    }

    public void makeRegion() {
        int regionWidth = 6;
        int x = _x1; int y = _y1;
        int w = _x2 - _x1; int h = _y2 - _y1;
        int sign_h = (h < 0) ? -1 : 1;

        double theta = (w != 0) ? Math.atan((double)h / (double)w) : sign_h * Math.PI / 2.0;
        if (theta < 0) theta = theta + 2 * Math.PI;
        double angle = theta + Math.PI / 2.0;

        int dx = (int)(regionWidth * Math.cos(angle));
        int dy = (int)(regionWidth * Math.sin(angle));

        int xpoints[] = new int[4];
        int ypoints[] = new int[4];

        xpoints[0] = x + dx; ypoints[0] = y + dy;
        xpoints[1] = x - dx; ypoints[1] = y - dy;
        xpoints[2] = x + w - dx; ypoints[2] = y + h - dy;
        xpoints[3] = x + w + dx; ypoints[3] = y + h + dy;

        _region = new Polygon(xpoints, ypoints, 4);
    }
}
