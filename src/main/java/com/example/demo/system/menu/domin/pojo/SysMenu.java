package com.example.demo.system.menu.domin.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

/**
 * 菜单权限表
 * @TableName sys_menu
 */
@TableName(value ="sys_menu")
public class SysMenu implements Serializable {
    /**
     * 菜单ID
     */
    @TableId(type = IdType.AUTO)
    private Long id;

    /**
     * 菜单名称
     */
    private String name;

    /**
     * 权限标识
     */
    private String permission;

    /**
     * 菜单类型
     */
    private Integer type;

    /**
     * 显示顺序
     */
    private Integer sort;

    /**
     * 父菜单ID
     */
    private Long parentId;

    /**
     * 路由地址
     */
    private String path;

    /**
     * 菜单图标
     */
    private String icon;

    /**
     * 组件路径
     */
    private String component;

    /**
     * 组件名
     */
    private String componentName;

    /**
     * 菜单状态
     */
    private Integer status;

    /**
     * 是否可见
     */
    private Boolean visible;

    /**
     * 是否缓存
     */
    private Boolean keepAlive;

    /**
     * 是否总是显示
     */
    private Boolean alwaysShow;

    /**
     * 创建者
     */
    private String creator;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新者
     */
    private String updater;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private Boolean deleted;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 菜单ID
     */
    public Long getId() {
        return id;
    }

    /**
     * 菜单ID
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 菜单名称
     */
    public String getName() {
        return name;
    }

    /**
     * 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 权限标识
     */
    public String getPermission() {
        return permission;
    }

    /**
     * 权限标识
     */
    public void setPermission(String permission) {
        this.permission = permission;
    }

    /**
     * 菜单类型
     */
    public Integer getType() {
        return type;
    }

    /**
     * 菜单类型
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * 显示顺序
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 显示顺序
     */
    public void setSort(Integer sort) {
        this.sort = sort;
    }

    /**
     * 父菜单ID
     */
    public Long getParentId() {
        return parentId;
    }

    /**
     * 父菜单ID
     */
    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    /**
     * 路由地址
     */
    public String getPath() {
        return path;
    }

    /**
     * 路由地址
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 菜单图标
     */
    public String getIcon() {
        return icon;
    }

    /**
     * 菜单图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 组件路径
     */
    public String getComponent() {
        return component;
    }

    /**
     * 组件路径
     */
    public void setComponent(String component) {
        this.component = component;
    }

    /**
     * 组件名
     */
    public String getComponentName() {
        return componentName;
    }

    /**
     * 组件名
     */
    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    /**
     * 菜单状态
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 菜单状态
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 是否可见
     */
    public Boolean getVisible() {
        return visible;
    }

    /**
     * 是否可见
     */
    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    /**
     * 是否缓存
     */
    public Boolean getKeepAlive() {
        return keepAlive;
    }

    /**
     * 是否缓存
     */
    public void setKeepAlive(Boolean keepAlive) {
        this.keepAlive = keepAlive;
    }

    /**
     * 是否总是显示
     */
    public Boolean getAlwaysShow() {
        return alwaysShow;
    }

    /**
     * 是否总是显示
     */
    public void setAlwaysShow(Boolean alwaysShow) {
        this.alwaysShow = alwaysShow;
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
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 创建时间
     */
    public void setCreateTime(Date createTime) {
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
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 更新时间
     */
    public void setUpdateTime(Date updateTime) {
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
}