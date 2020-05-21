import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel_Menu extends Panel
{
    static JTextField ipAddressField = new JTextField("Write their ipAddress here..."), serverPortField = new JTextField("your port"), clientPortField = new JTextField("their port");
    static JButton button1 = new JButton();

    Panel_Menu()
    {
        startUp();
        setup();
    }

    public void setup()
    {
        this.add(button1);
        this.add(ipAddressField);
        this.add(serverPortField);
        this.add(clientPortField);

        ipAddressField.setBounds(100, 200, 300, 30);
        serverPortField.setBounds(200, 250, 100, 30);
        clientPortField.setBounds(200, 300, 100, 30);

        button1.setBounds(200, 100, 100, 30);
        button1.setText("Connect & play");
        button1.setMargin(new Insets(0,0,0,0));
        button1.setFocusPainted(false);
        button1.setBackground(Color.GREEN);
        button1.addActionListener(new Play());
    }

    private static class Play implements ActionListener
    {

        public void actionPerformed(ActionEvent e)
        {
            /*
            Main.window.changePanel(Main.window.Game);
            */
            button1.setEnabled(false);
            Main.network = new Network();
            Main.connection = new Thread(Main.network);
            Main.connection.start();

        }
    }
}
