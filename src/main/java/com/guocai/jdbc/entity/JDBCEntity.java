package com.guocai.jdbc.entity;

import java.io.Serializable;

/**
 * java类简单作用描述
 *
 * @ClassName: JDBCEntity
 * @Package: com.guocai.jdbc.entity
 * @Description: 接收前台数据库连接信息
 * @Author: Sun GuoCai
 * @Version: 1.0
 * @Create: 2018-11-08-9:40
 */
public class JDBCEntity implements Serializable {
    private static final long serialVersionUID = -8322165234020773756L;
    private String tablePrefixName;
    private String tableName;
    private String packageName;
    private String entityName;
    private String host;
    private String port;
    private String sid;
    private String driver;
    private String username;
    private String password;
    private String author;

    public JDBCEntity() {
    }

    public JDBCEntity(String tablePrefixName, String tableName, String packageName, String entityName, String host, String port, String sid, String driver, String username, String password, String author) {
        this.tablePrefixName = tablePrefixName;
        this.tableName = tableName;
        this.packageName = packageName;
        this.entityName = entityName;
        this.host = host;
        this.port = port;
        this.sid = sid;
        this.driver = driver;
        this.username = username;
        this.password = password;
        this.author = author;
    }

    public String getTablePrefixName() {
        return this.tablePrefixName;
    }

    public String getTableName() {
        return this.tableName;
    }

    public String getPackageName() {
        return this.packageName;
    }

    public String getEntityName() {
        return this.entityName;
    }

    public String getHost() {
        return this.host;
    }

    public String getPort() {
        return this.port;
    }

    public String getSid() {
        return this.sid;
    }

    public String getDriver() {
        return this.driver;
    }

    public String getUsername() {
        return this.username;
    }

    public String getPassword() {
        return this.password;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setTablePrefixName(String tablePrefixName) {
        this.tablePrefixName = tablePrefixName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public void setPackageName(String packageName) {
        this.packageName = packageName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setDriver(String driver) {
        this.driver = driver;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof JDBCEntity)) return false;
        final JDBCEntity other = (JDBCEntity) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$tablePrefixName = this.getTablePrefixName();
        final Object other$tablePrefixName = other.getTablePrefixName();
        if (this$tablePrefixName == null ? other$tablePrefixName != null : !this$tablePrefixName.equals(other$tablePrefixName))
            return false;
        final Object this$tableName = this.getTableName();
        final Object other$tableName = other.getTableName();
        if (this$tableName == null ? other$tableName != null : !this$tableName.equals(other$tableName)) return false;
        final Object this$packageName = this.getPackageName();
        final Object other$packageName = other.getPackageName();
        if (this$packageName == null ? other$packageName != null : !this$packageName.equals(other$packageName))
            return false;
        final Object this$entityName = this.getEntityName();
        final Object other$entityName = other.getEntityName();
        if (this$entityName == null ? other$entityName != null : !this$entityName.equals(other$entityName))
            return false;
        final Object this$host = this.getHost();
        final Object other$host = other.getHost();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) return false;
        final Object this$port = this.getPort();
        final Object other$port = other.getPort();
        if (this$port == null ? other$port != null : !this$port.equals(other$port)) return false;
        final Object this$sid = this.getSid();
        final Object other$sid = other.getSid();
        if (this$sid == null ? other$sid != null : !this$sid.equals(other$sid)) return false;
        final Object this$driver = this.getDriver();
        final Object other$driver = other.getDriver();
        if (this$driver == null ? other$driver != null : !this$driver.equals(other$driver)) return false;
        final Object this$username = this.getUsername();
        final Object other$username = other.getUsername();
        if (this$username == null ? other$username != null : !this$username.equals(other$username)) return false;
        final Object this$password = this.getPassword();
        final Object other$password = other.getPassword();
        if (this$password == null ? other$password != null : !this$password.equals(other$password)) return false;
        final Object this$author = this.getAuthor();
        final Object other$author = other.getAuthor();
        if (this$author == null ? other$author != null : !this$author.equals(other$author)) return false;
        return true;
    }

    protected boolean canEqual(final Object other) {
        return other instanceof JDBCEntity;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $tablePrefixName = this.getTablePrefixName();
        result = result * PRIME + ($tablePrefixName == null ? 43 : $tablePrefixName.hashCode());
        final Object $tableName = this.getTableName();
        result = result * PRIME + ($tableName == null ? 43 : $tableName.hashCode());
        final Object $packageName = this.getPackageName();
        result = result * PRIME + ($packageName == null ? 43 : $packageName.hashCode());
        final Object $entityName = this.getEntityName();
        result = result * PRIME + ($entityName == null ? 43 : $entityName.hashCode());
        final Object $host = this.getHost();
        result = result * PRIME + ($host == null ? 43 : $host.hashCode());
        final Object $port = this.getPort();
        result = result * PRIME + ($port == null ? 43 : $port.hashCode());
        final Object $sid = this.getSid();
        result = result * PRIME + ($sid == null ? 43 : $sid.hashCode());
        final Object $driver = this.getDriver();
        result = result * PRIME + ($driver == null ? 43 : $driver.hashCode());
        final Object $username = this.getUsername();
        result = result * PRIME + ($username == null ? 43 : $username.hashCode());
        final Object $password = this.getPassword();
        result = result * PRIME + ($password == null ? 43 : $password.hashCode());
        final Object $author = this.getAuthor();
        result = result * PRIME + ($author == null ? 43 : $author.hashCode());
        return result;
    }

    public String toString() {
        return "JDBCEntity(tablePrefixName=" + this.getTablePrefixName() + ", tableName=" + this.getTableName() + ", packageName=" + this.getPackageName() + ", entityName=" + this.getEntityName() + ", host=" + this.getHost() + ", port=" + this.getPort() + ", sid=" + this.getSid() + ", driver=" + this.getDriver() + ", username=" + this.getUsername() + ", password=" + this.getPassword() + ", author=" + this.getAuthor() + ")";
    }
}
