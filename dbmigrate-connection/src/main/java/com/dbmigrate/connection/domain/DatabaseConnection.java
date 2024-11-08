package com.dbmigrate.connection.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.dbmigrate.common.annotation.Excel;
import com.dbmigrate.common.core.domain.BaseEntity;

/**
 * 数据源对象 DBMIGRATE_DATABASE_CONNECTION
 * 
 * @author wangyong
 * @date 2024-11-07
 */
public class DatabaseConnection extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long id;

    /** 数据源名称 */
    @Excel(name = "数据源名称")
    private String name;

    /** 数据库类型 */
    @Excel(name = "数据库类型")
    private String type;

    /** 驱动版本 */
    @Excel(name = "驱动版本")
    private String version;

    /** 驱动类名称 */
    private String driver;

    /** 连接模式：0默认 1专业 */
    private String mode;

    /** 连接地址 */
    private String address;

    /** 连接端口号 */
    private String port;

    /** 数据库名 */
    private String databaseName;

    /** 编码格式 */
    private String characterEncoding;

    /** jdbc-url连接串 */
    private String url;

    /** 连接账号 */
    @Excel(name = "连接账号")
    private String username;

    /** 账号密码 */
    private String password;

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
    public void setVersion(String version) 
    {
        this.version = version;
    }

    public String getVersion() 
    {
        return version;
    }
    public void setDriver(String driver) 
    {
        this.driver = driver;
    }

    public String getDriver() 
    {
        return driver;
    }
    public void setMode(String mode) 
    {
        this.mode = mode;
    }

    public String getMode() 
    {
        return mode;
    }
    public void setAddress(String address) 
    {
        this.address = address;
    }

    public String getAddress() 
    {
        return address;
    }
    public void setPort(String port) 
    {
        this.port = port;
    }

    public String getPort() 
    {
        return port;
    }
    public void setDatabaseName(String databaseName) 
    {
        this.databaseName = databaseName;
    }

    public String getDatabaseName() 
    {
        return databaseName;
    }
    public void setCharacterEncoding(String characterEncoding) 
    {
        this.characterEncoding = characterEncoding;
    }

    public String getCharacterEncoding() 
    {
        return characterEncoding;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("name", getName())
            .append("type", getType())
            .append("version", getVersion())
            .append("driver", getDriver())
            .append("mode", getMode())
            .append("address", getAddress())
            .append("port", getPort())
            .append("databaseName", getDatabaseName())
            .append("characterEncoding", getCharacterEncoding())
            .append("url", getUrl())
            .append("username", getUsername())
            .append("password", getPassword())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
