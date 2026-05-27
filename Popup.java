import java.awt.event.*;
import javax.swing.*;
import java.io.*;

abstract class Popup implements Serializable {
    protected JPopupMenu _popupPtr;

    public Popup(String title) {
        super();
        _popupPtr = new JPopupMenu(title);
        if (title != null) {
            _popupPtr.add(title);
            _popupPtr.addSeparator();
        }
    }

    public void popup(MouseEvent event) {
        _popupPtr.show(event.getComponent(), event.getX(), event.getY());
    }
}