package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table8;
import com.cwj.datasource.mysql.fnclass.repository.Table8Repository;
import com.cwj.datasource.mysql.fnclass.service.Table8Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政9班级表(Table8)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:41:13
 */
@Service("table8Service")
public class Table8ServiceImpl implements Table8Service {

    @Autowired
    private Table8Repository table8Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table8> findById(Integer id) {
        return table8Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table8> findAll(Sort sort) {
        return table8Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table8> findByPage(Pageable pageable) {
        return table8Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table8> findByIdCard(String idCard) {
        return table8Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table8> findByStuId(String stuId) {
        return table8Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table8 saveOne(Table8 table8) {
        return table8Repository.save(table8);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table8 updateByObj(Table8 table8) {
        Optional<Table8> optional = table8Repository.findById(table8.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table8, optional.get());
            return table8Repository.save(optional.get());
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
        table8Repository.deleteById(id);
    }
}