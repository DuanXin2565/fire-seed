package com.jc.fire.netty.constant;

/**
 * <Description> <br>
 * 推送的告警消息类型定义
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/7 9:46
 * @see  <br>
 * @since V0.1<br>
 */
public class MessageType {

    /**
     * 100 心跳数据
     */
    public static final int HEART_BEAT = 100;

    /**
     * 10 单设备类型
     */
    public static final int SINGLE_TYPE = 10;

    /**
     * 复合设备
     */
    public static final int COMPLEX_TYPE = 12;

    /**
     * 告警设备
     */
    public static final int ALARM_DEVIECE = 20;
}
