import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Network implements Runnable
{

    static Server server = new Server();
    static Socket client;
    static Thread search = new Thread(server);
    static boolean connected = false, serverConnected = true;
    static InputStream in;
    static OutputStream out;
    static byte[] buffer = new byte[500];

    public void run()
    {
        search.start();
        connect();
    }

    private static void connect()
    {
        Socket temp;
        while (!connected)
        {
            try
            {
                temp = new Socket(Main.window.Menu.ipAddressField.getText(), Integer.parseInt(Main.window.Menu.clientPortField.getText()));
                if (!connected)
                {
                    client = temp;
                    connected = true;
                    search.interrupt();
                    search = null;
                    server = null;
                    Main.window.Game.uppdateTurns(true);
                    Main.window.Game.iBegin = true;
                    serverConnected = false;
                }
            }catch (Exception e)
            {
                System.out.println("trying again");
            }
        }
        //här
        try
        {
            in = client.getInputStream();
            out = client.getOutputStream();
        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("något gick fel på stream");
        }

        Main.window.Menu.button1.setEnabled(true);
        Main.window.changePanel(Main.window.Game);
        System.out.println("Connectad!!");

        if (serverConnected)
        {
            Main.network.sendOrReadMessage(null, true);
        }
    }

    public static void sendOrReadMessage(String choice, boolean read)
    {
        if (read)
        {
            try
            {
                buffer = new byte[500];
                in.read(buffer);
                String temp = new String(buffer);

                String answer = temp.substring(temp.indexOf(":") + 1, temp.indexOf(";"));
                Main.window.Game.gotAnswer(answer);
            }catch (Exception e)
            {
                System.out.println("han disconnecta");
                Main.window.changePanel(Main.window.Menu);
            }
        }
        else
        {
            try
            {
                String temp = ":" + choice + ";";
                buffer = new byte[500];
                buffer = temp.getBytes();
                out.write(buffer);
            }catch (Exception e)
            {
                System.out.println("han disconnecta");
                Main.window.changePanel(Main.window.Menu);
            }
        }
    }
}
