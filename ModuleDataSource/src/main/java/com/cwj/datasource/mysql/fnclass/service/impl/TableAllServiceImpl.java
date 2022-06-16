package com.cwj.datasource.mysql.fnclass.service.impl;

import com.cwj.datasource.mysql.fnclass.entity.TableAll;
import com.cwj.datasource.mysql.fnclass.repository.TableAllRepository;
import com.cwj.datasource.mysql.fnclass.service.TableAllService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 所有行政班级表(TableAll)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:42:32
 */
@Service("tableAllService")
public class TableAllServiceImpl implements TableAllService {

    @Autowired
    private TableAllRepository tableAllRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<TableAll> findById(Integer id) {
        return tableAllRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<TableAll> findAll(Sort sort) {
        return tableAllRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<TableAll> findByPage(Pageable pageable) {
        return tableAllRepository.findAll(pageable);
    }


    /**
     * 通过 idcard_stu：身份证号 查询单条数据
     *
     * @param idCard 身份证号
     * @return 实例对象
     */
    @Override
    public Optional<TableAll> findByIdCard(String idCard) {
        return tableAllRepository.findByIdcardStu(idCard);
    }


    /**
     * 通过 stu_id：学号 查询单条数据
     *
     * @param stuId 学号
     * @return 实例对象
     */
    @Override
    public Optional<TableAll> findByStuId(String stuId) {
        return tableAllRepository.findByStuId(stuId);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public TableAll saveOne(TableAll tableAll) {
        return tableAllRepository.save(tableAll);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public TableAll updateByObj(TableAll tableAll) {
        Optional<TableAll> optional = tableAllRepository.findById(tableAll.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(tableAll, optional.get());
            return tableAllRepository.save(optional.get());
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
        tableAllRepository.deleteById(id);
    }
}