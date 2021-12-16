package com.cwj.datasource.configuration;

import lombok.Data;
import org.elasticsearch.client.RestHighLevelClient;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.elasticsearch.client.ClientConfiguration;
import org.springframework.data.elasticsearch.client.RestClients;
import org.springframework.data.elasticsearch.config.AbstractElasticsearchConfiguration;

/**
 * com.cwj.datasource.elasticsearch.curd
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-12-16 10:12
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticSearchClientConfig extends AbstractElasticsearchConfiguration {

    private String hostName;

    //@Bean
    //public RestHighLevelClient restHighLevelClient() {
    //    return new RestHighLevelClient(RestClient.builder(new HttpHost(hostName, 9200, "http")));
    //}

    @NotNull
    @Override
    @Bean
    // 2021-12-16 11:12:35
    // https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#elasticsearch.clients.rest
    public RestHighLevelClient elasticsearchClient() {
        System.out.println("-----> hostName: " + hostName);

        // 使用构建器提供集群地址、设置默认值HttpHeaders或启用 SSL
        final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
                .connectedTo(hostName + ":9200")
                .build();

        // 创建 RestHighLevelClient
        return RestClients.create(clientConfiguration).rest();
    }
}
