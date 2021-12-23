# Swagger2配置

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
