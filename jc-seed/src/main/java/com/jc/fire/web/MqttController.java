package com.jc.fire.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.jc.fire.service.MqttGateway;
import com.jc.fire.websocket.WebSocketServer;

/**
 * <Description> <br>
 * mqtt触发消息发布
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/22 15:22
 * @see com.jc.fire.web <br>
 * @since V0.1<br>
 */
@RestController
@RequestMapping("/mqtt")
public class MqttController {

    @Autowired
    private MqttGateway mqttGateway;

    /**
     * hello 为发布消息的主题，也可设置为传参；或者通过类似
     * com.jc.fire.mqttsp.JowoiotMqttClient 类设置为循环自动发送
     *
     * @param sendData
     * @return
     */
    @PostMapping("/sendMqtt.do")
    public String sendMqtt(@RequestParam String sendData) {
        mqttGateway.sendToMqtt(sendData, "hello");
        return "OK";

    }


}
