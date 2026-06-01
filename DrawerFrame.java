import java.awt.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

class DrawerFrame extends JFrame {
    private DrawerView _view;
    private String _fileName;

    public DrawerFrame(String title) {
        super(title);
        Dimension screenSize = getToolkit().getScreenSize();
        setSize(screenSize.width, screenSize.height);

        _fileName = "";
        _view = new DrawerView(screenSize.width, screenSize.height);
        getContentPane().add(_view);

        JMenuBar menus = new JMenuBar();
        setJMenuBar(menus);

        JMenu fileMenu = new JMenu("파일(F)");
        fileMenu.setMnemonic('F');
        menus.add(fileMenu);

        JMenuItem newFile = new JMenuItem("새 파일(N)");
        newFile.setMnemonic('N');
        newFile.addActionListener(e -> {
            _view.clear();
            _view.resetSelectedFigure();
            _view.setModifiedFlag(true);
            _view.repaint();
        });
        fileMenu.add(newFile);

        JMenuItem openFile = new JMenuItem("열기(O)");
        openFile.setMnemonic('O');
        openFile.addActionListener(e -> doFileOpen());
        fileMenu.add(openFile);

        JMenuItem saveFile = new JMenuItem("저장(S)");
        saveFile.setMnemonic('S');
        saveFile.addActionListener(e -> {
            if (_view.getModifiedFlag() == false) return;
            if (_fileName != null && _fileName.length() > 0) {
                save();
                return;
            }
            doFileSaveAs();
        });
        fileMenu.add(saveFile);

        JMenuItem anotherFile = new JMenuItem("새 이름으로(A)");
        anotherFile.setMnemonic('A');
        anotherFile.addActionListener(e -> doFileSaveAs());
        fileMenu.add(anotherFile);
        
        fileMenu.addSeparator();

        JMenuItem exit = new JMenuItem("종료(X)");
        exit.setMnemonic('X');
        exit.addActionListener(e -> System.exit(0));
        fileMenu.add(exit);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    private void save() {
        try {
            FileOutputStream fos = new FileOutputStream(_fileName);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(_view.getFigures());
            oos.flush();
            oos.close();
            fos.close();
            _view.setModifiedFlag(false);
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    private void doFileOpen() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogType(JFileChooser.OPEN_DIALOG);
        
        int returnVal = chooser.showOpenDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) return;
        
        _fileName = chooser.getSelectedFile().getPath();
        _view.clear();
        _view.resetSelectedFigure();
        
        try {
            FileInputStream fis = new FileInputStream(_fileName);
            ObjectInputStream ois = new ObjectInputStream(fis);
            ArrayList<Figure> figures = (ArrayList<Figure>) (ois.readObject());
            
            for (Figure ptr : figures) {
                _view.setPopup(ptr);
            }
            
            ois.close();
            fis.close();
            _view.setFigures(figures);
            _view.setModifiedFlag(false);
            _view.repaint();
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace();
        } catch(IOException ex) {
            ex.printStackTrace();
        }
    }

    private void doFileSaveAs() {
        JFileChooser chooser = new JFileChooser(System.getProperty("user.dir"));
        chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        chooser.setDialogType(JFileChooser.SAVE_DIALOG);
        
        int returnVal = chooser.showSaveDialog(null);
        if (returnVal != JFileChooser.APPROVE_OPTION) return;
        
        String fileName = chooser.getSelectedFile().getPath();
        if (fileName == null || fileName.length() == 0) return;
        
        _fileName = fileName;
        save();
    }
}