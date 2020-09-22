package com.jc.fire.netty.constant;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/3 10:08
 * @see  <br>
 * @since V0.1<br>
 */
public class CommandType {

    /**
     * 预留
     */
    public static final int RESERVED = 0;

    /**
     * 控制
     */
    public static final int CONTROL = 1;

    /**
     * 发送数据
     */
    public static final int SEND_MESSAGE = 2;

    /**
     * 确认
     */
    public static final int CONFIRM = 3;

    /**
     * 请求
     */
    public static final int REQUSET = 4;

    /**
     * 应答
     */
    public static final int ANSWER = 5;

    /**
     * 否认
     */
    public static final int DENIED = 6;

    //---后续为预留字段，厂家自定义
}
