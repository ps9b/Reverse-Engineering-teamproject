import java.awt.*;
import javax.swing.*;

// TVPopup 클래스 (TV 전용 우클릭 메뉴)
class TVPopup extends FigurePopup {
    public TVPopup(DrawerView view) {
        super(view, "TV", false);

        JMenuItem powerItem = new JMenuItem("ON/OFF");
        powerItem.addActionListener(e -> view.turnOnOffTv());
        _popupPtr.add(powerItem);

        JMenuItem antennaItem = new JMenuItem("Handle Antenna");
        antennaItem.addActionListener(e -> view.setAntenna());
        _popupPtr.add(antennaItem);
    }
}