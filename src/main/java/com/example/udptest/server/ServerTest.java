package com.example.udptest.server;

import com.example.udptest.entity.DoorSendMessage;
import com.example.udptest.util.HexConvert;
import lombok.extern.slf4j.Slf4j;

import java.net.*;

/**
 * @author aaron
 * @since 2021-02-25
 */
@Slf4j
public class ServerTest {


    public static void main(String[] args) {
        int maxUdpDataSize = 128;
        //UDP发送端：一台机器可能有多个IP地址，
        //把数据以本机192.168.1.147地址上13000号端号，发送到192.168.1.149上的14000端口
        //1.创建要用来发送的本地地址
        try {
            SocketAddress localAddr = new InetSocketAddress("192.168.1.111", 50000);
            //2.创建发送的Socket对象
            DatagramSocket socket = new DatagramSocket(localAddr);
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

            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
