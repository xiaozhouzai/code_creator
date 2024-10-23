package com.example.demo.system.user.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.tangzc.autotable.annotation.PrimaryKey;
import com.tangzc.autotable.annotation.mysql.MysqlCharset;
import com.tangzc.autotable.annotation.mysql.MysqlEngine;
import com.tangzc.autotable.annotation.mysql.MysqlTypeConstant;
import com.tangzc.mpe.autotable.annotation.Column;
import com.tangzc.mpe.autotable.annotation.ColumnId;
import com.tangzc.mpe.autotable.annotation.Table;
import jakarta.validation.constraints.Pattern;

import java.io.Serializable;
import java.time.LocalDateTime;


@Table(value = "sys_users", comment = "用户表")
@MysqlCharset(charset = "utf8mb4", collate = "utf8mb4_general_ci")  // 指定表的编码
@MysqlEngine("myisam") // 指定表的存储引擎
public class SysUsers implements Serializable {
    /**
     * 用户ID
     */
    @PrimaryKey(false)  // 自增
    @ColumnId(mode = IdType.ASSIGN_ID, comment = "id主键", type = MysqlTypeConstant.BIGINT) // true 为自增，false 为不自增
    private Long id;

    /**
     * 用户账号
     */
    // 指定字段长度,指定类型
    @Column(length = 100, type = MysqlTypeConstant.VARCHAR,comment = "用户账号")
    private String username;

    /**
     * 密码
     */
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "密码")
    private String password;

    /**
     * 用户昵称
     */
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "用户昵称")
    private String nickname;

    /**
     * 用户邮箱
     */
    @Pattern(regexp = "^\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*$", message = "邮箱格式不正确")
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Column(length = 11, type = MysqlTypeConstant.VARCHAR,comment = "手机号码")
    @Pattern(regexp = "^\\d{11}$", message = "手机号码格式不正确")
    private String mobile;

    /**
     * 用户性别
     */
    @Column(type = MysqlTypeConstant.TINYINT,defaultValue = "0",notNull = true,comment = "性别")
    private Integer sex;

    /**
     * 头像地址
     */
    @Column(length = 512, type = MysqlTypeConstant.VARCHAR,comment = "头像")
    private String avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Column(type = MysqlTypeConstant.TINYINT,defaultValue = "0",comment = "帐号状态",notNull = true)
    private Integer status;

    /**
     * 最后登录IP
     */
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "最后登录IP")
    private String loginIp;

    /**
     * 最后登录时间
     */
    @Column(length = 6, type = MysqlTypeConstant.DATETIME,comment = "最后登录时间")
    private LocalDateTime loginDate;

    /**
     * 创建者
     */
    @TableField(fill = FieldFill.INSERT)
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "创建者")
    private String creator;

    /**
     * 创建时间
     */
    @TableField(fill=FieldFill.INSERT)
    @Column(length = 6, type = MysqlTypeConstant.DATETIME,comment = "创建时间")
    private LocalDateTime createTime;

    /**
     * 更新者
     */
    @TableField(fill = FieldFill.UPDATE)
    @Column(length = 255, type = MysqlTypeConstant.VARCHAR,comment = "更新者")
    private String updater;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.UPDATE)
    @Column(length = 6, type = MysqlTypeConstant.DATETIME,comment = "更新时间")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(type = MysqlTypeConstant.BIT,defaultValue = "b'0'",notNull = true,comment = "是否删除")
    private Boolean deleted;

    /* 两种忽略字段的方式 */
    // 忽略该字段，即不参与建表也不参与查询
    @TableField(exist = false)
    // 仅仅忽略该字段不参与建表，但是会参与查询逻辑。所以如果表里没有该字段会报错找不到列
//    @Ignore
    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 用户ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 用户账号
     */
    public String getUsername() {
        return username;
    }

    /**
     * 用户账号
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 密码
     */
    public String getPassword() {
        return password;
    }

    /**
     * 密码
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * 用户昵称
     */
    public String getNickname() {
        return nickname;
    }

    /**
     * 用户昵称
     */
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    /**
     * 用户邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 用户邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 手机号码
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * 手机号码
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    /**
     * 用户性别
     */
    public Integer getSex() {
        return sex;
    }

    /**
     * 用户性别
     */
    public void setSex(Integer sex) {
        this.sex = sex;
    }

    /**
     * 头像地址
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 头像地址
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 帐号状态（0正常 1停用）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 帐号状态（0正常 1停用）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 最后登录IP
     */
    public String getLoginIp() {
        return loginIp;
    }

    /**
     * 最后登录IP
     */
    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    /**
     * 最后登录时间
     */
    public LocalDateTime getLoginDate() {
        return loginDate;
    }

    /**
     * 最后登录时间
     */
    public void setLoginDate(LocalDateTime loginDate) {
        this.loginDate = loginDate;
    }

    /**
     * 创建者
     */
    public String getCreator() {
        return creator;
    }

    /**
     * 创建者
     */
    public void setCreator(String creator) {
        this.creator = creator;
    }

    /**
     * 创建时间
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 更新者
     */
    public String getUpdater() {
        return updater;
    }

    /**
     * 更新者
     */
    public void setUpdater(String updater) {
        this.updater = updater;
    }

    /**
     * 更新时间
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 是否删除
     */
    public Boolean getDeleted() {
        return deleted;
    }

    /**
     * 是否删除
     */
    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        SysUsers other = (SysUsers) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getNickname() == null ? other.getNickname() == null : this.getNickname().equals(other.getNickname()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex()))
            && (this.getAvatar() == null ? other.getAvatar() == null : this.getAvatar().equals(other.getAvatar()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getLoginIp() == null ? other.getLoginIp() == null : this.getLoginIp().equals(other.getLoginIp()))
            && (this.getLoginDate() == null ? other.getLoginDate() == null : this.getLoginDate().equals(other.getLoginDate()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getUpdater() == null ? other.getUpdater() == null : this.getUpdater().equals(other.getUpdater()))
            && (this.getUpdateTime() == null ? other.getUpdateTime() == null : this.getUpdateTime().equals(other.getUpdateTime()))
            && (this.getDeleted() == null ? other.getDeleted() == null : this.getDeleted().equals(other.getDeleted()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getNickname() == null) ? 0 : getNickname().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
        result = prime * result + ((getAvatar() == null) ? 0 : getAvatar().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getLoginIp() == null) ? 0 : getLoginIp().hashCode());
        result = prime * result + ((getLoginDate() == null) ? 0 : getLoginDate().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getUpdater() == null) ? 0 : getUpdater().hashCode());
        result = prime * result + ((getUpdateTime() == null) ? 0 : getUpdateTime().hashCode());
        result = prime * result + ((getDeleted() == null) ? 0 : getDeleted().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", username=").append(username);
        sb.append(", password=").append(password);
        sb.append(", nickname=").append(nickname);
        sb.append(", email=").append(email);
        sb.append(", mobile=").append(mobile);
        sb.append(", sex=").append(sex);
        sb.append(", avatar=").append(avatar);
        sb.append(", status=").append(status);
        sb.append(", loginIp=").append(loginIp);
        sb.append(", loginDate=").append(loginDate);
        sb.append(", creator=").append(creator);
        sb.append(", createTime=").append(createTime);
        sb.append(", updater=").append(updater);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", deleted=").append(deleted);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}