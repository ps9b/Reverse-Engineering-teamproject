import java.awt.*;
import java.awt.event.*;
import java.io.*;

abstract class Figure extends Object implements Serializable {
    protected static int DOTSIZE = 6;
    protected Color _color;
    protected Popup _popup;
    protected Polygon _region;
    protected transient boolean _dotedFlag;

    public Figure(Color color) {
        _color = color;
        _popup = null;
        _region = null;
        _dotedFlag = false;
    }

    public void setPopup(Popup popup) {
        _popup = popup;
    }

    public void setColor(Color color) {
        _color = color;
    }

    public void popup(MouseEvent event) {
        if (_popup != null) {
            _popup.popup(event);
        }
    }

    public abstract void draw(Graphics g);
    public abstract void drawDots(Graphics g);

    public void eraseDots(DrawerView view) {
        _dotedFlag = false;
        view.repaint();
    }

    public abstract void drawing(Graphics g, int newX, int newY);

    public void move(Graphics g, int dx, int dy) {
        draw(g);
        move(dx, dy);
        draw(g);
    }

    public abstract void move(int dx, int dy);
    public abstract Figure copy();
    public abstract void makeRegion();

    public boolean ptInRegion(int x, int y) {
        if (_region != null) {
            return _region.contains(x, y);
        } else {
            return false;
        }
    }
}