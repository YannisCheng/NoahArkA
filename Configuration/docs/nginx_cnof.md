# Nginx代理配置

## 功能要求

![](/images/nginx-conf.png)

通过使用 `1台Nginx服务器` 反向代理`2台目标服务器`。

## Nginx配置

路径：


```
/etc/nginx/conf.d/tempServer.conf
```

```yml
# link:https://www.runoob.com/w3cnote/nginx-proxy-balancing.html
upstream backend {
	server	had-dn01:9001 weight=1 max_fails=2 fail_timeout=2;
	server  had-dn02:9001 weight=1 max_fails=2 fail_timeout=2;
	#访问地址对应固定的服务器地址
	#ip_hash;
}


server{
      listen 6688;
      server_name had-nn;
      location / {
		proxy_pass	http://backend;
          	#root /usr/share/nginx/html;
          	#index index.html index.htm;
      }
 }
```

