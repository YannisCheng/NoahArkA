package com.cwj.datasource.mysql.service.impl;

import com.cwj.datasource.mysql.entity.TableAll;
import com.cwj.datasource.mysql.dao.TableAllDao;
import com.cwj.datasource.mysql.service.TableAllService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (TableAll)表服务实现类
 *
 * @author makejava
 * @since 2021-09-02 21:28:00
 */
@Service("tableAllService")
public class TableAllServiceImpl implements TableAllService {
    @Resource
    private TableAllDao tableAllDao;

    /**
     * 通过ID查询单条数据
     *
     * @param stuId 主键
     * @return 实例对象
     */
    @Override
    public TableAll queryById(String stuId) {
        return this.tableAllDao.queryById(stuId);
    }

    /**
     * 查询多条数据
     *
     * @param offset 查询起始位置
     * @param limit 查询条数
     * @return 对象列表
     */
    @Override
    public List<TableAll> queryAllByLimit(int offset, int limit) {
        return this.tableAllDao.queryAllByLimit(offset, limit);
    }

    /**
     * 新增数据
     *
     * @param tableAll 实例对象
     * @return 实例对象
     */
    @Override
    public TableAll insert(TableAll tableAll) {
        this.tableAllDao.insert(tableAll);
        return tableAll;
    }

    /**
     * 修改数据
     *
     * @param tableAll 实例对象
     * @return 实例对象
     */
    @Override
    public TableAll update(TableAll tableAll) {
        this.tableAllDao.update(tableAll);
        return this.queryById(tableAll.getStuId());
    }

    /**
     * 通过主键删除数据
     *
     * @param stuId 主键
     * @return 是否成功
     */
    @Override
    public boolean deleteById(String stuId) {
        return this.tableAllDao.deleteById(stuId) > 0;
    }
}