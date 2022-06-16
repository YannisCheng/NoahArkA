package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table11;
import com.cwj.datasource.mysql.fnclass.repository.Table11Repository;
import com.cwj.datasource.mysql.fnclass.service.Table11Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政12班级表(Table11)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:57
 */
@Service("table11Service")
public class Table11ServiceImpl implements Table11Service {

    @Autowired
    private Table11Repository table11Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table11> findById(Integer id) {
        return table11Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table11> findAll(Sort sort) {
        return table11Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table11> findByPage(Pageable pageable) {
        return table11Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table11> findByIdCard(String idCard) {
        return table11Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table11> findByStuId(String stuId) {
        return table11Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table11 saveOne(Table11 table11) {
        return table11Repository.save(table11);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table11 updateByObj(Table11 table11) {
        Optional<Table11> optional = table11Repository.findById(table11.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table11, optional.get());
            return table11Repository.save(optional.get());
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
        table11Repository.deleteById(id);
    }
}