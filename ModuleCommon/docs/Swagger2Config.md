# Swagger2配置

## tags-类别划分

tags：将不同的接口统一分类，可作用于 `类名`、`方法名`，只是使用的注解的不同。
 
- 在 `类名` 上使用：**@Api(tags = "ES 基本操作操作测试")** 
  
  则将该类中的所有接口统一划分到 `ES 基本操作操作测试` 类别下。
  
- 在 `方法名` 上使用：**@ApiOperation(value = "搜索-MatchAll", notes = "搜索")**
 
  则将该方法对应的接口划分到 `搜索` 类别下。

## `Uncaught RangeError: Maximum call stack size exceeded` - 堆栈溢出


**代码片段：**

```Java
@RestController
@RequestMapping(value = "/esIndexCenter")
@Api(tags = "index管理Center")
public class EsIndexCenter {

    @Autowired
    RestHighLevelClient client;

    private final RequestOptions options = RequestOptions.DEFAULT;
    @GetMapping(value = "/index/Info")
    @ApiOperation(value = "index：查", notes = "index管理：获取index信息")
    @ApiImplicitParam(name = "indexName", value = "索引名称", defaultValue = "book_temp", required = true, dataType = "String")
    public GetIndexResponse getIndexInfo(String indexName) {
        if (checkIndexExist(indexName)) {
            GetIndexRequest getIndexRequest = new GetIndexRequest(indexName);
            try {
                return client.indices().get(getIndexRequest, options);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        return null;
    }
}
```
