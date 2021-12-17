package com.cwj.datasource.elasticsearch.repository;

import com.cwj.datasource.elasticsearch.entity.book.BookOfLaoZi;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * com.cwj.datasource.elasticsearch.repository
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 22:30
 */
public interface SampleRepository extends ElasticsearchRepository<BookOfLaoZi,String> {

}
