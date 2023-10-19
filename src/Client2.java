import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client2 {
    private static Socket clientSocket;
    private static DataInputStream in;
    private static DataOutputStream out;

    public static void main(String[] args) {
        try {
            try {
                Scanner sc = new Scanner(System.in);
                clientSocket = new Socket("localhost", 3345);
                out = new DataOutputStream(clientSocket.getOutputStream());
                in = new DataInputStream(clientSocket.getInputStream());
                while (true) {
                    System.out.println("Введите дату: ");
                    String name = sc.nextLine();
                    if (name.equalsIgnoreCase("")) {
                        clientSocket.close();
                        break;
                    }
                    out.writeUTF(name);
                    out.flush();
                    System.out.println(in.readUTF());
                }
            } finally {
                System.out.println("Клиент был закрыт...");
                in.close();
                out.close();
            }
        } catch (IOException e) {
            System.err.println(e);
        }
    }
}