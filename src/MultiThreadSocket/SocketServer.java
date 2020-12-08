package MultiThreadSocket;

import SingleSocket.SingleServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 服务器
 * 为了能让一个服务器端程序能同时为多个客户提供服务
 * 使用了多线程机制
 * 每个客户端的请求都由一个独立的线程进行处理
 *
 * @author WSY
 * @date 2020/12/08
 */
public class SocketServer {
    public static void main(String[] args) throws IOException {
        final int port = 7000;
        int clientNo = 1;
        ServerSocket serverSocket = new ServerSocket(port);
        //创建线程池
        ExecutorService es = Executors.newCachedThreadPool();

        try{
            while (true){
                Socket socket = serverSocket.accept();
                es.execute(new MultiThreadSocketServer(socket,clientNo));
                clientNo++;
            }
        }
        finally {
            serverSocket.close();
        }
    }
}

