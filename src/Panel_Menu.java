import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Panel_Menu extends Panel // detta är själva meny panelen
{
    static JTextField ipAddressField = new JTextField("Write their ipAddress here..."), serverPortField = new JTextField("your port"), clientPortField = new JTextField("their port");
    static JButton button1 = new JButton();

    Panel_Menu()// starta startup och setup funktion
    {
        startUp();
        setup();
    }

    public void setup() // denna är metoden som sätter upp knapparna och sånt i panelen
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
        button1.addActionListener(new Play()); // Den connectar dig och byter panel och sånt
    }

    private static class Play implements ActionListener // denna är playknappens funktion, alltså vad knappen ska göra när man trycker den. Den börjar connecta dig till andra personen
    {

        public void actionPerformed(ActionEvent e)
        {
            button1.setEnabled(false);
            Main.network = new Network();
            Main.connection = new Thread(Main.network);
            Main.connection.start(); // denna startar uppnetwork klassen

        }
    }
}
