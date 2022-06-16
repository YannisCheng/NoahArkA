package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysJob;
import com.cwj.datasource.mysql.base.repository.SysJobRepository;
import com.cwj.datasource.mysql.base.service.SysJobService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 定时任务调度表(SysJob)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysJobService")
public class SysJobServiceImpl implements SysJobService {

    @Autowired
    private SysJobRepository sysJobRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysJob> findById(Long id) {
        return sysJobRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysJob> findAll(Sort sort) {
        return sysJobRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysJob> findByPage(Pageable pageable) {
        return sysJobRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysJob saveOne(SysJob sysJob) {
        return sysJobRepository.save(sysJob);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysJob updateByObj(SysJob sysJob) {
        Optional<SysJob> optional = sysJobRepository.findById(sysJob.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysJob, optional.get());
            return sysJobRepository.save(optional.get());
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
    public void deleteById(Long id) {
        sysJobRepository.deleteById(id);
    }
}