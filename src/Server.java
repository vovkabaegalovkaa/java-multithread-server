
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
public class Server {
    public static int numConnections = 0;
    public static ArrayList<Weather> weathers = new ArrayList<Weather>();
    public static void main(String[] args) {
        weathers.add(new Weather(1, "yes", "cloudy"));
        weathers.add(new Weather(2, "yes", "cloudy"));
        weathers.add(new Weather(3, "no", "sunny"));
        weathers.add(new Weather(4, "no", "sunny"));
        weathers.add(new Weather(5, "yes", "cloudy"));
        try {
            ServerSocket server = new ServerSocket(3345);
            System.out.println("Сервер запущен!");
            while (true) {
                Socket client = server.accept();
                Thread t = new MonoThreadClientHandler(client);
                t.start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
