package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table5;
import com.cwj.datasource.mysql.fnclass.repository.Table5Repository;
import com.cwj.datasource.mysql.fnclass.service.Table5Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政6班级表(Table5)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:40:29
 */
@Service("table5Service")
public class Table5ServiceImpl implements Table5Service {

    @Autowired
    private Table5Repository table5Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table5> findById(Integer id) {
        return table5Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table5> findAll(Sort sort) {
        return table5Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table5> findByPage(Pageable pageable) {
        return table5Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table5> findByIdCard(String idCard) {
        return table5Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table5> findByStuId(String stuId) {
        return table5Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table5 saveOne(Table5 table5) {
        return table5Repository.save(table5);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table5 updateByObj(Table5 table5) {
        Optional<Table5> optional = table5Repository.findById(table5.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table5, optional.get());
            return table5Repository.save(optional.get());
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
        table5Repository.deleteById(id);
    }
}