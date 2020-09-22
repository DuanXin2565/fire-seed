//package com.jc.fire.netty.port;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.stereotype.Component;
//
///**
// * <Description> <br>
// *yml文件
// * @author duan.xin<br>
// * @version 1.0<br>
// * @taskId <br>
// * @Date 2020/9/14 14:49
// * @see <br>
// * @since V0.1<br>
// */
//@Component
//@ConfigurationProperties(prefix = "device")
//public class Port {
//
//    /**
//     * 思迪的端口
//     */
//    private int sdPort;
//
//    /**
//     * 拓扑索尔
//     */
//    private int tpslPort;
//
//    /**
//     * 复旦网络
//     */
//    private int fdPort;
//
//    /**
//     * 测试用的端口
//     */
//    private int testPort;
//
//    public int getSdPort() {
//        return sdPort;
//    }
//
//    public void setSdPort(int sdPort) {
//        this.sdPort = sdPort;
//    }
//
//    public int getTpslPort() {
//        return tpslPort;
//    }
//
//    public void setTpslPort(int tpslPort) {
//        this.tpslPort = tpslPort;
//    }
//
//    public int getFdPort() {
//        return fdPort;
//    }
//
//    public void setFdPort(int fdPort) {
//        this.fdPort = fdPort;
//    }
//
//    /**
//     * 获取所有的端口
//     * @return
//     */
//    public List<Integer> getPortList() {
//        List<Integer> portList = new ArrayList<>();
//        portList.add(sdPort);
//        portList.add(tpslPort);
//        portList.add(fdPort);
//        portList.add(testPort);
//        return portList;
//    }
//
//    public int getTestPort() {
//        return testPort;
//    }
//
//    public void setTestPort(int testPort) {
//        this.testPort = testPort;
//    }
//}
