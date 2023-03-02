import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.SQLOutput;
import java.util.Scanner;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(2905);

        Socket socket = serverSocket.accept();

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("connected!");
            //Client to server
            String msgRcv = dataInputStream.readUTF();
            if (msgRcv.equals('q')) {
                break;
            }
            System.out.println("Client: " + msgRcv);

            //Server to Client
            String msgS = scanner.nextLine();
            dataOutputStream.writeUTF(msgS);
            dataOutputStream.flush();
        }
        dataInputStream.close();
        dataOutputStream.close();
        socket.close();
        serverSocket.close();
    }
}