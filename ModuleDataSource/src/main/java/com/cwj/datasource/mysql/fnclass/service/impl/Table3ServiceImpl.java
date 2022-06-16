package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table3;
import com.cwj.datasource.mysql.fnclass.repository.Table3Repository;
import com.cwj.datasource.mysql.fnclass.service.Table3Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政4班级表(Table3)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:38:48
 */
@Service("table3Service")
public class Table3ServiceImpl implements Table3Service {

    @Autowired
    private Table3Repository table3Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table3> findById(Integer id) {
        return table3Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table3> findAll(Sort sort) {
        return table3Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table3> findByPage(Pageable pageable) {
        return table3Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table3> findByIdCard(String idCard) {
        return table3Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table3> findByStuId(String stuId) {
        return table3Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table3 saveOne(Table3 table3) {
        return table3Repository.save(table3);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table3 updateByObj(Table3 table3) {
        Optional<Table3> optional = table3Repository.findById(table3.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table3, optional.get());
            return table3Repository.save(optional.get());
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
        table3Repository.deleteById(id);
    }
}