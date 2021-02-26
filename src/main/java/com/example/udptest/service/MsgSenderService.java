//package com.example.udptest.service;
//
//import com.example.udptest.config.UdpConfig;
//import com.example.udptest.entity.ControllerReceiveMessage;
//import com.example.udptest.entity.DoorReceiveMessage;
//import org.springframework.stereotype.Service;
//
//import javax.annotation.Resource;
//import java.net.*;
//
///**
// * 报文发送服务类
// * @author aaron
// * @since 2021-02-25
// */
//@Service
//public class MsgSenderService {
//
//    InetAddress inetAddress = InetAddress.getLocalHost();
//
//    public MsgSenderService() throws UnknownHostException {
//    }
//
//    @Resource
//    private UdpConfig udpConfig;
//
//    public SocketAddress sendCrMsg() {
//        int port = udpConfig.getPort();
//        SocketAddress localAddr = new InetSocketAddress(inetAddress, port);
//        try (DatagramSocket socket = new DatagramSocket(localAddr)) {
//            //3.要发送的数据
//            byte arr[] = new ControllerReceiveMessage().getMessage();
//            //4.发送数据的目标地址和端口
//            SocketAddress destAdd = new InetSocketAddress(inetAddress, 25555);
//            //5.创建要发送的数据包,指定内容,指定目标地址
//            DatagramPacket dp = new DatagramPacket(arr, arr.length, destAdd);
//            //6.发送
//            socket.send(dp);
//            System.out.println("刷卡报文已发送!");
//            socket.close();
//            localAddr = new InetSocketAddress(inetAddress, port);
//            return localAddr;
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//        return new InetSocketAddress(inetAddress, port);
//    }
//
//    public SocketAddress sendDoorMsg(int targetPort) {
//        int port = udpConfig.getPort();
//        SocketAddress localAddr = new InetSocketAddress(inetAddress, port);
//        try (DatagramSocket socket = new DatagramSocket(localAddr)) {
//            //3.要发送的数据
//            byte arr[] = new DoorReceiveMessage().getMessage();
//            //4.发送数据的目标地址和端口
//            SocketAddress destAdd = new InetSocketAddress(inetAddress, targetPort);
//            //5.创建要发送的数据包,指定内容,指定目标地址
//            DatagramPacket dp = new DatagramPacket(arr, arr.length, destAdd);
//            //6.发送
//            socket.send(dp);
//            System.out.println("开门返回报文已发送!");
//            return new InetSocketAddress(inetAddress, port);
//        } catch (Exception e1) {
//            e1.printStackTrace();
//        }
//
//        return new InetSocketAddress(inetAddress, port);
//    }
//}
