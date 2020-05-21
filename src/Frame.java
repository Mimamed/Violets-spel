import javax.swing.*;

public class Frame extends JFrame
{

    static Panel_Menu Menu = new Panel_Menu();
    static Panel_Game Game = new Panel_Game();
    static JPanel currentPanel;

    Frame(String title)
    {
        this.setVisible(true);
        this.setSize(500,500);
        this.setLocationRelativeTo(null);
        this.setTitle(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.add(Menu);
        currentPanel = Menu;
    }


    public void changePanel(JPanel Panel)
    {
        this.remove(currentPanel);
        this.add(Panel);
        this.revalidate();
        this.repaint();
        currentPanel = Panel;
    }

}
