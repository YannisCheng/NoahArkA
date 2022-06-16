package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table2;
import com.cwj.datasource.mysql.fnclass.repository.Table2Repository;
import com.cwj.datasource.mysql.fnclass.service.Table2Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政3班级表(Table2)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:42
 */
@Service("table2Service")
public class Table2ServiceImpl implements Table2Service {

    @Autowired
    private Table2Repository table2Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table2> findById(Integer id) {
        return table2Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table2> findAll(Sort sort) {
        return table2Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table2> findByPage(Pageable pageable) {
        return table2Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table2> findByIdCard(String idCard) {
        return table2Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table2> findByStuId(String stuId) {
        return table2Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table2 saveOne(Table2 table2) {
        return table2Repository.save(table2);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table2 updateByObj(Table2 table2) {
        Optional<Table2> optional = table2Repository.findById(table2.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table2, optional.get());
            return table2Repository.save(optional.get());
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
        table2Repository.deleteById(id);
    }
}