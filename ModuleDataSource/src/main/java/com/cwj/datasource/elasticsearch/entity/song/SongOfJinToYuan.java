package com.cwj.datasource.elasticsearch.entity.song;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.TypeAlias;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;

/**
 * com.cwj.datasource.elasticsearch.entity.song 诗歌-金~元
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-17 10:25
 */
@Data
@NoArgsConstructor
@Document(indexName = "song_jin_to_yuan")
@TypeAlias(value = "SongOfJinToYuan")
public class SongOfJinToYuan implements Serializable {

    @Id
    private int id;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String title;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String author;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String dynasty;

    @Field(type = FieldType.Text, analyzer = "ik_smart", searchAnalyzer = "ik_max_word")
    private String content;
}