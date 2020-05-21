import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JPanel
{

    public void startUp()
    {
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
    }

    public abstract void setup();

}
