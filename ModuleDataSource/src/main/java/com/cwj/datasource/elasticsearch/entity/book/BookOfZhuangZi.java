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
 * com.cwj.datasource.elasticsearch.entity 《庄子》
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-17 10:16
 */
@Data
@NoArgsConstructor
@Document(indexName = "book_zhuang_zi")
@TypeAlias(value = "ZhuangZi")
public class BookOfZhuangZi implements Serializable {

    @Id
    private String id;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String albumName;


    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String title;


    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String author;


    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String content;
}
