package com.cwj.datasource.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * com.cwj.datasource.redis.RedisSpringController
 *
 * 在此类中仅为直接测试Redis的功能，因此忽略了Service层的创建，直接在Controller层中操作Redis数据库。
 *
 * @author ChengWenjia  cwj1714@163.com
 * @date 2021-08-05 09:56
 */
@RestController
@Api(tags = "Redis:连接测试")
@RequestMapping("/RedisSamples")
public class RedisSpringController {

    @Resource
    RedisTemplate<String, Object> redisTemplate;


    // ---- String ----
    @GetMapping("/setStr")
    @ApiOperation(value = "字符串set", notes = "增：字符串:key-value, 过期时间，时间单位", position = 0)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "String",defaultValue = "刘备", paramType = "query"),
            @ApiImplicitParam(name = "timeOut", value = "超时时间", required = true, dataType = "long",defaultValue = "600000", paramType = "query"),
            @ApiImplicitParam(name = "timeUnit", value = "时间单位", required = true, dataType = "ref",defaultValue = "TimeUnit", paramType = "query")
    })
    public void setStr(String key, String value,long timeOut,TimeUnit timeUnit){
        redisTemplate.opsForValue().set(key,value,timeOut, timeUnit);
    }

    @GetMapping("/getStr")
    @ApiOperation(value = "字符串get", notes = "查：键值对-key", position = 2)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query")
    )
    public Object getStr(String key){
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/delStr")
    @ApiOperation(value = "字符串删除", notes = "删：根据key",position = 3)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query")
    )
    public boolean delStr(String key){
        if (StringUtils.hasText(key) && redisTemplate.hasKey(key)) {
            return redisTemplate.delete(key);
        }
        return false;
    }

    /*@GetMapping("/setMultiStr")
    @ApiOperation(value = "map", notes = "增：Map集合")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "map",value = "Map集合", required = true, dataType = "String",defaultValue = "",paramType = "query")
    )
    public void multiSet(Map<String, Object> map){
        redisTemplate.opsForValue().multiSet(map);
    }*/

    // ---- List ----
    @GetMapping("/listRightSet")
    @ApiOperation(value = "list 右侧单增", notes = "右增单个：从list数据结构右侧添加value", position = 4)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "value", value = "单个值", required = true, dataType = "String",defaultValue = "刘备", paramType = "query")
    })
    public long listRightSet(String key, Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @GetMapping("/listLeftSet")
    @ApiOperation(value = "list 左侧单增", notes = "左增单个：从list数据结构左侧添加value", position = 5)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "value", value = "单个值", required = true, dataType = "String",defaultValue = "曹操", paramType = "query")
    })
    public long listLeftSet(String key, Object value){
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @GetMapping("/listRightAllStrSet")
    @ApiOperation(value = "list 右侧多个字符串增", notes = "右增多个Str：从list数据结构右侧添加多个value", position = 6)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "values", value = "多个字符串值", required = true, dataType = "String",defaultValue = "刘备1,曹操1,孙权1,诸葛亮1,司马懿1,鲁肃1", paramType = "query")
    })
    public long listRightAllStrSet(String key, Object ... values){
        return redisTemplate.opsForList().rightPushAll(key,values);
    }

    @GetMapping("/listRightAllListSet")
    @ApiOperation(value = "list 右侧List增", notes = "右增多个List：从list数据结构右侧添加多个value", position = 7)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "objectList", value = "list集合", required = true, dataType = "String",defaultValue = "[刘备1,曹操1,孙权1,诸葛亮1,司马懿1,鲁肃1]", paramType = "query")
    })
    public long listRightAllListSet(String key, List<Object> objectList){
        return redisTemplate.opsForList().rightPushAll(key,objectList);
    }

    @GetMapping("/listRightPopStr")
    @ApiOperation(value = "list 右侧删除数据", notes = "删：根据key", position = 8)
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query")
    )
    public Object listRightPopStr(String key){
        return redisTemplate.opsForList().rightPop(key);
    }

    // ---- Hash ----
    @GetMapping("/hashSet")
    @ApiOperation(value = "hash 单增", notes = "单增", position = 9)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "哈希key", required = true, dataType = "String",defaultValue = "sghash",paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String",defaultValue = "英雄", paramType = "query"),
            @ApiImplicitParam(name = "hashValue", value = "数据value", required = true, dataType = "String",defaultValue = "刘备", paramType = "query")
    })
    public void hashSet(String key, Object hashKey, Object hashValue){
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    @GetMapping("/hashGet")
    @ApiOperation(value = "hash 查", notes = "查询", position = 10)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "哈希key", required = true, dataType = "String",defaultValue = "sghash",paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String",defaultValue = "英雄", paramType = "query"),
    })
    public Object hashGet(String key, Object hashKey){
        return redisTemplate.opsForHash().get(key,hashKey);
    }

    @GetMapping("/hashDel")
    @ApiOperation(value = "hash 删除", notes = "删除1个或多个", position = 11)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "哈希key", required = true, dataType = "String",defaultValue = "sghash",paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String",defaultValue = "英雄", paramType = "query"),
    })
    public Object hashDel(String key, Object ... hashKeys){
        return  redisTemplate.opsForHash().delete(key,hashKeys);
    }

    @GetMapping("/hashGetMap")
    @ApiOperation(value = "hash 查询结果转为Map", notes = "查询结果转为Map", position = 12)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "哈希key", required = true, dataType = "String",defaultValue = "sghash",paramType = "query")
    })
    public Map<Object, Object> hashGetMap(String key){
        return  redisTemplate.opsForHash().entries(key);
    }

    @GetMapping("/hashGetSet")
    @ApiOperation(value = "hash 查询结果转为Set", notes = "查询结果转为Set", position = 13)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "哈希key", required = true, dataType = "String",defaultValue = "sghash",paramType = "query")
    })
    public Set<Object> hashGetSet(String key){
        return  redisTemplate.opsForHash().keys(key);
    }
}
