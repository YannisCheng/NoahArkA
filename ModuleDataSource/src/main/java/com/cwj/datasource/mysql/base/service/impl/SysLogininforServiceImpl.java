package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysLogininfor;
import com.cwj.datasource.mysql.base.repository.SysLogininforRepository;
import com.cwj.datasource.mysql.base.service.SysLogininforService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 系统访问记录(SysLogininfor)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysLogininforService")
public class SysLogininforServiceImpl implements SysLogininforService {

    @Autowired
    private SysLogininforRepository sysLogininforRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysLogininfor> findById(Long id) {
        return sysLogininforRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysLogininfor> findAll(Sort sort) {
        return sysLogininforRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysLogininfor> findByPage(Pageable pageable) {
        return sysLogininforRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysLogininfor saveOne(SysLogininfor sysLogininfor) {
        return sysLogininforRepository.save(sysLogininfor);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysLogininfor updateByObj(SysLogininfor sysLogininfor) {
        Optional<SysLogininfor> optional = sysLogininforRepository.findById(sysLogininfor.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysLogininfor, optional.get());
            return sysLogininforRepository.save(optional.get());
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
        sysLogininforRepository.deleteById(id);
    }
}