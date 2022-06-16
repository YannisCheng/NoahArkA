package com.cwj.datasource.mysql.base.service.impl;

import com.cwj.datasource.mysql.base.entity.SysNotice;
import com.cwj.datasource.mysql.base.repository.SysNoticeRepository;
import com.cwj.datasource.mysql.base.service.SysNoticeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * 通知公告表(SysNotice)表服务实现类
 *
 * @author WenjiaCheng
 * @since 2022-06-16 20:46:17
 */
@Service("sysNoticeService")
public class SysNoticeServiceImpl implements SysNoticeService {

    @Autowired
    private SysNoticeRepository sysNoticeRepository;


    // --------------------------------------  查  --------------------------------------

    /**
     * 通过主键id查询单条数据
     *
     * @param id 主键
     * @return 实例对象
     */
    @Override
    public Optional<SysNotice> findById(Integer id) {
        return sysNoticeRepository.findById(id);
    }


    /**
     * 不分页查询所有数据
     *
     * @return 所有数据
     */
    @Override
    public List<SysNotice> findAll(Sort sort) {
        return sysNoticeRepository.findAll(sort);
    }


    /**
     * 通过分页查询多条数据
     *
     * @param pageable 分页
     * @return 分页数据
     */
    @Override
    public Page<SysNotice> findByPage(Pageable pageable) {
        return sysNoticeRepository.findAll(pageable);
    }


    // --------------------------------------  增  --------------------------------------

    /**
     * 保存单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysNotice saveOne(SysNotice sysNotice) {
        return sysNoticeRepository.save(sysNotice);
    }


    // --------------------------------------  改  --------------------------------------

    /**
     * 通过obj更新单个对象
     *
     * @return 保存后的对象
     */
    @Override
    public SysNotice updateByObj(SysNotice sysNotice) {
        Optional<SysNotice> optional = sysNoticeRepository.findById(sysNotice.id);
        boolean present = optional.isPresent();
        if (present) {
            BeanUtils.copyProperties(sysNotice, optional.get());
            return sysNoticeRepository.save(optional.get());
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
        sysNoticeRepository.deleteById(id);
    }
}