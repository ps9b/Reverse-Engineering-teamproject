import java.awt.*;
import javax.swing.*;

// FigurePopup 클래스 (일반 도형 우클릭 메뉴)
class FigurePopup extends Popup {
    public FigurePopup(DrawerView view, String title, boolean fillButtonFlag) {
        super(title);

        JMenuItem deleteItem = new JMenuItem("지우기");
        deleteItem.addActionListener(e -> view.deleteFigure());
        _popupPtr.add(deleteItem);

        JMenuItem copyItem = new JMenuItem("복사하기");
        copyItem.addActionListener(e -> view.copyFigure());
        _popupPtr.add(copyItem);

        JMenu colorPopup = new JMenu("색 정하기");
        _popupPtr.add(colorPopup);

        JMenuItem blackItem = new JMenuItem("검정색");
        blackItem.addActionListener(e -> view.setColor(Color.black));
        colorPopup.add(blackItem);

        JMenuItem redItem = new JMenuItem("빨간색");
        redItem.addActionListener(e -> view.setColor(Color.red));
        colorPopup.add(redItem);

        JMenuItem greenItem = new JMenuItem("초록색");
        greenItem.addActionListener(e -> view.setColor(Color.green));
        colorPopup.add(greenItem);

        JMenuItem blueItem = new JMenuItem("파랑색");
        blueItem.addActionListener(e -> view.setColor(Color.blue));
        colorPopup.add(blueItem);

        if (fillButtonFlag) {
            JMenuItem fillItem = new JMenuItem("채우기");
            fillItem.addActionListener(e -> view.setFill());
            _popupPtr.add(fillItem);
        }
    }
}