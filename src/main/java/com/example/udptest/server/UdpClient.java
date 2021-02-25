package com.example.udptest.server;

import com.example.udptest.entity.DoorSendMessage;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.annotation.WebListener;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

/**
 * @author aaron
 * @since 2021-02-25
 */
@Slf4j
@WebListener
public class UdpClient{

//    public static void send(DatagramSocket datagramSocket,SocketAddress socketAddress, byte[] content) {
//        try {
//            //参数1.数据 2.数据长度
//            DatagramPacket packet = new DatagramPacket(content, content.length, socketAddress);
//            datagramSocket.send(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }


    public void send(){
        //UDP发送端：一台机器可能有多个IP地址，
        //把数据以本机192.168.1.147地址上13000号端号，发送到192.168.1.149上的14000端口
        //1.创建要用来发送的本地地址
        try{
            SocketAddress localAddr = new InetSocketAddress( 60000);
            //2.创建发送的Socket对象
            DatagramSocket dSender = new DatagramSocket(localAddr);
            while(true){
                byte buffer[] = new DoorSendMessage().getMessage();//3.要发送的数据
                //4.发送数据的目标地址和端口
                SocketAddress destAdd = new InetSocketAddress( 20000);
                //5.创建要发送的数据包,指定内容,指定目标地址
                DatagramPacket dp = new DatagramPacket(buffer, buffer.length, destAdd);
                dSender.send(dp);//6.发送
                System.out.println("数据已发送:!");
                Thread.sleep(1000);
            }//end while
        }catch(Exception e1){
            e1.printStackTrace();
        }

    }
}
