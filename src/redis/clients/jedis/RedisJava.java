package redis.clients.jedis;

import javax.swing.Spring;

import org.junit.Test;

public class RedisJava {
	
	
	
	@Test
	public void demo1()	{
		
		Jedis jedis = new Jedis("192.168.41.131",6379);
		
		jedis.set("name", "imooc");
		
		String value = jedis.get("name");
		System.out.println(value);
		jedis.close();
	}
	
	@Test
	public void demo2() {
		//获得连接池的配置对象
		JedisPoolConfig config = new JedisPoolConfig();
		//最大连接数
		config.setMaxTotal(30);
		//设置最大空闲连接数
		config.setMaxIdle(10);
		//获得连接
		JedisPool jedisPool = new JedisPool(config,"192.168.41.131",6379);
		
		//获得核心对象
		Jedis jedis = null;
		
		try {
			//通过连接池获得连接
			jedis = jedisPool.getResource();
			//设置数据
			jedis.set("name", "zhangsan");
			String value= jedis.get("name");
			System.out.println(value);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(jedis != null) {
				jedis.close();
			}
			if(jedisPool !=null) {
				jedisPool.close();
			}
		}
	}
}
