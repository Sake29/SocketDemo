package MultiThreadSocket;

import java.io.*;
import java.net.Socket;

public class MultiThreadSocketServer implements Runnable {

    private Socket socket;
    private int clientNo;

    public MultiThreadSocketServer(Socket socket, int clientNo) {
        this.socket = socket;
        this.clientNo = clientNo;
    }

    @Override
    public void run() {
        try {
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
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("与客户端：" + clientNo + "通信结束");
            try{
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
