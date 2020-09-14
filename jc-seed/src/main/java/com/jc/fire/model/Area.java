package com.jc.fire.model;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * area
 * @author 
 */
@Data
public class Area implements Serializable {
    /**
     * 区域id
     */
    private Long areaId;

    /**
     * 区域名称
     */
    private String areaName;

    /**
     * 上级区域id
     */
    private Long parentId;

    /**
     * 区域类型
     */
    private String areaType;

    /**
     * 创建人id
     */
    private Long userId;

    /**
     * 日期
     */
    private Date stateDate;

    /**
     * 是否删除
     */
    private Boolean isDelete;

    private static final long serialVersionUID = 1L;
}