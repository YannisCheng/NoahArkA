package com.cwj.datasource.elasticsearch.entity.book;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * com.cwj.datasource.elasticsearch.dao 《老子-道德经》
 *
 * https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#elasticsearch.mapping
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 10:36
 */
@Data
// 无参构造函数
@NoArgsConstructor
// 链式调用
// @Accessors(chain = true)
// 文本类型配置
@Document(indexName = "book_lao_zi")
// 别名，未配置该选项将默认使用类名
@TypeAlias(value = "LaoZi")
public class BookOfLaoZi implements Serializable {
    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_smart",searchAnalyzer = "ik_max_word")
    private String content;

}
