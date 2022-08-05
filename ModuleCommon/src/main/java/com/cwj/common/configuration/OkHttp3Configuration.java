package com.cwj.common.configuration;

import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.concurrent.TimeUnit;

/**
 * OkHttp3Configuration okhttp3配置类
 *
 * @author ChengWenjia
 * @since 2022-08-04 10:17
 */
@Configuration
// 导入配置文件。注意增加编码方式，否则可能中文乱码
//@PropertySource(value = {"classpath:application-common-net.yml"}, encoding = "UTF-8")
public class OkHttp3Configuration {

    //@Value("${okhttp.connectTimeout:30}")
    //private Integer connectTimeout;
    //
    //@Value("${okhttp.readTimeout}")
    //private Integer readTimeout;
    //
    //@Value("${okhttp.writeTimeout}")
    //private Integer writeTimeout;
    //
    //@Value("${okhttp.maxIdleConnections}")
    //private Integer maxIdleConnections;
    //
    //@Value("${okhttp.keepAliveDuration}")
    //private Long keepAliveDuration;


    @Bean
    public OkHttpClient okHttpClient() {
        return new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .sslSocketFactory(sslSocketFactory(), x509TrustManager())
                .retryOnConnectionFailure(false)
                .hostnameVerifier((hostname, session) -> true)
                .connectionPool(pool())
                .build();
    }

    @Bean
    public X509TrustManager x509TrustManager() {
        return new X509TrustManager() {
            @Override
            public void checkClientTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain, String authType)
                    throws CertificateException {
            }

            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return new X509Certificate[0];
            }
        };
    }

    @Bean
    public SSLSocketFactory sslSocketFactory() {
        try {
            // 信任任何链接
            SSLContext sslContext = SSLContext.getInstance("TLS");
            sslContext.init(null, new TrustManager[]{x509TrustManager()}, new SecureRandom());
            return sslContext.getSocketFactory();
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public ConnectionPool pool() {
        return new ConnectionPool(200, 300, TimeUnit.SECONDS);
    }

}
