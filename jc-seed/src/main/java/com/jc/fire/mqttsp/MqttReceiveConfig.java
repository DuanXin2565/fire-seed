package com.jc.fire.mqttsp;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;
import org.springframework.messaging.MessagingException;

import lombok.Data;

/**
 * 〈一句话功能简述〉<br>
 * 〈MQTT接收消息处理〉
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/21 14:54
 * @see <br>
 * @since V0.1<br>
 */
@Configuration
@EnableIntegration
@Data
public class MqttReceiveConfig {

    @Value("${spring.mqtt.username}")
    private String username;

    @Value("${spring.mqtt.password}")
    private String password;

    @Value("${spring.mqtt.url}")
    private String url;

    @Value("${spring.mqtt.client.id}")
    private String clientId;

    @Value("${spring.mqtt.default.topic}")
    private String topic;

    @Value("${spring.mqtt.completionTimeout}")
    private int completionTimeout;


    @Bean
    public MqttConnectOptions getMqttReceiveConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(username);
        mqttConnectOptions.setServerURIs(new String[]{url});
        mqttConnectOptions.setPassword(password.toCharArray());
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }

    @Bean
    public MqttPahoClientFactory mqttReceiveClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttReceiveConnectOptions());
        return factory;
    }

    /**
     * 接收通道 这里不能直接new一个通道,应该将其绑定数据处理
     * 这样才能接收到同一个topic过来的数据
     *
     * @return
     */
    @Bean
    public MessageChannel mqttInputChannel() {
        DirectChannel dc = new DirectChannel();
        dc.subscribe(handler());
        return dc;
    }

    /**
     * 配置client,监听的topic
     * 可以设置多个inbound()方法，用于监听不同的topic
     *
     * @return
     */
    @Bean
    public MessageProducer inbound() {
        MqttPahoMessageDrivenChannelAdapter adapter =
                new MqttPahoMessageDrivenChannelAdapter(clientId + "_inbound", mqttReceiveClientFactory(),
                        "hello", "hello1");
        adapter.setCompletionTimeout(completionTimeout);
        adapter.setQos(1);
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }

    /**
     * 通过通道获取数据
     *
     * @return
     */
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler handler() {
        return new MessageHandler() {
            @Override
            public void handleMessage(Message<?> message) throws MessagingException {
                String topic = message.getHeaders().get("mqtt_receivedTopic").toString();
                String type = topic.substring(topic.lastIndexOf("/") + 1, topic.length());
                if ("hello".equalsIgnoreCase(topic)) {
                    System.out.println("hello,test message," + message.getPayload().toString());
                }
                else if ("hello1".equalsIgnoreCase(topic)) {
                    System.out.println("hello1,test message," + message.getPayload().toString());
                }
            }
        };
    }
}


