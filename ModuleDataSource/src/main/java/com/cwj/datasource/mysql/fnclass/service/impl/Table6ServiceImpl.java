package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table6;
import com.cwj.datasource.mysql.fnclass.repository.Table6Repository;
import com.cwj.datasource.mysql.fnclass.service.Table6Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政7班级表(Table6)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:54
 */
@Service("table6Service")
public class Table6ServiceImpl implements Table6Service {

    @Autowired
    private Table6Repository table6Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table6> findById(Integer id) {
        return table6Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table6> findAll(Sort sort) {
        return table6Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table6> findByPage(Pageable pageable) {
        return table6Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table6> findByIdCard(String idCard) {
        return table6Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table6> findByStuId(String stuId) {
        return table6Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table6 saveOne(Table6 table6) {
        return table6Repository.save(table6);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table6 updateByObj(Table6 table6) {
        Optional<Table6> optional = table6Repository.findById(table6.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table6, optional.get());
            return table6Repository.save(optional.get());
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
        table6Repository.deleteById(id);
    }
}