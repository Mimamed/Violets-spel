import javax.swing.*;
import java.awt.*;

public abstract class Panel extends JPanel //denna klassen bestämmer hur en panel ska se ut men är inte själva panelen. Se det som en defoult för hur paneler du skapar ser ut.
{

    public void startUp()
    {
        this.setLayout(null);
        this.setBackground(Color.DARK_GRAY);
    }
//abstract tvingar dig att koda in.
    public abstract void setup();

}
