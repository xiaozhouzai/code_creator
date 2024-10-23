package com.example.demo.system.user.domain.vo;

import java.util.List;
import java.util.Map;

public class WeatherVo {
    private String status;
    private String count;
    private String info;
    private String infocode;
    private List<Map<String,String>> lives;

    public WeatherVo() {
    }

    public WeatherVo(String status, String count, String info, String infocode, List<Map<String, String>> lives) {
        this.status = status;
        this.count = count;
        this.info = info;
        this.infocode = infocode;
        this.lives = lives;
    }

    /**
     * 获取
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 获取
     * @return count
     */
    public String getCount() {
        return count;
    }

    /**
     * 设置
     * @param count
     */
    public void setCount(String count) {
        this.count = count;
    }

    /**
     * 获取
     * @return info
     */
    public String getInfo() {
        return info;
    }

    /**
     * 设置
     * @param info
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * 获取
     * @return infocode
     */
    public String getInfocode() {
        return infocode;
    }

    /**
     * 设置
     * @param infocode
     */
    public void setInfocode(String infocode) {
        this.infocode = infocode;
    }

    /**
     * 获取
     * @return lives
     */
    public List<Map<String, String>> getLives() {
        return lives;
    }

    /**
     * 设置
     * @param lives
     */
    public void setLives(List<Map<String, String>> lives) {
        this.lives = lives;
    }
}
