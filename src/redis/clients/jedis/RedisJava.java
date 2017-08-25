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
		//������ӳص����ö���
		JedisPoolConfig config = new JedisPoolConfig();
		//���������
		config.setMaxTotal(30);
		//����������������
		config.setMaxIdle(10);
		//�������
		JedisPool jedisPool = new JedisPool(config,"192.168.41.131",6379);
		
		//��ú��Ķ���
		Jedis jedis = null;
		
		try {
			//ͨ�����ӳػ������
			jedis = jedisPool.getResource();
			//��������
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
