package com.example.udptest.controller;

import com.example.udptest.entity.ControllerReceiveMessage;
import com.example.udptest.server.UdpSocketServer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;



/**
 * @author aaron
 * @since 2021-02-25
 */

@RestController
@RequestMapping("/slot-card")
public class SlotCardController {


    @GetMapping("/test")
    public void test(String ip,int port) throws IOException {
        UdpSocketServer udpSocketServer = new UdpSocketServer();
        byte[] msg = new ControllerReceiveMessage().getMessage();
        udpSocketServer.sendMsg(ip,port,msg);
    }

}
