import java.awt.*;
import javax.swing.*;

// MainPopup 클래스 (빈 화면 우클릭 메뉴)
class MainPopup extends Popup {
    public MainPopup(DrawerView view) {
        super("종류");
        
        JMenuItem pointItem = new JMenuItem("점");
        pointItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_POINT));
        _popupPtr.add(pointItem);

        JMenuItem lineItem = new JMenuItem("선");
        lineItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_LINE));
        _popupPtr.add(lineItem);

        JMenuItem boxItem = new JMenuItem("사각형");
        boxItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_BOX));
        _popupPtr.add(boxItem);

        JMenuItem circleItem = new JMenuItem("원");
        circleItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_CIRCLE));
        _popupPtr.add(circleItem);

        JMenuItem tvItem = new JMenuItem("TV");
        tvItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_TV));
        _popupPtr.add(tvItem);
    }
}