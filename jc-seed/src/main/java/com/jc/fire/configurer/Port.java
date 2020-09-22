package com.jc.fire.configurer;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * <Description> <br>
 *
 * @author duan.xin<br>
 * @version 1.0<br>
 * @taskId <br>
 * @Date 2020/9/15 9:34
 * @see com.jc.fire.configurer <br>
 * @since V0.1<br>
 */
@Configuration
public class Port {


    @Value("${device.sdPort:}")
    public int sdPort;



    @Value("${device.testPort:}")
    private int testPort;


    public int getSdPort() {
        return sdPort;
    }

    public void setSdPort(int sdPort) {
        this.sdPort = sdPort;
    }


    public int getTestPort() {
        return testPort;
    }

    public void setTestPort(int testPort) {
        this.testPort = testPort;
    }

    /**
     * 获取所有的端口
     * @return
     */
    public List<Integer> getPortList() {
        List<Integer> portList = new ArrayList<>();
        portList.add(sdPort);
        portList.add(testPort);
        return portList;
    }

}
