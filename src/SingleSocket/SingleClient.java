package SingleSocket;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * 客户端
 * 向服务器发送正方形的边长
 * 服务器计算完之后返回正方形的面积
 *
 * @author WSY
 * @date 2020/12/08
 */
public class SingleClient {

    public static void main(String[] args) throws IOException {
        //端口号
        int port = 7000;
        //主机
        String host = "localhost";
        //创建一个socket并将其连接到指定端口号
        Socket socket = new Socket(host,port);

        //通过socket 读取服务器发送的消息
        DataInputStream dis = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        //通过socket 向服务器发送消息
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        Scanner scanner = new Scanner(System.in);
        boolean flag = false;

        while (!flag){
            System.out.println("请输入正方形的长：");
            double length = scanner.nextDouble();
            //向服务器发送数据，实质是向localhost:7000发送数据
            dos.writeDouble(length);
            dos.flush();
            //读取服务器返回的结果
            double area = dis.readDouble();
            System.out.println("服务器返回的计算结果：" + area);
            while (true){
                System.out.println("继续计算？（Y/N）");
                String str = scanner.next();
                if (str.equalsIgnoreCase("Y")){
                    //向服务器发送数据，实质是向localhost:7000发送数据
                    dos.writeInt(1);
                    dos.flush();
                    break;
                }else if (str.equalsIgnoreCase("N")){
                    //向服务器发送数据，实质是向localhost:7000发送数据
                    dos.writeInt(0);
                    dos.flush();
                    flag = true;
                    break;
                }
            }
        }
        socket.close();
    }
}
