package com.dbmigrate.connection.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.dbmigrate.common.annotation.Log;
import com.dbmigrate.common.core.controller.BaseController;
import com.dbmigrate.common.core.domain.AjaxResult;
import com.dbmigrate.common.enums.BusinessType;
import com.dbmigrate.connection.domain.DatabaseConnection;
import com.dbmigrate.connection.service.IDatabaseConnectionService;
import com.dbmigrate.common.utils.poi.ExcelUtil;
import com.dbmigrate.common.core.page.TableDataInfo;

/**
 * 数据源Controller
 *
 * @author wangyong
 * @date 2024-11-07
 */
@RestController
@RequestMapping("/connection/connection")
public class DatabaseConnectionController extends BaseController
{
    @Autowired
    private IDatabaseConnectionService databaseConnectionService;

    /**
     * 查询数据源列表
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:list')")
    @GetMapping("/list")
    public TableDataInfo list(DatabaseConnection databaseConnection)
    {
        startPage();
        List<DatabaseConnection> list = databaseConnectionService.selectDatabaseConnectionList(databaseConnection);
        return getDataTable(list);
    }

    /**
     * 导出数据源列表
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:export')")
    @Log(title = "数据源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, DatabaseConnection databaseConnection)
    {
        List<DatabaseConnection> list = databaseConnectionService.selectDatabaseConnectionList(databaseConnection);
        ExcelUtil<DatabaseConnection> util = new ExcelUtil<DatabaseConnection>(DatabaseConnection.class);
        util.exportExcel(response, list, "数据源数据");
    }

    /**
     * 获取数据源详细信息
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:query')")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(databaseConnectionService.selectDatabaseConnectionById(id));
    }

    /**
     * 新增数据源
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:add')")
    @Log(title = "数据源", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody DatabaseConnection databaseConnection)
    {
        return toAjax(databaseConnectionService.insertDatabaseConnection(databaseConnection));
    }

    /**
     * 修改数据源
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:edit')")
    @Log(title = "数据源", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody DatabaseConnection databaseConnection)
    {
        return toAjax(databaseConnectionService.updateDatabaseConnection(databaseConnection));
    }

    /**
     * 删除数据源
     */
    @PreAuthorize("@ss.hasPermi('connection:connection:remove')")
    @Log(title = "数据源", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(databaseConnectionService.deleteDatabaseConnectionByIds(ids));
    }
}
