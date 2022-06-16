package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table7;
import com.cwj.datasource.mysql.fnclass.repository.Table7Repository;
import com.cwj.datasource.mysql.fnclass.service.Table7Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政8班级表(Table7)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:37:54
 */
@Service("table7Service")
public class Table7ServiceImpl implements Table7Service {

    @Autowired
    private Table7Repository table7Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table7> findById(Integer id) {
        return table7Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table7> findAll(Sort sort) {
        return table7Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table7> findByPage(Pageable pageable) {
        return table7Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table7> findByIdCard(String idCard) {
        return table7Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table7> findByStuId(String stuId) {
        return table7Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table7 saveOne(Table7 table7) {
        return table7Repository.save(table7);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table7 updateByObj(Table7 table7) {
        Optional<Table7> optional = table7Repository.findById(table7.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table7, optional.get());
            return table7Repository.save(optional.get());
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
        table7Repository.deleteById(id);
    }
}