package com.cwj.datasource.elasticsearch.service.impl;

import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import com.cwj.datasource.elasticsearch.repository.SampleRepository;
import com.cwj.datasource.elasticsearch.service.SampleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * com.cwj.datasource.elasticsearch.service.impl
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 22:37
 */
@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    SampleRepository sampleRepository;

    @Override
    public Page<BookOfLaoZi> findAll(Pageable pageable) {
        return sampleRepository.findAll(pageable);
    }
}
