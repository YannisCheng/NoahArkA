package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table10;
import com.cwj.datasource.mysql.fnclass.repository.Table10Repository;
import com.cwj.datasource.mysql.fnclass.service.Table10Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政11班级表(Table10)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:57
 */
@Service("table10Service")
public class Table10ServiceImpl implements Table10Service {

    @Autowired
    private Table10Repository table10Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table10> findById(Integer id) {
        return table10Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table10> findAll(Sort sort) {
        return table10Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table10> findByPage(Pageable pageable) {
        return table10Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table10> findByIdCard(String idCard) {
        return table10Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table10> findByStuId(String stuId) {
        return table10Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table10 saveOne(Table10 table10) {
        return table10Repository.save(table10);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table10 updateByObj(Table10 table10) {
        Optional<Table10> optional = table10Repository.findById(table10.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table10, optional.get());
            return table10Repository.save(optional.get());
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
        table10Repository.deleteById(id);
    }
}