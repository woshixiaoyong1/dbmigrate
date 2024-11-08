package com.dbmigrate.connection.service;

import java.util.List;
import com.dbmigrate.connection.domain.DatabaseConnection;

/**
 * 数据源Service接口
 * 
 * @author wangyong
 * @date 2024-11-07
 */
public interface IDatabaseConnectionService 
{
    /**
     * 查询数据源
     * 
     * @param id 数据源主键
     * @return 数据源
     */
    public DatabaseConnection selectDatabaseConnectionById(Long id);

    /**
     * 查询数据源列表
     * 
     * @param databaseConnection 数据源
     * @return 数据源集合
     */
    public List<DatabaseConnection> selectDatabaseConnectionList(DatabaseConnection databaseConnection);

    /**
     * 新增数据源
     * 
     * @param databaseConnection 数据源
     * @return 结果
     */
    public int insertDatabaseConnection(DatabaseConnection databaseConnection);

    /**
     * 修改数据源
     * 
     * @param databaseConnection 数据源
     * @return 结果
     */
    public int updateDatabaseConnection(DatabaseConnection databaseConnection);

    /**
     * 批量删除数据源
     * 
     * @param ids 需要删除的数据源主键集合
     * @return 结果
     */
    public int deleteDatabaseConnectionByIds(Long[] ids);

    /**
     * 删除数据源信息
     * 
     * @param id 数据源主键
     * @return 结果
     */
    public int deleteDatabaseConnectionById(Long id);
}
