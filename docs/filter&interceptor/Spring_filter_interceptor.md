#

2021-12-23 14:23:52 周四

**博客：**

- [https://blog.csdn.net/qq_44156131/article/details/104001057](https://blog.csdn.net/qq_44156131/article/details/104001057)
- [SpringBoot 过滤器&拦截器](https://snailclimb.gitee.io/springboot-guide/#/./docs/basis/springboot-interceptor)
- [Spring中的拦截器](https://blog.csdn.net/hongxingxiaonan/article/details/48090075)
- [spring-boot笔记-HandlerInterceptor和MethodInterceptor(AspectJ)（四）](https://blog.csdn.net/u013185616/article/details/72926966)
- [Spring Boot 使用 @Aspect 注解实现 AOP](https://blog.csdn.net/qmqm011/article/details/90172698)
- [SpringBoot使用AOP，PointCut表达式详解以及使用](https://blog.csdn.net/LuQiaoYa/article/details/88233846)
- [springBoot AOP @Before,@Around,@After,@AfterReturn,@AfterThrowing的理解](https://blog.csdn.net/Crystalqy/article/details/104292711)

## 拦截器分类

- **url请求：`HandlerInterceptor`、`Filter`**。

  拦截的目标是请求的地址

 - `Filter`：

  是`Servlet`规范规定的，不属于`spring框架`。 在写Filter时需要自己配置拦截的`urlPatterns`,它适合更粗粒度的拦截，在请求前后做一些编解码处理、Session验证等。

 - `HandlerInterceptor`：

  提供更精细的的控制能力：在request被响应之前、request被响应之后、视图渲染之前以及request全部结束之后。 所以针对请求地址做一些验证、预处理等操作比较合适。
也可以用作计算一个请求的相应时间等。【必须过DispatcherServlet的请求才会被拦截】

- **method：`MethodInterceptor`**

 利用的是`AOP实现机制`，它拦截的目标是`方法`，即使不是controller中的方法。 实现MethodInterceptor拦截器大致也分为2种：

  - 实现`MethodInterceptor接口`；
  - 利用`AspectJ`的`注解`或`配置`。

    利用AOP可以对业务逻辑的各个部分进行隔离，从而使得业务逻辑各部分之间的耦合度降低，提高程序的可重用性，同时提高了开发的效率。
    在Spring AOP中业务逻辑仅仅只关注业务本身，将日志记录、性能统计、安全控制、事务处理、异常处理等代码从业务逻辑代码中划分出来，从而在改变这些行为的时候不影响业务逻辑的代码。
 
## 过滤实现(@WebFilter方式)

**`@WebFilter`** 注解中：
 
- `filterName` 中的值是`当前类`的名称 
- `urlPatterns`中的值为 `需要被过滤的url`

```java
@WebFilter(filterName = "BaseFilter", urlPatterns = "/esSearchCenter/*")
public class BaseFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException { }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException { }

    @Override
    public void destroy() { }
}
```

通过该注解创建类后就可以直接应用到项目中。

## 拦截实现-HTTP请求(实现HandlerInterceptor接口)

### 创建HandlerInterceptor接口实现类BaseHandlerInterceptor

```java
@Component
public class BaseHandlerInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception { }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception { }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception { }
}
```

### 创建WebMvcConfigurer接口实例，并在该实例中引用类BaseHandlerInterceptor

```java
@Configuration
public class BaseWebConfig implements WebMvcConfigurer {

    @Autowired
    BaseHandlerInterceptor baseHandlerInterceptor;

    /**
     * 运行顺序：
     * 1.加入的顺序就是拦截器执行的顺序，
     * 2.按顺序执行所有拦截器的preHandle
     * 3.所有的preHandle 执行完再执行全部postHandle 最后是postHandle
     *
     *
     *
     * 1 preHandle request : /NoahArk_Dev/doc.html
     * 1 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 preHandle request : /NoahArk_Dev/doc.html
     * 2 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 postHandle request : /NoahArk_Dev/doc.html
     * 2 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 1 postHandle request : /NoahArk_Dev/doc.html
     * 1 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 2 afterCompletion request : /NoahArk_Dev/doc.html
     * 2 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     * 1 afterCompletion request : /NoahArk_Dev/doc.html
     * 1 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@4b1eb1ce
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(baseHandlerInterceptor).addPathPatterns("/esSearchCenter/*");
    }
}
```

## 拦截实现-方法(Method AOP，by @AspectJ方式)

 1. 通过@Aspect注解创建Filter接口实现类
 2. 在启动类上添加 `@ServletComponentScan` 注解

### 通过@Aspect注解创建Filter接口实现类

```java
@Aspect
@Component
public class AspectOfAop {
    private final Logger logger = LoggerFactory.getLogger(AspectOfAop.class);

    public static final String AOP_TAG = "AOP --> ";

    /**
     * Pointcut是织入Advice的触发条件。
     * 每个Pointcut的定义包括2部分：
     * 1.表达式；
     * 2.方法签名，方法签名必须是public及void型。
     * <p>
     * 可以将Pointcut中的方法看作是一个被Advice引用的助记符，
     * 因为表达式不直观，因此我们可以通过方法签名的方式为此表达式命名。
     * 因此Pointcut中的方法只需要方法签名，而不需要在方法体内编写实际代码。
     * <p>
     * 参数说明：`*` 表示所有，`..` 表示任意参数。1个`*`表示1层目录，从后向前推目录层级。
     */
    @Pointcut("execution(* com.cwj.datasource.elasticsearch.controller.EsSearchCenter.*(..))")
    public void aboutElasticSearch() { }

    /**
     * 前置增强，目标方法执行之前执行
     */
    @Before("aboutElasticSearch()")
    public void doBefore(JoinPoint joinPoint) { }

    /**
     * 环绕增强，目标方法执行前后分别执行一些代码
     */
    @Around("aboutElasticSearch()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable { }

    /**
     * 后置增强，不管是抛出异常或者正常退出都会执行
     */
    @After("aboutElasticSearch()")
    public void doAfter() { }

    /**
     * 返回增强，目标方法正常执行完毕时执行
     * returning的值和doAfterReturning的参数名一致
     */
    @AfterReturning(returning = "ret", pointcut = "aboutElasticSearch()")
    public void doAfterReturn(Object ret) throws Throwable { }

    /**
     * 异常抛出增强，目标方法发生异常的时候执行
     */
    @AfterThrowing(throwing = "throwable", pointcut = "execution(* com.cwj.datasource.elasticsearch.controller.*.*(..))")
    public void doAfterThrowing(Throwable throwable) { }

}
```

### 在启动类上添加 `@ServletComponentScan` 注解

```java
@ServletComponentScan
@SpringBootApplication
public class CommonApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommonApplication.class, args);
    }

}
```


## 拦截、过滤、AOP执行流程打印

执行日志：

```java
2021-12-24 09:40:11.865 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /webjars/bycdao-ui/layer3.0.3/skin/default/loading-1.gif
2021-12-24 09:40:11.866 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /webjars/bycdao-ui/layer3.0.3/skin/default/loading-1.gif
2021-12-24 09:40:11.868 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /esSearchCenter/getById
过滤器 1# --> doFilter
过滤器 1# --> 请求的接口为：/NoahArk_Dev/esSearchCenter/getById
2021-12-24 09:40:11.869 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /esSearchCenter/getById
拦截器 1# --> 1 preHandle request : /NoahArk_Dev/esSearchCenter/getById
拦截器 1# --> 1 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
拦截器 2# --> 2 preHandle request : /NoahArk_Dev/esSearchCenter/getById
拦截器 2# --> 2 preHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
AOP --> doAround，pjp：execution(BookOfLaoZi com.cwj.datasource.elasticsearch.controller.EsSearchCenter.getItemById(String))
AOP --> doBefore，joinPoint：execution(BookOfLaoZi com.cwj.datasource.elasticsearch.controller.EsSearchCenter.getItemById(String))
2021-12-24 09:40:11.890 DEBUG org.apache.http.impl.nio.client.MainClientExec 121 prepare - [exchange: 4] start execution
2021-12-24 09:40:11.891 DEBUG org.apache.http.client.protocol.RequestAddCookies 123 process - CookieSpec selected: default
2021-12-24 09:40:11.892 DEBUG org.apache.http.client.protocol.RequestAuthCache 131 doPreemptiveAuth - Re-using cached 'basic' auth scheme for http://es.yannises.cn:80
2021-12-24 09:40:11.892 DEBUG org.apache.http.client.protocol.RequestAuthCache 140 doPreemptiveAuth - No credentials for preemptive authentication
2021-12-24 09:40:11.893 DEBUG org.apache.http.impl.nio.client.AbstractClientExchangeHandler 366 requestConnection - [exchange: 4] Request connection for {}->http://es.yannises.cn:80
2021-12-24 09:40:11.893 DEBUG org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager 279 requestConnection - Connection request: [route: {}->http://es.yannises.cn:80][total kept alive: 1; route allocated: 1 of 10; total allocated: 1 of 30]
2021-12-24 09:40:11.894 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 154 setSocketTimeout - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:r]: Set timeout 0
2021-12-24 09:40:11.894 DEBUG org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager$1 303 completed - Connection leased: [id: http-outgoing-0][route: {}->http://es.yannises.cn:80][total kept alive: 0; route allocated: 1 of 10; total allocated: 1 of 30]
2021-12-24 09:40:11.895 DEBUG org.apache.http.impl.nio.client.AbstractClientExchangeHandler 313 connectionAllocated - [exchange: 4] Connection allocated: CPoolProxy{http-outgoing-0 [ACTIVE]}
2021-12-24 09:40:11.895 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 182 setAttribute - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:r]: Set attribute http.nio.exchange-handler
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.client.InternalRequestExecutor 71 requestReady - http-outgoing-0 [ACTIVE] Request ready
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 108 setEvent - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][rw:r]: Event set [w]
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.client.MainClientExec 215 generateRequest - [exchange: 4] Attempt 1 to execute request
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.client.MainClientExec 222 generateRequest - [exchange: 4] Target auth state: UNCHALLENGED
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.client.MainClientExec 229 generateRequest - [exchange: 4] Proxy auth state: UNCHALLENGED
2021-12-24 09:40:11.896 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 154 setSocketTimeout - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][rw:w]: Set timeout 30000
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 136 onRequestSubmitted - http-outgoing-0 >> GET /book_lao_zi/_doc/qrdx2323-23u9h223XNf HTTP/1.1
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 139 onRequestSubmitted - http-outgoing-0 >> Content-Length: 0
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 139 onRequestSubmitted - http-outgoing-0 >> Host: es.yannises.cn:80
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 139 onRequestSubmitted - http-outgoing-0 >> Connection: Keep-Alive
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 139 onRequestSubmitted - http-outgoing-0 >> User-Agent: elasticsearch-java/7.12.1-SNAPSHOT (Java/1.8.0_131)
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 139 onRequestSubmitted - http-outgoing-0 >> X-Elastic-Client-Meta: es=7.12.1-SNAPSHOT,jv=1.8,t=7.12.1-SNAPSHOT,hc=4.1.4,kt=1.5
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 108 setEvent - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][rw:w]: Event set [w]
2021-12-24 09:40:11.897 DEBUG org.apache.http.impl.nio.client.MainClientExec 273 requestCompleted - [exchange: 4] Request completed
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession$LoggingByteChannel 222 write - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][rw:w]: 272 bytes written
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "GET /book_lao_zi/_doc/qrdx2323-23u9h223XNf HTTP/1.1[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "Content-Length: 0[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "Host: es.yannises.cn:80[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "Connection: Keep-Alive[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "User-Agent: elasticsearch-java/7.12.1-SNAPSHOT (Java/1.8.0_131)[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "X-Elastic-Client-Meta: es=7.12.1-SNAPSHOT,jv=1.8,t=7.12.1-SNAPSHOT,hc=4.1.4,kt=1.5[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 >> "[\r][\n]"
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.client.InternalRequestExecutor 71 requestReady - http-outgoing-0 [ACTIVE] Request ready
2021-12-24 09:40:11.898 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 116 clearEvent - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:w]: Event cleared [w]
2021-12-24 09:40:12.283 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession$LoggingByteChannel 206 read - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:r]: 750 bytes read
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 << "HTTP/1.1 200 OK[\r][\n]"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 << "Content-Type: application/json; charset=UTF-8[\r][\n]"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 << "Date: Fri, 24 Dec 2021 01:40:12 GMT[\r][\n]"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 << "Content-Length: 626[\r][\n]"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 54 wire - http-outgoing-0 << "[\r][\n]"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.Wire 68 wire - http-outgoing-0 << "{"_index":"book_lao_zi","_type":"_doc","_id":"qrdx2323-23u9h223XNf","_version":1,"_seq_no":18,"_primary_term":38,"found":true,"_source":{"book_item":"","content":" \r\n[0xffffffe3][0xffffff80][0xffffff80][0xffffffe3][0xffffff80][0xffffff80][0xffffffe6][0xffffffb5][0xffffff8b][0xffffffe8][0xffffffaf][0xffffff95][0xffffffe6][0xffffff95][0xffffffb0][0xffffffe6][0xffffff8d][0xffffffae]-2 [0xffffffe7][0xffffff82][0xffffff8a][0xffffffe8][0xffffff80][0xffffff85][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe7][0xffffffab][0xffffff8b][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe8][0xffffffa7][0xffffff86][0xffffffe8][0xffffff80][0xffffff85][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe7][0xffffffab][0xffffffa0][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe8][0xffffffa7][0xffffff81][0xffffffe8][0xffffff80][0xffffff85][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe6][0xffffff98][0xffffff8e][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe4][0xffffffbc][0xffffff90][0xffffffe8][0xffffff80][0xffffff85][0xffffffe6][0xffffff97][0xffffffa0][0xffffffe5][0xffffff8a][0xffffff9f][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe7][0xffffff9f][0xffffff9c][0xffffffe8][0xffffff80][0xffffff85][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe9][0xffffff95][0xffffffbf][0xffffffe3][0xffffff80][0xffffff82][0xffffffe5][0xffffff85][0xffffffb6][0xffffffe5][0xffffff9c][0xffffffa8][0xffffffe9][0xffffff81][0xffffff93][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffff9b][0xffffffb0][[0xffffffe7][0xffffffb1][0xffffffb3][0xffffffe4][0xffffffbd][0xffffff99]][0xffffffe9][0xffffffa3][0xffffff9f][0xffffffe8][0xffffffb5][0xffffff98][0xffffffe8][0xffffffa1][0xffffff8c][0xffffffe3][0xffffff80][0xffffff82][0xffffffe7][0xffffff89][0xffffffa9][0xffffffe6][0xffffff88][0xffffff96][0xffffffe6][0xffffff81][0xffffffb6][0xffffffe4][0xffffffb9][0xffffff8b][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffff95][0xffffff85][0xffffffe6][0xffffff9c][0xffffff89][0xffffffe6][0xffffffac][0xffffffb2][0xffffffe8][0xffffff80][0xffffff85][0xffffffe5][0xffffffbc][0xffffff97][0xffffffe5][0xffffffb1][0xffffff85][0xffffffe3][0xffffff80][0xffffff82][0xffffffe6][0xffffff9b][0xffffffb2][0xffffffe5][0xffffff88][0xffffff99][0xffffffe9][0xffffff87][0xffffff91][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffff9e][0xffffff89][0xffffffe5][0xffffff88][0xffffff99][0xffffffe5][0xffffffae][0xffffff9a][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffffb4][0xffffffbc][0xffffffe5][0xffffff88][0xffffff99][0xffffffe7][0xffffff9b][0xffffff88][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffff95][0xffffff9d][0xffffffe5][0xffffff88][0xffffff99][0xffffffe6][0xffffff96][0xffffffb0][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe5][0xffffffb0][0xffffff91][0xffffffe5][0xffffff88][0xffffff99][0xffffffe5][0xffffffbe][0xffffff97][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe5][0xffffffa4][0xffffff9a][0xffffffe5][0xffffff88][0xffffff99][0xffffffe6][0xffffff83][0xffffff91][0xffffffe3][0xffffff80][0xffffff82][0xffffffe6][0xffffff98][0xffffffaf][0xffffffe4][0xffffffbb][0xffffffa5][0xffffffe5][0xffffffa3][0xffffffb0][0xffffffe4][0xffffffba][0xffffffba][0xffffffe6][0xffffff89][0xffffffa7][0xffffffe4][0xffffffb8][0xffffff80][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe4][0xffffffbb][0xffffffa5][0xffffffe4][0xffffffb8][0xffffffba][0xffffffe5][0xffffffa4][0xffffffa9][0xffffffe4][0xffffffb8][0xffffff8b][0xffffffe7][0xffffff89][0xffffffa7][0xffffffe3][0xffffff80][0xffffff82][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe8][0xffffffa7][0xffffff86][0xffffffe6][0xffffff95][0xffffff85][0xffffffe7][0xffffffab][0xffffffa0][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe8][0xffffffa7][0xffffff81][0xffffffe6][0xffffff95][0xffffff85][0xffffffe6][0xffffff98][0xffffff8e][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe4][0xffffffbc][0xffffff90][0xffffffe6][0xffffff95][0xffffff85][0xffffffe6][0xffffff9c][0xffffff89][0xffffffe5][0xffffff8a][0xffffff9f][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe8][0xffffff87][0xffffffaa][0xffffffe7][0xffffff9f][0xffffff9c][0xffffffe6][0xffffff95][0xffffff85][0xffffffe8][0xffffff83][0xffffffbd][0xffffffe9][0xffffff95][0xffffffbf][0xffffffe3][0xffffff80][0xffffff82][0xffffffe5][0xffffffa4][0xffffffab][0xffffffe5][0xffffff94][0xffffffaf][0xffffffe4][0xffffffb8][0xffffff8d][0xffffffe4][0xffffffba][0xffffff89][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe6][0xffffff95][0xffffff85][0xffffffe8][0xffffff8e][0xffffffab][0xffffffe8][0xffffff83][0xffffffbd][0xffffffe4][0xffffffb8][0xffffff8e][0xffffffe4][0xffffffb9][0xffffff8b][0xffffffe4][0xffffffba][0xffffff89][0xffffffe3][0xffffff80][0xffffff82][0xffffffe5][0xffffff8f][0xffffffa4][0xffffffe4][0xffffffb9][0xffffff8b][0xffffffe6][0xffffff89][0xffffff80][0xffffffe8][0xffffff83][0xffffff83][0xffffffe6][0xffffff9b][0xffffffb2][0xffffffe9][0xffffff87][0xffffff91][0xffffffe8][0xffffff80][0xffffff85][0xffffffef][0xffffffbc][0xffffff8c][0xffffffe5][0xffffff87][0xffffffa0][0xffffffe8][0xffffffaf][0xffffffad][0xffffffe6][0xffffff89][0xffffff8d][0xffffffef][0xffffffbc][0xffffff9f][0xffffffe8][0xffffffaf][0xffffff9a][0xffffffe9][0xffffff87][0xffffff91][0xffffffe5][0xffffffbd][0xffffff92][0xffffffe4][0xffffffb9][0xffffff8b][0xffffffe3][0xffffff80][0xffffff82]"}}"
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 125 onResponseReceived - http-outgoing-0 << HTTP/1.1 200 OK
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 128 onResponseReceived - http-outgoing-0 << Content-Type: application/json; charset=UTF-8
2021-12-24 09:40:12.284 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 128 onResponseReceived - http-outgoing-0 << Date: Fri, 24 Dec 2021 01:40:12 GMT
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.conn.ManagedNHttpClientConnectionImpl 128 onResponseReceived - http-outgoing-0 << Content-Length: 626
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.InternalRequestExecutor 106 responseReceived - http-outgoing-0 [ACTIVE(626)] Response received
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.MainClientExec 286 responseReceived - [exchange: 4] Response received HTTP/1.1 200 OK
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.InternalRequestExecutor 81 inputReady - http-outgoing-0 [ACTIVE(626)] Input ready
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.MainClientExec 325 consumeContent - [exchange: 4] Consume content
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.AbstractClientExchangeHandler 298 manageConnectionPersistence - [exchange: 4] Connection can be kept alive indefinitely
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.MainClientExec 385 responseCompleted - [exchange: 4] Response processed
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.client.AbstractClientExchangeHandler 239 releaseConnection - [exchange: 4] releasing connection
2021-12-24 09:40:12.285 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 190 removeAttribute - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:r]: Remove attribute http.nio.exchange-handler
2021-12-24 09:40:12.286 DEBUG org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager 373 releaseConnection - Releasing connection: [id: http-outgoing-0][route: {}->http://es.yannises.cn:80][total kept alive: 0; route allocated: 1 of 10; total allocated: 1 of 30]
2021-12-24 09:40:12.286 DEBUG org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager 387 releaseConnection - Connection [id: http-outgoing-0][route: {}->http://es.yannises.cn:80] can be kept alive indefinitely
2021-12-24 09:40:12.286 DEBUG org.apache.http.impl.nio.conn.LoggingIOSession 154 setSocketTimeout - http-outgoing-0 192.168.9.137:62196<->198.16.63.11:80[ACTIVE][r:r]: Set timeout 0
2021-12-24 09:40:12.286 DEBUG org.apache.http.impl.nio.conn.PoolingNHttpClientConnectionManager 393 releaseConnection - Connection released: [id: http-outgoing-0][route: {}->http://es.yannises.cn:80][total kept alive: 1; route allocated: 1 of 10; total allocated: 1 of 30]
2021-12-24 09:40:12.286 DEBUG org.apache.http.impl.nio.client.InternalRequestExecutor 85 inputReady - http-outgoing-0 [ACTIVE] [content length: 626; pos: 626; completed: true]
2021-12-24 09:40:12.287 DEBUG org.elasticsearch.client.RequestLogger 59 logResponse - request [GET http://es.yannises.cn/book_lao_zi/_doc/qrdx2323-23u9h223XNf] returned [HTTP/1.1 200 OK]
2021-12-24 09:40:12.287 TRACE org.elasticsearch.client.RequestLogger 83 logResponse - curl -iX GET 'http://es.yannises.cn/book_lao_zi/_doc/qrdx2323-23u9h223XNf'
# HTTP/1.1 200 OK
# Content-Type: application/json; charset=UTF-8
# Date: Fri, 24 Dec 2021 01:40:12 GMT
# Content-Length: 626
#
# {"_index":"book_lao_zi","_type":"_doc","_id":"qrdx2323-23u9h223XNf","_version":1,"_seq_no":18,"_primary_term":38,"found":true,"_source":{"book_item":"","content":" \r\n　　测试数据-2 炊者不立，自视者不章，自见者不明，自伐者无功，自矜者不长。其在道，曰[米余]食赘行。物或恶之，故有欲者弗居。曲则金，枉则定，洼则盈，敝则新，少则得，多则惑。是以声人执一，以为天下牧。不自视故章，不自见故明，不自伐故有功，不自矜故能长。夫唯不争，故莫能与之争。古之所胃曲金者，几语才？诚金归之。"}}
AOP --> doAfterReturn：BookOfLaoZi(id=qrdx2323-23u9h223XNf, content= 
　　测试数据-2 炊者不立，自视者不章，自见者不明，自伐者无功，自矜者不长。其在道，曰[米余]食赘行。物或恶之，故有欲者弗居。曲则金，枉则定，洼则盈，敝则新，少则得，多则惑。是以声人执一，以为天下牧。不自视故章，不自见故明，不自伐故有功，不自矜故能长。夫唯不争，故莫能与之争。古之所胃曲金者，几语才？诚金归之。)----> 该返回值已经被处理
AOP --> doAfter
AOP --> 方法名：getItemById，方法执行耗时：484
拦截器 2# --> 2 postHandle request : /NoahArk_Dev/esSearchCenter/getById
拦截器 2# --> 2 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
拦截器 1# --> 1 postHandle request : /NoahArk_Dev/esSearchCenter/getById
拦截器 1# --> 1 postHandle response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
拦截器 2# --> 2 afterCompletion request : /NoahArk_Dev/esSearchCenter/getById
拦截器 2# --> 2 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
拦截器 1# --> 1 afterCompletion request : /NoahArk_Dev/esSearchCenter/getById
拦截器 1# --> 1 afterCompletion response : org.springframework.security.web.header.HeaderWriterFilter$HeaderWriterResponse@3b08bdc8
过滤器 1# --> 该用户的请求已经处理完毕，请求花费的时间为：499
2021-12-24 09:40:12.441 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /webjars/bycdao-ui/ace-editor/worker-json.js
2021-12-24 09:40:12.443 DEBUG springfox.documentation.spring.web.PropertySourcedRequestMappingHandlerMapping 108 lookupHandlerMethod - looking up handler for path: /webjars/bycdao-ui/ace-editor/worker-json.js
```

