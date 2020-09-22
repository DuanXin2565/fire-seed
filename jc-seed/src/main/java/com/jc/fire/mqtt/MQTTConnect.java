//package com.jc.fire.mqtt;
//
//import org.eclipse.paho.client.mqttv3.MqttClient;
//import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
//import org.eclipse.paho.client.mqttv3.MqttTopic;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
///**
// * <Description> <br>
// *
// * @author duan.xin<br>
// * @version 1.0<br>
// * @taskId <br>
// * @Date 2020/9/21 14:09
// * @see com.jc.fire.mqtt <br>
// * @since V0.1<br>
// */
//public class MQTTConnect {
//
//    private static final Logger LOGGER = LoggerFactory.getLogger(MQTTConnect.class);
//
//    public static final String HOST = "tcp://127.0.0.1:61613";
//
//    private static final String clientId = "server";
//
//    public MqttClient client;
//    public MqttTopic topic;
//    private String userName = "admin";
//    private String passWord = "password";
//
//    //生成配置对象，用户名，密码等
//    public MqttConnectOptions getOptions() {
//        MqttConnectOptions options = new MqttConnectOptions();
//        options.setCleanSession(false);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
//        options.setConnectionTimeout(10);
//        //设置心跳
//        options.setKeepAliveInterval(20);
//        return options;
//    }
//
//    public MqttConnectOptions getOptions(MqttConnectOptions options) {
//
//        options.setCleanSession(false);
//        options.setUserName(userName);
//        options.setPassword(passWord.toCharArray());
//        options.setConnectionTimeout(10);
//        options.setKeepAliveInterval(20);
//        return options;
//    }
//
//
//}
//
