package org.example.redis.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@RestController
public class RedisV1StringController {
    private final StringRedisTemplate stringRedisTemplate;

    /* http://redisgate.kr/redis/clients/spring_strings.php 부가적인 정보는 위 참조 */

    /** SET: key-value 저장 */
    @GetMapping("/v1/set/{key}")
    public String set(@PathVariable("key") String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        String msg = "set -> Ok";
        valueOperations.set(key,"value-"+key); // redis 에 key,value 설정
        log.info(msg);
        return msg;
    }

    /** MSET: 여러 개 key-value 를 저장한다 */
    @GetMapping("/v1/mset/{key}")
    public String mset(@PathVariable("key") String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();

        String msg = "MSET(multiple-set) -> OK";
        HashMap<String,String> keyValues = new HashMap<>();
        keyValues.put(key+"01", "value-01");
        keyValues.put(key+"02", "value-02");
        keyValues.put(key+"03", "value-03");

        valueOperations.multiSet(keyValues); // return void
        log.info(msg);
        return msg;
    }

    /** GET: 1개의 Key 조회  */
    @GetMapping("/v1/get/{key}")
    public String get(@PathVariable("key") String key) {
        String result = stringRedisTemplate.opsForValue().get(key);
        log.info("GET {} {}", "->", result);
        return result;
    }

    /** MGET: 여러개 key 조회 */
    @GetMapping("/v1/mget/{key}")
    public String mget(@PathVariable("key") String key) {
        ValueOperations<String,String> valueOperations = stringRedisTemplate.opsForValue();
        String msg = "MGET(multiple-mget) -> OK";

        List<String> strings = List.of(key + "01", key + "02", key + "03");

        List<String> result = valueOperations.multiGet(strings);
        msg+= result;
        log.info(msg);

        return msg;
    }

    /** DEL: 삭제 */
    @GetMapping("v1/del/{key}")
    public String delete(@PathVariable("key") String key) {
        Boolean result = stringRedisTemplate.delete(key);
        log.info("Delete {} {}", "->", result);

        var keys = new ArrayList<String>();
        keys.add("key2");
        keys.add("key3");
        Long result2 = stringRedisTemplate.delete(keys);
        log.info("Delete {} {}", "->", result2);
        return String.valueOf(result2);
    }

}
