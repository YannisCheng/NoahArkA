package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.Table4;
import com.cwj.datasource.mysql.fnclass.repository.Table4Repository;
import com.cwj.datasource.mysql.fnclass.service.Table4Service;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 行政5班级表(Table4)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:39:47
 */
@Service("table4Service")
public class Table4ServiceImpl implements Table4Service {

    @Autowired
    private Table4Repository table4Repository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<Table4> findById(Integer id) {
        return table4Repository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<Table4> findAll(Sort sort) {
        return table4Repository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<Table4> findByPage(Pageable pageable) {
        return table4Repository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<Table4> findByIdCard(String idCard) {
        return table4Repository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<Table4> findByStuId(String stuId) {
        return table4Repository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table4 saveOne(Table4 table4) {
        return table4Repository.save(table4);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public Table4 updateByObj(Table4 table4) {
        Optional<Table4> optional = table4Repository.findById(table4.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(table4, optional.get());
            return table4Repository.save(optional.get());
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
        table4Repository.deleteById(id);
    }
}