/*
Jada Chang
ISC4U ISU
*/
import java.awt.*;
import javax.swing.*;

public class TitlePage {
    JFrame frame;
    JPanel mainPanel;
    JLabel cover; //cover image
    JButton start; //start button
    Image pointer; //cursor

    public TitlePage() {
        frame = new JFrame("Crown of the Town");
        frame.setPreferredSize(new Dimension(1000, 600));
        frame.setLocation(10, 10);
        //frame.setBounds(10, 10, 1000, 600);

        //make my JPanel
        mainPanel = new JPanel();
        Color java99 = new Color(178, 255, 244);//aqua(turing 99)
        mainPanel.setBackground(java99);
        mainPanel.setBorder (BorderFactory.createEmptyBorder (10,10,10,10));
        //mainPanel.setLayout(new );

        //Add the title screen image
        cover = new JLabel (new ImageIcon ("assets/cover2.png"));
        Dimension size = cover.getPreferredSize();
        cover.setBounds(150, 10, size.width, size.height);
        mainPanel.add(cover); //add cover image to panel

        //Make start button
        start = new JButton("START");
        start.setFont(new Font("leafy", Font.PLAIN, 25));
        start.setBounds(40, 100, 100, 60);
        mainPanel.add(start);//add start button to panel
        //WRITE THE METHOD FOR BUTTON TO OPEN GAME CLASSES

        //Set custom cursor
        pointer = Toolkit.getDefaultToolkit().getImage("assets/cursor.png");
        Point hotspot = new Point (0, 0);
        Toolkit toolkit = Toolkit.getDefaultToolkit ();
        Cursor cursor = toolkit.createCustomCursor (pointer, hotspot, "cursor");

        //plop everything onto screen
        frame.add(mainPanel); //add panel to frame
        frame.pack();
        frame.setCursor(cursor);
        frame.setVisible(true);
    }
}

/*
***NOTES FOR MYSELF PLS IGNORE THIS***
JFRAME STUFF: https://chortle.ccsu.edu/java5/notes/chap56/ch56_9.html
COLOUR: https://mathbits.com/MathBits/Java/Graphics/Color.htm
LAYOUT MANAGER: https://www.javatpoint.com/java-layout-manager
*/