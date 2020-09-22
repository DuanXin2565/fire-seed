package com.jc.fire.mqttsp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;
import javax.annotation.PostConstruct;

import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 将订阅到的消息再发布出去
 */
@Component
public class JowoiotMqttClient {

    Logger logger = LoggerFactory.getLogger(JowoiotMqttClient.class.getName());

    //public static Set<String> topicHeartbeats = new HashSet<>();
    public static String topicToServer;

    @Autowired
    MqttClient mqttClient;

    @Autowired
    MqttConnectOptions mqttConnectOptions;

    public static ConcurrentLinkedQueue<List<Map<String, Object>>> payloadQueue = new ConcurrentLinkedQueue<>();


    @Value("${mqtt.qos:2}")
    int qos;

    @Value("${retained:false}")
    boolean retained;

    public void connect() throws MqttException {
        mqttClient.connect(mqttConnectOptions);
    }

    /**
     * 订阅消息
     */
    public void subscribe() {
        try {
            mqttClient.subscribe("hello");
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable throwable) {
                    System.out.println("connectionLost");
                }

                @Override
                public void messageArrived(String s, MqttMessage mqttMessage) throws Exception {
                    System.out.println("s:" + s);
                    //接收消息
                    byte[] payload = mqttMessage.getPayload();
                    String message = new String(payload, "utf-8");
                    System.out.println("message:" + message);
                    //处理消息
                    List<Map<String, Object>> mapList = new ArrayList<>();
                    Map<String, Object> map = new HashMap<>();
                    map.put("message", message);
                    mapList.add(map);
                    //数据放入容器payloadQueue
                    payloadQueue.offer(mapList);
                    //当取得订阅的消息后再把它推送出去
                    publish();
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken iMqttDeliveryToken) {
                    System.out.println("deliveryComplete");
                }
            });
        }
        catch (MqttException e) {

            e.printStackTrace();
        }

    }

    /**
     * 发布消息
     * @throws MqttException
     */
    public void publish() throws MqttException {
        //取出messageMap数据
        List<Map<String, Object>> poll = payloadQueue.poll();
//        while (poll != null && poll.size() != 0) {
//
//        }
        if (poll != null && poll.size() != 0) {
            try {
                TimeUnit.SECONDS.sleep(2);
                //发送数据
                System.out.println("poll.toString().getBytes(): ");
                // 打印需要发送的数据
                if (poll != null) {
                    mqttClient.publish("jowoiot/toServer/bruce/lptestpub", poll.toString().getBytes(), qos, retained);
                    logger.info("publish success :\t topicToServer:" + "jowoiot/toServer/bruce/lptestpub" + "\npayload:" + poll.toString());
                }
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    @PostConstruct
    public void start() throws MqttException {
        connect();
        subscribe();
//        publish();
    }
}
