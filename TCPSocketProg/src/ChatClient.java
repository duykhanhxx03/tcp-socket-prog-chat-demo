import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Write your server IPv4 address: ");
        String address = scanner.nextLine();
        Socket socket = new Socket(address, 2905);

        DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());
        DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());


        while (true) {
            System.out.println("connected!");
            //Client to Server
            String msgS = scanner.nextLine();
            dataOutputStream.writeUTF(msgS);
            dataOutputStream.flush();

            if (msgS.equals('q')) {
                break;
            }

            //Server to Client
            String msgRcv = dataInputStream.readUTF();
            System.out.println("Server: " + msgRcv);

        }
        dataOutputStream.close();
        dataInputStream.close();
        socket.close();
    }
}
