package com.jc.fire.model;

import java.io.Serializable;

import lombok.Data;

/**
 * areas
 * @author 
 */
@Data
public class Areas implements Serializable {

    private static final long serialVersionUID = -9141487166697875961L;

    private Long id;

    /**
     * 父级Id
     */
    private Long parentId;

    /**
     * 区域类型
     */
    private Byte level;

    /**
     * 区域名称
     */
    private String name;

    /**
     * 全名称
     */
    private String fullName;

    /**
     * 区域名称
     */
    private String shortName;

    /**
     * 省Id/直辖市Id
     */
    private Long provinceId;

    /**
     * 地市Id
     */
    private Long cityId;

    /**
     * 区县Id
     */
    private Long countryId;

    /**
     * 乡镇街道Id
     */
    private Long streetId;

}