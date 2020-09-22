//package com.jc.fire.mqtt;
//
///**
// * <Description> <br>
// *
// * @author duan.xin<br>
// * @version 1.0<br>
// * @taskId <br>
// * @Date 2020/9/21 14:10
// * @see com.jc.fire.mqtt <br>
// * @since V0.1<br>
// */
//
//import java.util.concurrent.TimeUnit;
//import javax.annotation.PostConstruct;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttDeliveryToken;
//import org.eclipse.paho.client.mqttv3.MqttException;
//import org.eclipse.paho.client.mqttv3.MqttMessage;
//import org.eclipse.paho.client.mqttv3.MqttPersistenceException;
//import org.eclipse.paho.client.mqttv3.MqttTopic;
//import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.stereotype.Component;
//
///**
// * 发布端
// * Title:Server
// * Description: 服务器向多个客户端推送主题，即不同客户端可向服务器订阅相同主题
// *
// */
//@Component
//public class MQTTServer {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MQTTServer.class);
//
//
//    public static final String HOST = "tcp://127.0.0.1:61613";
//
//    private static final String clientId = "server";
//
//    public MqttClient client;
//    public MqttTopic topic;
//
//    public MqttMessage message;
//
//    String Message_Subsrib_To_Publish = "start";
//
//    private static MQTTConnect mqttConnect = new MQTTConnect();
//
//    public MQTTServer() throws MqttException {
//        // MemoryPersistence设置clientId的保存形式，默认为以内存保存
////        client = new MqttClient(HOST, clientId, new MemoryPersistence());
//        connect();
//
//    }
//
//
//    public void connect() throws MqttException {
//        //防止重复创建MQTTClient实例
//        if (client == null) {
//            //就是这里的clientId，服务器用来区分用户的，不能重复
//            // MemoryPersistence设置clientId的保存形式，默认为以内存保存
//            client = new MqttClient(HOST, clientId, new MemoryPersistence());
////            client.setCallback(new PushCallback());
//        }
//        MqttConnectOptions options = mqttConnect.getOptions();
//        //判断拦截状态，这里注意一下，如果没有这个判断，是非常坑的
//        if (!client.isConnected()) {
//            client.connect(options);
//            LOGGER.info("---------------------连接成功");
//        }
//        else {
//            //这里的逻辑是如果连接成功就重新连接
//            client.disconnect();
//            client.connect(mqttConnect.getOptions(options));
//            LOGGER.info("---------------------连接成功");
//        }
//    }
//
//
//    public  boolean publish(MqttTopic topic, MqttMessage message) throws MqttPersistenceException,
//            MqttException {
//
//        MqttDeliveryToken token = topic.publish(message);
//        token.waitForCompletion();
//        System.out.println("message is published completely! "
//                + token.isComplete());
//        return token.isComplete();
//    }
//
//    /**
//     * MQTT发送指令
//     * @param
//     * @param
//     * @return
//     * @throws MqttException
//     */
//    public static void sendMQTTMessage(String topic, String data) throws MqttException {
//
//        MQTTServer server = new MQTTServer();
//        server.topic = server.client.getTopic(topic);
//        server.message = new MqttMessage();
//        //消息等级
//        //level 0：最多一次的传输
//        //level 1：至少一次的传输，(鸡肋)
//        //level 2： 只有一次的传输
//        server.message.setQos(2);
//        //如果重复消费，则把值改为true,然后发送一条空的消息，之前的消息就会覆盖，然后在改为false
//        server.message.setRetained(false);
//
//        server.message.setPayload(data.getBytes());
//        server.publish(server.topic, server.message);
//
//    }
//
//    @PostConstruct
//    public void publishMessage() throws MqttException {
//        while (true) {
//            System.out.println("Message_Subsrib_To_Publish22222:  " + Message_Subsrib_To_Publish);
//            try {
//                TimeUnit.SECONDS.sleep(1);
//                //取出messageMap数据
//                //发送数据
//                sendMQTTMessage("testTopic", "duan.xin test mqtt");
//                // 你的发布主题  ,,,
//                Message_Subsrib_To_Publish = null;
//                System.out.println("Message_Subsrib_To_Publish44444:  " + Message_Subsrib_To_Publish);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}
//
