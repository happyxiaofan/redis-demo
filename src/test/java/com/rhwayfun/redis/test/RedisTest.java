package com.rhwayfun.redis.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 
 * @ClassName: RedisTest 
 * @Description: TODO
 * @author ZhongCB
 * @date 2016年8月8日 下午4:52:35 
 *
 */
public class RedisTest extends BaseTest {

	@Autowired
	private RedisTemplate<String, Object> redisTemplate;

	/**
	 * 测试插入与获取Redis的数据
	 * @Title: testPutAndGet 
	 * @Description: TODO
	 * @throws
	 */
	@Test
	public void testPutAndGet() {
		redisTemplate.opsForHash().put("user", "name", "rhwayfun");
		Object object = redisTemplate.opsForHash().get("user", "name");
		System.out.println(object);
	}

	/**
	 * 测试Redis作为缓存的例子
	 * @Title: testCache 
	 * @Description: TODO
	 * @throws InterruptedException
	 * @throws
	 */
	@Test
	public void testCache() throws InterruptedException {
		// 插入一条数据
		redisTemplate.opsForHash().put("user", "name", "rhwayfun");
		// 设置失效时间为2秒
		redisTemplate.expire("user", 2, TimeUnit.SECONDS);
		Thread.sleep(1000);
		// 1秒后获取
		Object object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("1秒后：" + object);
		Thread.sleep(1000);
		// 2秒后获取
		object = redisTemplate.opsForHash().get("user", "name");
		System.out.println("2秒后：" + object);
	}
}
