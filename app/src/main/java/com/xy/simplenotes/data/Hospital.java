package com.xy.simplenotes.data;

import java.io.Serializable;
import java.util.Date;

/**
 * 医院表
 *
 * @author hujianbin
 * @since 1.0
 */
public class Hospital implements Serializable {
    public Hospital() {
    }

    /**
     * 主键
     **/
    private Integer id;
    /**
     * 医院名称
     **/
    private String name;
    /**
     * 医院地址
     **/
    private String address;
    /**
     * 医院级别,0一甲，1二甲，2三甲，3卫生所
     **/
    private Integer level;
    /**
     * 医院简介
     **/
    private String description;
    /**
     * 电话
     **/
    private String phone;
    /**
     * 经度
     **/
    private String longitude;
    /**
     * 纬度
     **/
    private String latitude;
    /**
     * 医院标示
     **/
    private String marking;
    /**
     * 医院在系统内的标识，0无效，1有效
     **/
    private Integer status;
    /**
     * 录入系统时间
     **/
    private Date createTime;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getLevel() {
        return level;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getMarking() {
        return marking;
    }

    public void setMarking(String marking) {
        this.marking = marking;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getStatus() {
        return status;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateTime() {
        return createTime;
    }
}
