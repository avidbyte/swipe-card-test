//package com.example.udptest.server;
//
//
//import com.example.udptest.entity.DoorSendMessage;
//import com.example.udptest.util.HexConvert;
//import com.example.udptest.util.IcCardUtil;
//import lombok.extern.slf4j.Slf4j;
//
//import javax.servlet.ServletContextEvent;
//import javax.servlet.ServletContextListener;
//import javax.servlet.annotation.WebListener;
//import java.io.IOException;
//import java.net.*;
//
///**
// * @author aaron
// * @since 2021-02-25
// */
//
//@Slf4j
//@WebListener
//public class UdpListener implements ServletContextListener {
//
//    /**
//     * 接收端口
//     */
//    private static int RECEIVE_PORT = 60000;
//
//    /**
//     * 发送端口
//     */
//    private static int SEND_PORT = 60000;
//
//    private static int maxUdpDataSize = 128;
//
//
//    @Override
//    public void contextInitialized(ServletContextEvent servletContextEvent) {
//        log.info("========> 监听UDP数据包，监听端口 {} <======== ", RECEIVE_PORT);
//        try {
//            executeUdpMsg(RECEIVE_PORT, SEND_PORT);
//        } catch (SocketException | UnknownHostException e) {
//            log.error(e.getMessage());
//        }
//    }
//
//    private void executeUdpMsg(int receivePort, int sendPort) throws SocketException, UnknownHostException {
//        InetAddress inetAddress = InetAddress.getLocalHost();
//        SocketAddress receiveSocketAddress = new InetSocketAddress(receivePort);
//        //创建服务器端DatagramSocket，指定端口
//        DatagramSocket datagramSocket = new DatagramSocket(receiveSocketAddress);
//        log.info("========> 创建数据报，用于接收客户端发送的数据 <========");
//        while (true) {
//            byte[] buffer = new byte[maxUdpDataSize];
//            DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
//            try {
//                log.info("========> 端口 {} 等待接收消息 <========", receivePort);
//                datagramSocket.receive(packet);
//                // 接收到的UDP信息，然后解码
//                buffer = packet.getData();
//                InetAddress address = packet.getAddress();
//                String targetIp = address.getHostAddress();
//                int targetPort = packet.getPort();
//                log.info("===接收到来自[" + targetIp + ":" + targetPort + "]的消息:" + HexConvert.BinaryToHexString(buffer));
//                if (IcCardUtil.judgeController(buffer)) {
//                    if (IcCardUtil.judgeRecordType(buffer)) {
//                        SocketAddress sendMsgSocketAddress = new InetSocketAddress(inetAddress, sendPort);
//                        UdpClient.send(datagramSocket, sendMsgSocketAddress, new DoorSendMessage().getMessage());
//                        String msg = HexConvert.BinaryToHexString(new DoorSendMessage().getMessage());
//                        InetSocketAddress addr = (InetSocketAddress) sendMsgSocketAddress;
//                        log.info("===发送IP:" + addr.getAddress() + ",发送端口:" + addr.getPort() + ",目标IP:" + targetIp + ",目标端口:" + targetPort + ",发送的报文:" + msg);
//                    }
//                }
//            } catch (IOException e) {
//                log.error(e.getMessage());
//            }
//        }
//    }
//
//
//    @Override
//    public void contextDestroyed(ServletContextEvent servletContextEvent) {
//        log.info("========> 关闭监听UDP数据包，监听端口 {} <======== ", RECEIVE_PORT);
//    }
//
//}
