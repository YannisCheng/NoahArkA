package com.cwj.datasource.elasticsearch.dao;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * com.cwj.datasource.elasticsearch.dao
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 10:36
 */
@Data
@NoArgsConstructor
// @Accessors(chain = true)
@Document(indexName = "book_lao_zi")
@TypeAlias(value = "LaoZi")
public class BookOfLaoZi implements Serializable {
}
