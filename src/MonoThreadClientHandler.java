
import java.io.*;
import java.net.Socket;
public class MonoThreadClientHandler extends Thread {
    Socket clientDialog;
    public MonoThreadClientHandler(Socket client) {
        this.clientDialog = client;
    }
    @Override
    public void run() {
        super.run();
        System.out.println("Подключение принято от " +
                clientDialog.getInetAddress().toString().substring(1) + ":"
                + clientDialog.getPort());
        Server.numConnections++;
        System.out.println("Клиентов подключено: " + Server.numConnections);
        try {
            DataOutputStream out = new DataOutputStream(clientDialog.getOutputStream());
            DataInputStream in = new DataInputStream(clientDialog.getInputStream());
            System.out.println("Сервер считывает данные с канала");
            while (!clientDialog.isClosed()) {
                String entry = "";
                try {
                    entry = in.readUTF();
                }  catch (IOException e) {

                }
                if (entry.equalsIgnoreCase("")) {
                    System.out.println("Поток закрыт");
                    clientDialog.close();
                    break;
                }
                System.out.println("Клиент прислал - " + entry);
                int date = Integer.parseInt(entry);
               for (Weather d: Server.weathers){
                   if(date == d.date) {
                       System.out.println(d.toString());
                       out.writeUTF(d.toString());
                       out.flush();
                   }
               }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Server.numConnections--;
            System.out.println("Клиент отключился: " +
                    clientDialog.getInetAddress().toString().substring(1) + ":"
                    + clientDialog.getPort());
            System.out.println("Клиентов подключено: " +
                    Server.numConnections);
        }
    }
}