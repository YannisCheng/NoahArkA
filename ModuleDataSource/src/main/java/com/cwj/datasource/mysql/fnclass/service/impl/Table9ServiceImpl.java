package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table9;
import com.cwj.datasource.mysql.fnclass.repository.Table9Repository;
import com.cwj.datasource.mysql.fnclass.service.Table9Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政10班级表(Table9)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:41:55
 */
@Service("table9Service")
public class Table9ServiceImpl implements Table9Service {

    @Autowired
    private Table9Repository table9Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table9> findById(Integer id) {
        return table9Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table9> findAll(Sort sort) {
        return table9Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table9> findByPage(Pageable pageable) {
        return table9Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table9> findByIdCard(String idCard) {
        return table9Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table9> findByStuId(String stuId) {
        return table9Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table9 saveOne(Table9 table9) {
        return table9Repository.save(table9);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table9 updateByObj(Table9 table9) {
        Optional<Table9> optional = table9Repository.findById(table9.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table9, optional.get());
            return table9Repository.save(optional.get());
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
        table9Repository.deleteById(id);
    }
}