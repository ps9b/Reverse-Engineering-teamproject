import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

class DrawerView extends JPanel implements MouseMotionListener {
    private ArrayList<Figure> _figures;
    private Popup _mainPopup;
    private Popup _pointPopup;
    private Popup _linePopup;
    private Popup _boxPopup;
    private Popup _circlePopup;
    private Popup _tvPopup;

    private Figure _selectedFigure;
    private int _whatToDraw;
    private int _actionMode;
    private int _currentX;
    private int _currentY;
    private boolean _modifiedFlag;

    public static int DRAW_POINT = 1;
    public static int DRAW_LINE = 2;
    public static int DRAW_BOX = 3;
    public static int DRAW_CIRCLE = 4;
    public static int DRAW_TV = 5;

    private static int NOTHING = 0;
    private static int DRAWING = 1;
    private static int MOVING = 2;

    public DrawerView(int w, int h) {
        super();
        setSize(w, h);
        setPreferredSize(new Dimension(w, h));
        this.enableEvents(AWTEvent.MOUSE_EVENT_MASK);

        _figures = new ArrayList<Figure>();
        _mainPopup = new MainPopup(this);
        _pointPopup = new FigurePopup(this, "점", false);
        _linePopup = new FigurePopup(this, "선", false);
        _boxPopup = new FigurePopup(this, "사각형", true);
        _circlePopup = new FigurePopup(this, "원", true);
        _tvPopup = new TVPopup(this);

        _selectedFigure = null;
        _whatToDraw = NOTHING;
        _actionMode = NOTHING;
        _currentX = 0;
        _currentY = 0;

        addMouseMotionListener(this);
    }

    private void popupMenu(MouseEvent e) {
        if (_selectedFigure != null) _selectedFigure.eraseDots(this);
        _selectedFigure = null;

        for (Figure ptr : _figures) {
            if (ptr.ptInRegion(e.getX(), e.getY())) {
                _selectedFigure = ptr;
                break;
            }
        }

        if (_selectedFigure != null) {
            _selectedFigure.popup(e);
        } else {
            _mainPopup.popup(e);
        }
    }

    private void buttonPressed(java.awt.Point point) {
        if (_whatToDraw == NOTHING) {
            if (_selectedFigure != null) {
                if (_selectedFigure.ptInRegion(point.x, point.y)) {
                    _actionMode = MOVING;
                    _currentX = point.x;
                    _currentY = point.y;
                    removeFigure(_selectedFigure);
                    setModifiedFlag(true);
                    return;
                }
            }
            _selectedFigure = null;

            for (Figure ptr : _figures) {
                if (ptr.ptInRegion(point.x, point.y)) {
                    _selectedFigure = ptr;
                    break;
                }
            }
            repaint();
            return;
        }

        if (_selectedFigure != null) _selectedFigure.eraseDots(this);
        _selectedFigure = null;

        if (_whatToDraw == DRAW_POINT) {
            _selectedFigure = new Point(Color.black, point.x, point.y);
            _selectedFigure.setPopup(_pointPopup);
        } else if (_whatToDraw == DRAW_LINE) {
            _selectedFigure = new Line(Color.black, point.x, point.y, point.x, point.y);
            _selectedFigure.setPopup(_linePopup);
        } else if (_whatToDraw == DRAW_BOX) {
            _selectedFigure = new Box(Color.black, point.x, point.y, point.x, point.y);
            _selectedFigure.setPopup(_boxPopup);
        } else if (_whatToDraw == DRAW_CIRCLE) {
            _selectedFigure = new Circle(Color.black, point.x, point.y, point.x, point.y);
            _selectedFigure.setPopup(_circlePopup);
        } else if (_whatToDraw == DRAW_TV) {
            _selectedFigure = new TV(Color.black, point.x, point.y, true);
            _selectedFigure.setPopup(_tvPopup);
            _whatToDraw = NOTHING;
            _selectedFigure.makeRegion();
            addFigure(_selectedFigure);
            repaint();
            return;
        }

        _whatToDraw = NOTHING;
        _actionMode = DRAWING;
        _selectedFigure.draw(getGraphics());
        setModifiedFlag(true);
    }

    private void mouseMoved(java.awt.Point point) {
        Graphics g = getGraphics();
        if (g == null) return;
        
        if (_actionMode == DRAWING) {
            g.setXORMode(getBackground());
            _selectedFigure.drawing(g, point.x, point.y);
        } else if (_actionMode == MOVING) {
            g.setXORMode(getBackground());
            _selectedFigure.move(g, point.x - _currentX, point.y - _currentY);
            _currentX = point.x;
            _currentY = point.y;
        }
    }

    private void buttonReleased(java.awt.Point point) {
        if (_selectedFigure == null && _actionMode == NOTHING) {
            return;
        }
        Graphics g = getGraphics();
        if (_actionMode == DRAWING && g != null) {
            g.setXORMode(getBackground());
            _selectedFigure.drawing(g, point.x, point.y);
        }
        _selectedFigure.makeRegion();
        addFigure(_selectedFigure);
        _actionMode = NOTHING;
        repaint();
    }

    public void setModifiedFlag(boolean flag) { _modifiedFlag = flag; }
    public boolean getModifiedFlag() { return _modifiedFlag; }
    public ArrayList<Figure> getFigures() { return _figures; }
    public void setFigures(ArrayList<Figure> figures) { _figures = figures; }
    public void addFigure(Figure ptr) { _figures.add(ptr); }
    public void removeFigure(Figure ptr) { _figures.remove(ptr); }
    public void clear() { _figures.clear(); }
    public void resetSelectedFigure() { _selectedFigure = null; }

    public void setPopup(Figure ptr) {
        if (ptr instanceof Point) { ptr.setPopup(_pointPopup); }
        else if (ptr instanceof Line) { ptr.setPopup(_linePopup); }
        else if (ptr instanceof Box) { ptr.setPopup(_boxPopup); }
        else if (ptr instanceof Circle) { ptr.setPopup(_circlePopup); }
        else if (ptr instanceof TV) { ptr.setPopup(_tvPopup); }
        else { ptr.setPopup(null); }
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (Figure ptr : _figures) {
            ptr.draw(g);
        }
        if (_selectedFigure != null && _actionMode == NOTHING) {
            _selectedFigure.drawDots(g);
        }
    }

    public void processMouseEvent(MouseEvent e) {
        if (e.isPopupTrigger()) {
            popupMenu(e);
        } else if (e.getID() == MouseEvent.MOUSE_PRESSED && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
            buttonPressed(new java.awt.Point(e.getX(), e.getY()));
        } else if (e.getID() == MouseEvent.MOUSE_RELEASED && (e.getModifiers() & MouseEvent.BUTTON1_MASK) != 0) {
            buttonReleased(new java.awt.Point(e.getX(), e.getY()));
        } else {
            super.processMouseEvent(e);
        }
    }

    public void mouseDragged(MouseEvent e) {
        mouseMoved(new java.awt.Point(e.getX(), e.getY()));
    }

    public void mouseMoved(MouseEvent e) {
        _actionMode = NOTHING;
    }

    public void setColor(Color color) {
        if (_selectedFigure == null) return;
        _selectedFigure.setColor(color);
        setModifiedFlag(true);
        repaint();
    }

    public void createFigure(int id) { _whatToDraw = id; }

    public void deleteFigure() {
        if (_selectedFigure == null) return;
        removeFigure(_selectedFigure);
        _selectedFigure = null;
        setModifiedFlag(true);
        repaint();
    }

    public void copyFigure() {
        if (_selectedFigure == null) return;
        Figure newFigure = _selectedFigure.copy();
        newFigure.makeRegion();
        addFigure(newFigure);
        _selectedFigure = newFigure;
        setModifiedFlag(true);
        repaint();
    }

    public void setFill() {
        if (_selectedFigure == null) return;
        if (_selectedFigure instanceof TwoPointFigure) {
            TwoPointFigure ptr = (TwoPointFigure)_selectedFigure;
            ptr.setFill();
        }
        setModifiedFlag(true);
        repaint();
    }

    public void turnOnOffTv() {
        if (_selectedFigure == null) return;
        if (_selectedFigure instanceof TV) {
            TV pTV = (TV)_selectedFigure;
            pTV.pressPowerButton();
        }
        setModifiedFlag(true);
        repaint();
    }

    public void setAntenna() {
        if (_selectedFigure == null) return;
        if (_selectedFigure instanceof TV) {
            TV pTV = (TV)_selectedFigure;
            pTV.setAntenna();
        }
        setModifiedFlag(true);
        repaint();
    }
}