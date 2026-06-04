import java.awt.*;
import java.util.ArrayList;

class TV extends TwoPointFigure {
    private boolean _antennaFlag;
    private ArrayList<Figure> _parts = new ArrayList<>(); // ArrayList

    private static int FRAME_WIDTH = 150;
    private static int FRAME_HEIGHT = 90;
    private static int ANTENNA_WIDTH = 30;
    private static int ANTENNA_HEIGHT = 40;
    private static int FRAME_GAP = 12;
    private static int SCREEN_WIDTH = 105;
    private static int SCREEN_HEIGHT = FRAME_HEIGHT - 2 * FRAME_GAP;
    private static int SWITCH_GAP = 17;
    private static int SWITCH_SIZE = 10;
    private static int POWER_SWITCH_WIDTH = 15;
    private static int POWER_SWITCH_HEIGHT = 8;
    private static int TOTAL_WIDTH = FRAME_WIDTH;
    private static int TOTAL_HEIGHT = FRAME_HEIGHT + ANTENNA_HEIGHT;

    public TV(Color color, int x, int y, boolean antennaOption) {
        super(Color.black, x, y, x + TOTAL_WIDTH, y + TOTAL_HEIGHT);

        int x1 = x;
        int y1 = y + ANTENNA_HEIGHT;
        int x2 = x + FRAME_WIDTH;
        int y2 = y + TOTAL_HEIGHT;
        _parts.add(new Box(Color.black, x1, y1, x2, y2));   // frame

        x1 = x1 + FRAME_GAP;
        y1 = y1 + FRAME_GAP;
        x2 = x1 + SCREEN_WIDTH;
        y2 = y1 + SCREEN_HEIGHT;
        _parts.add(new Box(color, x1, y1, x2, y2));          // screen

        x1 = x2 + FRAME_GAP;
        y1 = y1 + FRAME_GAP / 2;
        x2 = x1 + SWITCH_SIZE;
        y2 = y1 + SWITCH_SIZE;
        _parts.add(new Circle(Color.black, x1, y1, x2, y2)); // channelButton

        y1 = y1 + SWITCH_GAP;
        y2 = y1 + SWITCH_SIZE;
        _parts.add(new Circle(Color.black, x1, y1, x2, y2)); // volumnButton

        y1 = y1 + SWITCH_GAP;
        y2 = y1 + SWITCH_SIZE;
        _parts.add(new Circle(Color.black, x1, y1, x2, y2)); // menuButton

        x1 = x1 - FRAME_GAP / 3 + 2;
        y1 = y1 + SWITCH_GAP + 2;
        x2 = x1 + POWER_SWITCH_WIDTH;
        y2 = y1 + POWER_SWITCH_HEIGHT;
        _parts.add(new Box(Color.black, x1, y1, x2, y2));    // powerButton

        _antennaFlag = antennaOption;
        if (antennaOption) {
            int cx = x + TOTAL_WIDTH / 2;
            x1 = cx - ANTENNA_WIDTH;
            y1 = y;
            x2 = cx;
            y2 = y + ANTENNA_HEIGHT;
            _parts.add(new Line(Color.black, x1, y1, x2, y2)); // antenna1

            x1 = cx + ANTENNA_WIDTH;
            _parts.add(new Line(Color.black, x1, y1, x2, y2)); // antenna2
        }
    }

    public void draw(Graphics g) {
        for (Figure part : _parts) {
            part.draw(g);  // ArrayList -> draw
        }
    }

    public void move(int dx, int dy) {
        super.move(dx, dy);
        for (Figure part : _parts) {
            part.move(dx, dy); //ArrayList -> move
        }
    }

    public Figure copy() {
        TV newTV = new TV(_color, _x1, _y1, _antennaFlag);
        newTV._popup = _popup;
        newTV.move(50, 50);
        return newTV;
    }
}
