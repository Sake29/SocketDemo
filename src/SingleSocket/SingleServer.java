package SingleSocket;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 服务器端
 *
 * @author WSY
 * @date 2020/12/08
 */
public class SingleServer {
    public static void main(String[] args) throws IOException {
        //监听的端口号
        int port = 7000;
        //在该端口上创建一个socket
        ServerSocket serverSocket = new ServerSocket(port);
        //监听来自客户端的连接
        Socket socket = serverSocket.accept();
        //通过socket 读取读取客户端发送的消息
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        //通过socket 向客户端发送消息
        DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(socket.getOutputStream()));

        do {
            //获取客户端发送的数据
            double length = dis.readDouble();
            System.out.println("服务器端收到的边长数据：" + length);
            double result = length * length;
            //将计算结果发送给客户端
            dos.writeDouble(result);
            dos.flush();
        } while (dis.readInt() != 0);

        socket.close();
        serverSocket.close();
    }
}
