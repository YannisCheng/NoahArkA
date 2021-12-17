package com.cwj.datasource.elasticsearch.service;

import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * com.cwj.datasource.elasticsearch.service
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 22:35
 */
public interface SampleService {

    Page<BookOfLaoZi> findAll(Pageable pageable);
}
