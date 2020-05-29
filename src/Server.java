import com.dosse.upnp.UPnP;

import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable//här är tråden servern kör i. Här väntar servern på andra personens klient
{

    static ServerSocket serverSocket;

    public void run()
    {
        setup();
    }

    private static void setup()
    {
        try
        {
            UPnP.openPortTCP(Integer.parseInt(Main.window.Menu.serverPortField.getText()));
            serverSocket = new ServerSocket(Integer.parseInt(Main.window.Menu.serverPortField.getText()));
            Main.network.client = serverSocket.accept();
            Main.network.connected = true;
            Main.window.Game.uppdateTurns(false);
            Main.window.Game.iBegin = false;// om du är den som hostar, så är du inte den som börjar
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
