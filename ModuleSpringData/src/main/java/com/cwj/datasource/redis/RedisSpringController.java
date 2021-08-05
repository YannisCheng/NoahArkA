package com.cwj.datasource.redis;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * com.cwj.datasource.redis.RedisSpringController
 * <p>
 * 在此类中仅为直接测试Redis的功能，因此忽略了Service层的创建，直接在Controller层中操作Redis数据库。
 * 参考：https://blog.csdn.net/weixin_44806772/article/details/106083152
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

    @Resource
    StringRedisTemplate stringRedisTemplate;


    // ---- String ----
    @GetMapping("/setStr")
    @ApiOperation(value = "字符串set", notes = "增：字符串:key-value, 过期时间，时间单位")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query"),
            @ApiImplicitParam(name = "value", value = "值", required = true, dataType = "String", defaultValue = "刘备", paramType = "query"),
            @ApiImplicitParam(name = "timeOut", value = "超时时间", required = true, dataType = "long", defaultValue = "600000", paramType = "query"),
            @ApiImplicitParam(name = "timeUnit", value = "时间单位", required = true, dataType = "ref", defaultValue = "TimeUnit", paramType = "query")
    })
    public void setStr(String key, String value, long timeOut, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    @GetMapping("/getStr")
    @ApiOperation(value = "字符串get", notes = "查：键值对-key")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query")
    )
    public Object getStr(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @GetMapping("/delStr")
    @ApiOperation(value = "字符串删除", notes = "删：根据key")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query")
    )
    public boolean delStr(String key) {
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
    @ApiOperation(value = "list 右侧单增", notes = "右增单个：从list数据结构右侧添加value")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query"),
            @ApiImplicitParam(name = "value", value = "单个值", required = true, dataType = "String", defaultValue = "刘备", paramType = "query")
    })
    public long listRightSet(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @GetMapping("/listLeftSet")
    @ApiOperation(value = "list 左侧单增", notes = "左增单个：从list数据结构左侧添加value")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query"),
            @ApiImplicitParam(name = "value", value = "单个值", required = true, dataType = "String", defaultValue = "曹操", paramType = "query")
    })
    public long listLeftSet(String key, String value) {
        return redisTemplate.opsForList().rightPush(key, value);
    }

    @GetMapping("/listRightAllStrSet")
    @ApiOperation(value = "list 右侧多个字符串增", notes = "右增多个Str：从list数据结构右侧添加多个value")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query"),
            @ApiImplicitParam(name = "values", value = "多个字符串值", required = true, dataType = "String", defaultValue = "刘备2,曹操2,孙权2,诸葛亮2,司马懿2,鲁肃2", paramType = "query")
    })
    public long listRightAllStrSet(String key, String... values) {
        return redisTemplate.opsForList().rightPushAll(key, values);
    }

    @GetMapping("/listRightAllListSet")
    @ApiOperation(value = "list 右侧List增", notes = "右增多个List：从list数据结构右侧添加多个value")
    /*@ApiImplicitParams({
            @ApiImplicitParam(name = "key",value = "key", required = true, dataType = "String",defaultValue = "三国志",paramType = "query"),
            @ApiImplicitParam(name = "objectList", value = "list集合", required = true, dataType = "String",defaultValue = "[刘备1,曹操1,孙权1,诸葛亮1,司马懿1,鲁肃1]", paramType = "query")
    })*/
    public long listRightAllListSet(@RequestParam(value = "key", defaultValue = "三国志") String key, @RequestParam(defaultValue = "刘备1,曹操1,孙权1,诸葛亮1,司马懿1,鲁肃1") List<String> objectList) {
        return redisTemplate.opsForList().rightPushAll(key, objectList);
    }

    @GetMapping("/listRightPopStr")
    @ApiOperation(value = "list 右侧删除数据", notes = "删：根据key")
    @ApiImplicitParams(
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query")
    )
    public Object listRightPopStr(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }


    @GetMapping("/listRange")
    @ApiOperation(value = "list 范围查询", notes = "根据key、范围查询数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "key", required = true, dataType = "String", defaultValue = "三国志", paramType = "query"),
            @ApiImplicitParam(name = "start", value = "起始索引", required = true, dataType = "int", defaultValue = "0", paramType = "query"),
            @ApiImplicitParam(name = "end", value = "结束索引", required = true, dataType = "int", defaultValue = "20", paramType = "query")
    })
    public List<Object> listGetRange(String key, int start, int end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    // ---- Hash ----
    @GetMapping("/hashSet")
    @ApiOperation(value = "hash 单增", notes = "单增")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "哈希key", required = true, dataType = "String", defaultValue = "sghash", paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String", defaultValue = "英雄", paramType = "query"),
            @ApiImplicitParam(name = "hashValue", value = "数据value", required = true, dataType = "String", defaultValue = "刘备", paramType = "query")
    })
    public void hashSet(String key, String hashKey, String hashValue) {
        redisTemplate.opsForHash().put(key, hashKey, hashValue);
    }

    @GetMapping("/hashGet")
    @ApiOperation(value = "hash 查", notes = "查询")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "哈希key", required = true, dataType = "String", defaultValue = "sghash", paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String", defaultValue = "英雄", paramType = "query"),
    })
    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    @GetMapping("/hashDel")
    @ApiOperation(value = "hash 删除", notes = "删除1个或多个")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "哈希key", required = true, dataType = "String", defaultValue = "sghash", paramType = "query"),
            @ApiImplicitParam(name = "hashKey", value = "数据key", required = true, dataType = "String", defaultValue = "英雄", paramType = "query"),
    })
    public Object hashDel(String key, Object... hashKeys) {
        return redisTemplate.opsForHash().delete(key, hashKeys);
    }

    @GetMapping("/hashGetMap")
    @ApiOperation(value = "hash 查询结果转为Map", notes = "查询结果转为Map")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "哈希key", required = true, dataType = "String", defaultValue = "sghash", paramType = "query")
    })
    public Map<Object, Object> hashGetMap(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    @GetMapping("/hashGetSet")
    @ApiOperation(value = "hash 查询结果转为Set", notes = "查询结果转为Set")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "key", value = "哈希key", required = true, dataType = "String", defaultValue = "sghash", paramType = "query")
    })
    public Set<Object> hashGetSet(String key) {
        return redisTemplate.opsForHash().keys(key);
    }


    // ---- set ----
    @GetMapping("/setGet")
    @ApiOperation(value = "set 查", notes = "根据key值获取Set中的所有值")
    public Set<Object> setGet(@RequestParam(defaultValue = "蜀国") String key) {
        return redisTemplate.opsForSet().members(key);
    }

    @GetMapping("/setSet")
    @ApiOperation(value = "set 增", notes = "将数据放入set缓存")
    public long setSet(@RequestParam(defaultValue = "蜀国") String key, @RequestParam(defaultValue = "刘备,关羽,张飞,诸葛亮,姜维,马谡,魏延") String... values) {
        return redisTemplate.opsForSet().add(key, values);
    }


    @GetMapping("/setDelKeyWithValue")
    @ApiOperation(value = "set 删key->value", notes = "从key set 中删除给定values并返回已删除元素的数量")
    public long setDel(@RequestParam(defaultValue = "蜀国") String key, @RequestParam(defaultValue = "刘备,关羽,张飞,诸葛亮,姜维,马谡,魏延") String... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    @GetMapping("/setDelKeyAll")
    @ApiOperation(value = "set 删key->random", notes = "从key set 中删除并返回一个随机成员")
    public Object setDel(@RequestParam(defaultValue = "蜀国") String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    // ---- ZSet ----
    @GetMapping("/zsetAdd")
    @ApiOperation(value = "zSet 增", notes = "将value添加到key的排序集，或者如果它已经存在则更新它的score")
    public boolean zsAdd(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "张文远") String value, @RequestParam(defaultValue = "9.9") double score) {
        return redisTemplate.opsForZSet().add(key, value, score);
    }

    @GetMapping("/zRemove")
    @ApiOperation(value = "zSet 删", notes = "从排序集中删除values 。 返回已删除元素的数量")
    public Long zRemove(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "张文远") String... values) {
        return redisTemplate.opsForZSet().remove(key, values);
    }

    @GetMapping("/zIncrementScore")
    @ApiOperation(value = "zSet score增加", notes = "增加value对应的分数并根据分数进行排序")
    public Double zIncrementScore(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "张文远") String value, @RequestParam(defaultValue = "9.9") double delta) {
        return stringRedisTemplate.opsForZSet().incrementScore(key, value, delta);
    }

    @GetMapping("/zRank")
    @ApiOperation(value = "zSet score排名", notes = "返回元素在集合的排名,有序集合是按照元素的score值由小到大排列")
    public Long zRank(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "张文远") String value) {
        return stringRedisTemplate.opsForZSet().rank(key, value);
    }

    @GetMapping("/zRange")
    @ApiOperation(value = "zSet 查：元素", notes = "获取集合的元素, 从小到大排序")
    public Set<String> zRange(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") long start, @RequestParam(defaultValue = "-1") long end) {
        return stringRedisTemplate.opsForZSet().range(key, start, end);
    }

    @GetMapping("/zRangeWithScores")
    @ApiOperation(value = "zSet 查：元素+分数", notes = "获取集合元素, 并且把score值也获取")
    public Set<ZSetOperations.TypedTuple<String>> zRangeWithScores(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") long start, @RequestParam(defaultValue = "-1") long end) {
        return stringRedisTemplate.opsForZSet().rangeWithScores(key, start, end);
    }

    @GetMapping("/zRangeByScore")
    @ApiOperation(value = "zSet 查：元素|分数", notes = "根据Score值查询集合元素")
    public Set<String> zRangeByScore(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "20") double max) {
        return stringRedisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    @GetMapping("/zRangeByScoreWithScores")
    @ApiOperation(value = "zSet 查：分数|分数|元素", notes = "根据Score值查询集合元素, 从小到大排序")
    public Set<ZSetOperations.TypedTuple<String>> zRangeByScoreWithScores(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "20") double max) {
        return stringRedisTemplate.opsForZSet().rangeByScoreWithScores(key, min, max);
    }

    @GetMapping("/zCount")
    @ApiOperation(value = "zSet 查：分数|元素数量", notes = "根据score值获取集合元素数量")
    public long zCount(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "20") double max) {
        return stringRedisTemplate.opsForZSet().count(key, min, max);
    }

    @GetMapping("/zScore")
    @ApiOperation(value = "zSet 查：元素|score", notes = "获取集合中value元素的score值")
    public double zScore(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "张文远") String value) {
        return stringRedisTemplate.opsForZSet().score(key, value);
    }

    @GetMapping("/zRemoveRange")
    @ApiOperation(value = "zSet 删除：索引|元素", notes = "移除指定索引位置的成员")
    public long zRemoveRange(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") long start, @RequestParam(defaultValue = "3") long end) {
        return stringRedisTemplate.opsForZSet().removeRange(key, start, end);
    }


    @GetMapping("/zRemoveRangeByScore")
    @ApiOperation(value = "zSet 删除：score|元素", notes = "根据指定的score值的范围来移除成员")
    public long zRemoveRangeByScore(@RequestParam(defaultValue = "魏国") String key, @RequestParam(defaultValue = "0") double min, @RequestParam(defaultValue = "20") double max) {
        return stringRedisTemplate.opsForZSet().removeRangeByScore(key, min, max);
    }
}
