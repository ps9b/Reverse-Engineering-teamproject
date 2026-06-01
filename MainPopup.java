import javax.swing.*;

// MainPopup Ŭ���� (�� ȭ�� ��Ŭ�� �޴�)
class MainPopup extends Popup {
    public MainPopup(DrawerView view) {
        super("����");
        
        JMenuItem pointItem = new JMenuItem("��");
        pointItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_POINT));
        _popupPtr.add(pointItem);

        JMenuItem lineItem = new JMenuItem("��");
        lineItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_LINE));
        _popupPtr.add(lineItem);

        JMenuItem boxItem = new JMenuItem("�簢��");
        boxItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_BOX));
        _popupPtr.add(boxItem);

        JMenuItem circleItem = new JMenuItem("��");
        circleItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_CIRCLE));
        _popupPtr.add(circleItem);

        JMenuItem tvItem = new JMenuItem("TV");
        tvItem.addActionListener(e -> view.createFigure(DrawerView.DRAW_TV));
        _popupPtr.add(tvItem);
    }
}