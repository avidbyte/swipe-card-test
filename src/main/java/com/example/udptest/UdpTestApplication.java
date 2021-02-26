package com.example.udptest;

import com.example.udptest.server.UdpSocketServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.SocketException;
import java.net.UnknownHostException;

/**
 * @author aaron
 */
@SpringBootApplication
public class UdpTestApplication {

    public static void main(String[] args) throws SocketException, UnknownHostException {
        SpringApplication.run(UdpTestApplication.class, args);

        //启动socket服务
        UdpSocketServer server = new UdpSocketServer();
        server.startSocketServer(50000);

    }

}
