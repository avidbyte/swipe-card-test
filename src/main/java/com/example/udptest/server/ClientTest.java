package com.example.udptest.server;

import com.example.udptest.entity.ControllerReceiveMessage;
import com.example.udptest.entity.DoorSendMessage;
import com.example.udptest.util.HexConvert;
import lombok.extern.slf4j.Slf4j;

import java.net.*;

/**
 * @author aaron
 * @since 2021-02-25
 */
@Slf4j
public class ClientTest {

    static int maxUdpDataSize = 128;


    public static void main(String[] args) {
        SocketAddress localAddr = new InetSocketAddress("192.168.1.111", 50000);
        localAddr =  send(localAddr);
        DatagramSocket socket = null;
        try {
            //2.创建发送的Socket对象
            socket = new DatagramSocket(localAddr);
            while (true) {
                byte[] buffer = new byte[maxUdpDataSize];
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                log.info("=======端口 {} 等待接收消息 ======", 50000);
                socket.receive(packet);
                // 接收到的UDP信息，然后解码
                buffer = packet.getData();
                InetAddress address = packet.getAddress();
                String ip = address.getHostAddress();
                int targetPort = packet.getPort();
                log.info("=========接收到来自" + ip + ":" + targetPort + "的消息:" + HexConvert.BinaryToHexString(buffer));
                socket.close();
                if (buffer[1] == ((byte) 0x40)) {
                    localAddr =  openSuccess(localAddr);
                    socket = new DatagramSocket(localAddr);
                } else {
                    localAddr =  send(localAddr);
                    socket = new DatagramSocket(localAddr);
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            socket.close();

        }

    }


    public static SocketAddress send(SocketAddress localAddr) {
        //UDP发送端：一台机器可能有多个IP地址，
        //把数据以本机192.168.1.147地址上13000号端号，发送到192.168.1.149上的14000端口
        //1.创建要用来发送的本地地址
         localAddr = new InetSocketAddress("192.168.1.111", 50000);
        //2.创建发送的Socket对象
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(localAddr);
//            while (true) {
            //3.要发送的数据
            byte arr[] = new ControllerReceiveMessage().getMessage();
            //4.发送数据的目标地址和端口
            SocketAddress destAdd = new InetSocketAddress("192.168.1.111", 20000);
            //5.创建要发送的数据包,指定内容,指定目标地址
            DatagramPacket dp = new DatagramPacket(arr, arr.length, destAdd);
            socket.send(dp);//6.发送
            System.out.println("Send数据已发送:!");
//                Thread.sleep(5000);
            socket.close();
            localAddr = new InetSocketAddress("192.168.1.111", 50000);
            return localAddr;
//            }//end while
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            socket.close();
            localAddr = new InetSocketAddress("192.168.1.111", 50000);
        }
        return localAddr;

    }


    public static SocketAddress openSuccess(SocketAddress localAddr) {
        //UDP发送端：一台机器可能有多个IP地址，
        //把数据以本机192.168.1.147地址上13000号端号，发送到192.168.1.149上的14000端口
        //1.创建要用来发送的本地地址
         localAddr = new InetSocketAddress("192.168.1.111", 50000);
        //2.创建发送的Socket对象
        DatagramSocket socket = null;
        try {
            socket = new DatagramSocket(localAddr);
//            while (true) {
            //3.要发送的数据
            byte arr[] = new DoorSendMessage().getMessage();
            //4.发送数据的目标地址和端口
            SocketAddress destAdd = new InetSocketAddress("192.168.1.111", 20000);
            //5.创建要发送的数据包,指定内容,指定目标地址
            DatagramPacket dp = new DatagramPacket(arr, arr.length, destAdd);
            socket.send(dp);//6.发送
            System.out.println("openSuccess 数据已发送:!");
//                Thread.sleep(5000);
            socket.close();
            localAddr = new InetSocketAddress("192.168.1.111", 50000);
            return localAddr;
//            }//end while
        } catch (Exception e1) {
            e1.printStackTrace();
        } finally {
            socket.close();
            localAddr = new InetSocketAddress("192.168.1.111", 50000);
        }
        return localAddr;
    }


}
