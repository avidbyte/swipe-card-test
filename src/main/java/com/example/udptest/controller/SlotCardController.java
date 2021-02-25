package com.example.udptest.controller;

import com.example.udptest.service.MsgReceiverService;
import com.example.udptest.service.MsgSenderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.net.SocketException;

/**
 * @author aaron
 * @since 2021-02-25
 */

@RestController
@RequestMapping("/slot-card")
public class SlotCardController {

    @Resource
    private MsgReceiverService msgReceiverService;

    @Resource
    private MsgSenderService msgSenderService;

    @GetMapping("/test")
    public void test() throws SocketException {
        msgSenderService.sendCrMsg();
        msgReceiverService.executeUdpMsg();
    }

}
