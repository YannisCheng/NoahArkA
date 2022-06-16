package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysOperLog;
import com.cwj.datasource.mysql.base.repository.SysOperLogRepository;
import com.cwj.datasource.mysql.base.service.SysOperLogService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 操作日志记录(SysOperLog)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysOperLogService")
public class SysOperLogServiceImpl implements SysOperLogService {

    @Autowired
    private SysOperLogRepository sysOperLogRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysOperLog> findById(Long id) {
        return sysOperLogRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysOperLog> findAll(Sort sort) {
        return sysOperLogRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysOperLog> findByPage(Pageable pageable) {
        return sysOperLogRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysOperLog saveOne(SysOperLog sysOperLog) {
        return sysOperLogRepository.save(sysOperLog);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysOperLog updateByObj(SysOperLog sysOperLog) {
        Optional<SysOperLog> optional = sysOperLogRepository.findById(sysOperLog.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysOperLog, optional.get());
            return sysOperLogRepository.save(optional.get());
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
        sysOperLogRepository.deleteById(id);
    }
}