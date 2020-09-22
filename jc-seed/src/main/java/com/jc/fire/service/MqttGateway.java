package com.jc.fire.service;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/21 15:01
 * @see com.jc.fire.service <br>
 * @since V0.1<br>
 */
@MessagingGateway(defaultRequestChannel = "mqttOutboundChannel")
public interface MqttGateway {
    /**
     * 发布消息
     * @param data
     * @param topic
     */
    void sendToMqtt(String data, @Header(MqttHeaders.TOPIC) String topic);
}