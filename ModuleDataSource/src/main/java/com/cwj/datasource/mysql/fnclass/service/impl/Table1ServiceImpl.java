package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table1;
import com.cwj.datasource.mysql.fnclass.repository.Table1Repository;
import com.cwj.datasource.mysql.fnclass.service.Table1Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政2班级表(Table1)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:42
 */
@Service("table1Service")
public class Table1ServiceImpl implements Table1Service {

    @Autowired
    private Table1Repository table1Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table1> findById(Integer id) {
        return table1Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table1> findAll(Sort sort) {
        return table1Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table1> findByPage(Pageable pageable) {
        return table1Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table1> findByIdCard(String idCard) {
        return table1Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table1> findByStuId(String stuId) {
        return table1Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table1 saveOne(Table1 table1) {
        return table1Repository.save(table1);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table1 updateByObj(Table1 table1) {
        Optional<Table1> optional = table1Repository.findById(table1.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table1, optional.get());
            return table1Repository.save(optional.get());
        }
        return null;
    }


    // --------------------------------------  删  --------------------------------------

    /**
     * 通过主键id删除单条数据
     *
     * @param id 主键
     */
    @Override
    public void deleteById(Integer id) {
        table1Repository.deleteById(id);
    }
}