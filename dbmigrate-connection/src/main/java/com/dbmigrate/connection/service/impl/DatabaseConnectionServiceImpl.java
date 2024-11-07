package com.dbmigrate.connection.service.impl;

import java.util.List;
import com.dbmigrate.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.dbmigrate.connection.mapper.DatabaseConnectionMapper;
import com.dbmigrate.connection.domain.DatabaseConnection;
import com.dbmigrate.connection.service.IDatabaseConnectionService;

/**
 * 数据源Service业务层处理
 * 
 * @author wangyong
 * @date 2024-11-06
 */
@Service
public class DatabaseConnectionServiceImpl implements IDatabaseConnectionService 
{
    @Autowired
    private DatabaseConnectionMapper databaseConnectionMapper;

    /**
     * 查询数据源
     * 
     * @param id 数据源主键
     * @return 数据源
     */
    @Override
    public DatabaseConnection selectDatabaseConnectionById(Long id)
    {
        return databaseConnectionMapper.selectDatabaseConnectionById(id);
    }

    /**
     * 查询数据源列表
     * 
     * @param databaseConnection 数据源
     * @return 数据源
     */
    @Override
    public List<DatabaseConnection> selectDatabaseConnectionList(DatabaseConnection databaseConnection)
    {
        return databaseConnectionMapper.selectDatabaseConnectionList(databaseConnection);
    }

    /**
     * 新增数据源
     * 
     * @param databaseConnection 数据源
     * @return 结果
     */
    @Override
    public int insertDatabaseConnection(DatabaseConnection databaseConnection)
    {
        databaseConnection.setCreateTime(DateUtils.getNowDate());
        return databaseConnectionMapper.insertDatabaseConnection(databaseConnection);
    }

    /**
     * 修改数据源
     * 
     * @param databaseConnection 数据源
     * @return 结果
     */
    @Override
    public int updateDatabaseConnection(DatabaseConnection databaseConnection)
    {
        databaseConnection.setUpdateTime(DateUtils.getNowDate());
        return databaseConnectionMapper.updateDatabaseConnection(databaseConnection);
    }

    /**
     * 批量删除数据源
     * 
     * @param ids 需要删除的数据源主键
     * @return 结果
     */
    @Override
    public int deleteDatabaseConnectionByIds(Long[] ids)
    {
        return databaseConnectionMapper.deleteDatabaseConnectionByIds(ids);
    }

    /**
     * 删除数据源信息
     * 
     * @param id 数据源主键
     * @return 结果
     */
    @Override
    public int deleteDatabaseConnectionById(Long id)
    {
        return databaseConnectionMapper.deleteDatabaseConnectionById(id);
    }
}
