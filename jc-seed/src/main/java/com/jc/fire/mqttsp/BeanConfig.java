package com.jc.fire.mqttsp;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/22 11:46
 * @see com.jc.fire.mqttsp <br>
 * @since V0.1<br>
 */

import java.util.UUID;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.persist.MemoryPersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mqtt客户端连接配置信息
 */
@Configuration
public class BeanConfig {

    @Value("${spring.mqtt.username:}")
    private String username;

    @Value("${spring.mqtt.password:}")
    private String password;

    @Value("${spring.mqtt.url:}")
    private String hostUrl;

    @Value("${spring.mqtt.client.id:}")
    private String clientId;

    @Value("${spring.mqtt.default.topic:}")
    private String defaultTopic;

    @Value("${mqtt.connectionTimeout:3}")
    int connectTimeout;

    @Value("${mqtt.keepAliveInterval:10}")
    int keepAliveInterval;


    @Bean
    public MqttConnectOptions mqttConnectOptions() {
        MqttConnectOptions options = new MqttConnectOptions();
        options.setCleanSession(false);
        options.setAutomaticReconnect(true);
        options.setConnectionTimeout(connectTimeout);
        options.setKeepAliveInterval(keepAliveInterval);
        options.setPassword(password.toCharArray());
        options.setUserName(username);
        options.setServerURIs(new String[]{hostUrl});
        return options;
    }

    @Bean
    public MqttClient mqttClient() throws MqttException {
        return new MqttClient(hostUrl, "UPS_" + UUID.randomUUID().toString(), new MemoryPersistence());

    }
}
