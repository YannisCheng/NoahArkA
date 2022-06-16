package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysJobLog;
import com.cwj.datasource.mysql.base.repository.SysJobLogRepository;
import com.cwj.datasource.mysql.base.service.SysJobLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 定时任务调度日志表(SysJobLog)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysJobLogService")
public class SysJobLogServiceImpl implements SysJobLogService {

    @Autowired
    private SysJobLogRepository sysJobLogRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysJobLog> findById(Long id) {
        return sysJobLogRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysJobLog> findAll(Sort sort) {
        return sysJobLogRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysJobLog> findByPage(Pageable pageable) {
        return sysJobLogRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysJobLog saveOne(SysJobLog sysJobLog) {
        return sysJobLogRepository.save(sysJobLog);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysJobLog updateByObj(SysJobLog sysJobLog) {
        Optional<SysJobLog> optional = sysJobLogRepository.findById(sysJobLog.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysJobLog, optional.get());
            return sysJobLogRepository.save(optional.get());
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
        sysJobLogRepository.deleteById(id);
    }
}