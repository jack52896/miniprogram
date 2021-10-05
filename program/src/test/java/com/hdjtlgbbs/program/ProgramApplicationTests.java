package com.hdjtlgbbs.program;

import com.hdjtlgbbs.program.dao.WxUserDao;
import com.hdjtlgbbs.program.entity.WxUserEntity;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration(classes = ProgramApplication.class)
class ProgramApplicationTests {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    KafkaProducer kafkaProducer;

    @Test
    void contextLoads() {
        redisTemplate.opsForValue().set("name", "zhangsan");
    }

    @Test
    void elastic() {

    }

    @Test
    void testKafka() {
        System.out.println("---start---");
        kafkaProducer.send("test", "hello kafka");
        System.out.println("---end---");
    }
}
@Component
class KafkaProducer {
    @Autowired
    private KafkaTemplate kafkaTemplate;

    /*
           topic 消息标题
           content 发送的消息内容主题
     */
    public void send(String topic, String content) {
        kafkaTemplate.send(topic, content);
    }
}

@Component
class KafkaComsumer{
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    WxUserDao wxUserDao;
    @KafkaListener(topics = {"test"})
    public void consumer(ConsumerRecord consumerRecord){
        redisTemplate.opsForValue().set("name", consumerRecord.value());
        System.out.println("接受的消息"+consumerRecord.value());
    }
}
