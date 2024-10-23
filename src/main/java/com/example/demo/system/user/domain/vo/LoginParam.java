package com.example.demo.system.user.domain.vo;



import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

import java.io.Serializable;

public class LoginParam implements Serializable {

    private static final long serialVersionUID = 1L;

    @NotNull(message = "用户名不能为空")
    private String username;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String password;

    @Pattern(regexp = "^[0-9]{6}$", message = "验证码格式不正确")
    @NotNull(message = "验证码不能为空")
    private String code;

    @Pattern(regexp = "^1[3-9][0-9]{9}$", message = "手机号格式不正确")
    @NotNull(message = "手机号不能为空")
    private String mobile;

    public LoginParam() {
    }

    public LoginParam(String username, String password, String code, String mobile) {
        this.username = username;
        this.password = password;
        this.code = code;
        this.mobile = mobile;
    }

    /**
     * 获取
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     * @return mobile
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 设置
     * @param mobile
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
