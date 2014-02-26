package woche3.application;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;

import woche3.model.BookConditionEnum;
import woche3.model.BookDO;
import woche3.view.BookDetailFrame;

/**
 * @author msyfrig
 */
public class BookDetailMain {

    private static List<BookDO> bookDOList = new ArrayList<>();
    static {
        BookDO book = new BookDO("978-3-16-148410-0", "Booktitle",
                "Agatha Christie", "Publisher", BookConditionEnum.NEW);
        bookDOList.add(book);
    }

    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Show two Views: Editing in Detail Frames; Fully Synchronized
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JFrame frame1 = new BookDetailFrame(bookDOList
                                    .get(0));
                            JFrame frame2 = new BookDetailFrame(bookDOList
                                    .get(0));
                            frame1.setVisible(true);
                            frame2.setVisible(true);
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });
    }

}
