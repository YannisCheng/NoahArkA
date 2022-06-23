package com.cwj.datasource.configuration;

import lombok.Data;
import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * com.cwj.datasource.elasticsearch.curd
 *
 * @author ChengWenjia  cwj1714@163.com
 * @since 2021-12-16 10:12
 */
@Data
@Configuration
@PropertySource(value = "classpath:application-data-dev.yml", encoding = "UTF-8")
@ConfigurationProperties(prefix = "spring.elasticsearch")
public class ElasticSearchClientConfig /*extends AbstractElasticsearchConfiguration*/ {

    private String hostName;

    @Bean
    public RestHighLevelClient restHighLevelClient() {
        // 注意：http://es.yannises.cn 域名中已经包含了9200端口，所以无需再次设置端口号
        RestHighLevelClient client = new RestHighLevelClient(RestClient.builder(HttpHost.create(hostName)));
        System.out.println("client toString: " + client.toString());
        return client;
        // 完整写法
        // return new RestHighLevelClient(RestClient.builder(new HttpHost(hostName, 9200, "http")));
    }

    // 此种定义方式总是自动追加9200端口号。
    //@NotNull
    //@Override
    //@Bean
    //// 2021-12-16 11:12:35
    //// https://docs.spring.io/spring-data/elasticsearch/docs/4.0.6.RELEASE/reference/html/#elasticsearch.clients.rest
    //public RestHighLevelClient elasticsearchClient() {
    //    System.out.println("-----> hostName: " + hostName);
    //
    //    // 使用构建器提供集群地址、设置默认值HttpHeaders或启用 SSL
    //    final ClientConfiguration clientConfiguration = ClientConfiguration.builder()
    //            .connectedTo(hostName)
    //            .build();
    //
    //    // 创建 RestHighLevelClient
    //    return RestClients.create(clientConfiguration).rest();
    //}
}
