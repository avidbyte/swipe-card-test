package com.example.udptest.server;

import com.example.udptest.entity.ControllerReceiveMessage;
import com.example.udptest.entity.DoorReceiveMessage;
import com.example.udptest.util.HexConvert;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.*;

/**
 * @author aaron
 * @since 2021-02-26
 */
@Slf4j
public class UdpSocketServer {

    private static DatagramSocket datagramSocket;
    private static int maxUdpDataSize = 128;

    public void startSocketServer(int port) throws UnknownHostException, SocketException {

        InetAddress inetAddress = InetAddress.getLocalHost();

        SocketAddress socketAddress = new InetSocketAddress(inetAddress, port);
        //创建服务器端DatagramSocket，指定端口
        datagramSocket = new DatagramSocket(socketAddress);
//        datagramSocket.connect(socketAddress);
        log.info("========> 创建数据报，用于接收客户端发送的数据 <========");
        while (true) {
            byte[] buffer = new byte[maxUdpDataSize];
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
            try {
                log.info("========> 端口 {} 等待接收消息 <========", port);
                datagramSocket.receive(packet);
                // 接收到的UDP信息，然后解码
                buffer = packet.getData();
                InetAddress address = packet.getAddress();
                String targetIp = address.getHostAddress();
                int targetPort = packet.getPort();
                log.info("接收--来自[" + targetIp + ":" + targetPort + "]的报文:" + HexConvert.BinaryToHexString(buffer));
                byte[] msg;
                if (buffer[1] == ((byte) 0x40)) {
                    msg = new DoorReceiveMessage().getMessage();
                } else {
                    msg = new ControllerReceiveMessage().getMessage();
                }
                sendMsg(targetIp, targetPort, msg);
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }
    }

    public void sendMsg(String ip, int port, byte[] content) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(ip, port);
        DatagramPacket packet = new DatagramPacket(content, content.length, socketAddress);
        String str = HexConvert.BinaryToHexString(content);
        log.info("发送--目标IP[" + ip + ":" + port + "],报文内容:" + str);
        datagramSocket.send(packet);
    }

    public void sendMsg(InetAddress address, int port, byte[] content) throws IOException {
        SocketAddress socketAddress = new InetSocketAddress(address, port);
        DatagramPacket packet = new DatagramPacket(content, content.length, socketAddress);
        String ip = address.getHostAddress();
        String str = HexConvert.BinaryToHexString(content);
        log.info("发送--目标IP[" + ip + ":" + port + "],报文内容:" + str);
        datagramSocket.send(packet);
    }


}
