//package com.example.udptest.service;
//
//import com.example.udptest.config.UdpConfig;
//import com.example.udptest.util.HexConvert;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.io.IOException;
//import java.net.*;
//
///**
// * @author aaron
// * @since 2021-02-25
// */
//
//@Slf4j
//@Service
//public class MsgReceiverService {
//
//    @Resource
//    private MsgSenderService msgSenderService;
//
//    @Resource
//    private UdpConfig udpConfig;
//
//
//
//    public void executeUdpMsg() throws SocketException {
//
//        int udpPort = udpConfig.getPort();
//        int maxUdpDataSize = udpConfig.getMaxUdpDataSize();
//        //创建服务器端DatagramSocket，指定端口
//        DatagramSocket socket = new DatagramSocket(udpPort);
//        log.info("=======创建数据报，用于接收客户端发送的数据======");
//        while (true) {
//            byte[] buffer = new byte[maxUdpDataSize];
//            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//            try {
//                log.info("=======端口 {} 等待接收消息 ======", udpPort);
//                socket.receive(packet);
//                // 接收到的UDP信息，然后解码
//                buffer = packet.getData();
//                InetAddress address = packet.getAddress();
//                String ip = address.getHostAddress();
//                int targetPort = packet.getPort();
//                log.info("=========接收到来自" + ip + ":" + targetPort + "的消息:" + HexConvert.BinaryToHexString(buffer));
//                socket.close();
//                SocketAddress localAddr;
//                if (buffer[1] == ((byte) 0x40)) {
//                    localAddr = msgSenderService.sendDoorMsg(targetPort);
//                    break;
//                }else{
//                    localAddr = msgSenderService.sendCrMsg();
//                }
//                socket = new DatagramSocket(localAddr);
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//    }
//
//
//
//}
