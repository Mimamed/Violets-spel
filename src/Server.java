import java.io.IOException;
import java.net.ServerSocket;

public class Server implements Runnable
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
            serverSocket = new ServerSocket(Integer.parseInt(Main.window.Menu.serverPortField.getText()));
            Main.network.client = serverSocket.accept();
            Main.network.connected = true;
            Main.window.Game.uppdateTurns(false);
            Main.window.Game.iBegin = false;
            Main.network.sendOrReadMessage(null, true);
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
