package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table0;
import com.cwj.datasource.mysql.fnclass.repository.Table0Repository;
import com.cwj.datasource.mysql.fnclass.service.Table0Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政1班级表(Table0)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:40
 */
@Service("table0Service")
public class Table0ServiceImpl implements Table0Service {

    @Autowired
    private Table0Repository table0Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table0> findById(Integer id) {
        return table0Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table0> findAll(Sort sort) {
        return table0Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table0> findByPage(Pageable pageable) {
        return table0Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table0> findByIdCard(String idCard) {
        return table0Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table0> findByStuId(String stuId) {
        return table0Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table0 saveOne(Table0 table0) {
        return table0Repository.save(table0);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table0 updateByObj(Table0 table0) {
        Optional<Table0> optional = table0Repository.findById(table0.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table0, optional.get());
            return table0Repository.save(optional.get());
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
        table0Repository.deleteById(id);
    }
}