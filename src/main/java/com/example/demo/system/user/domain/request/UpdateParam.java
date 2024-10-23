package com.example.demo.system.user.domain.request;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.Length;

public class UpdateParam {

    @NotNull(message = "系统异常")
    private Long id;

    @NotNull(message = "密码不能为空")
    @Length(min = 6, max = 20, message = "密码长度为6-20位")
    private String password;

    @NotNull(message = "姓名不能为空")
    private String nickname;

    @NotNull(message = "邮箱不能为空")
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式不正确")
    private String email;

    @NotNull(message = "手机号不能为空")
    @Pattern(regexp = "^1[3-9]\\d{9}$", message = "手机号格式不正确")
    private String mobile;


    public UpdateParam() {
    }

    public UpdateParam(Long id, String password, String nickname, String email, String mobile) {
        this.id = id;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
        this.mobile = mobile;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return nickname
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 设置
     * @param nickname
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
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

    public String toString() {
        return "UpdateParam{id = " + id + ", password = " + password + ", nickname = " + nickname + ", email = " + email + ", mobile = " + mobile + "}";
    }
}
