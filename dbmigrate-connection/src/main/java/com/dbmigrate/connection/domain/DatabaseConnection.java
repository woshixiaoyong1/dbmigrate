package com.dbmigrate.connection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dbmigrate.common.annotation.Excel;
import com.dbmigrate.common.core.domain.BaseEntity;

/**
 * 数据源对象 dbmigrate_database_connection
 * 
 * @author wangyong
 * @date 2024-11-06
 */
public class DatabaseConnection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 连接名称 */
    @Excel(name = "连接名称")
    private String name;

    /** 数据库类型 */
    @Excel(name = "数据库类型")
    private String type;

    /** 驱动类名称 */
    @Excel(name = "驱动类名称")
    private String driver;

    /** jdbc-url连接串 */
    @Excel(name = "jdbc-url连接串")
    private String url;

    /** 连接账号 */
    @Excel(name = "连接账号")
    private String username;

    /** 账号密码 */
    @Excel(name = "账号密码")
    private String password;

    /** 数据状态：1有效 2删除 */
    @Excel(name = "数据状态：1有效 2删除")
    private String status;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setDriver(String driver) 
    {
        this.driver = driver;
    }

    public String getDriver() 
    {
        return driver;
    }
    public void setUrl(String url) 
    {
        this.url = url;
    }

    public String getUrl() 
    {
        return url;
    }
    public void setUsername(String username) 
    {
        this.username = username;
    }

    public String getUsername() 
    {
        return username;
    }
    public void setPassword(String password) 
    {
        this.password = password;
    }

    public String getPassword() 
    {
        return password;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("driver", getDriver())
            .append("url", getUrl())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .append("status", getStatus())
            .toString();
    }
}
