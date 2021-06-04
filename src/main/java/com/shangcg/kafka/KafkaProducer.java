package com.shangcg.kafka;


import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import javax.annotation.Resource;


/**
 * kafka 生产者
 * @author shangchenguang
 * TODO 未完
 */
@Component
public class KafkaProducer {

    @Resource
    private KafkaTemplate<String, Object> kafkaTemplate;

    public static final String TOPIC_TEST = "topic.test";

    public static final String TOPIC_GROUP1 = "topic.group1";

    public static final String TOPIC_GROUP2 = "topic.group2";

    public void send(String s){
        ListenableFuture<SendResult<String, Object>> future = kafkaTemplate.send(TOPIC_TEST, s);
        future.addCallback(new ListenableFutureCallback<SendResult<String, Object>>() {
            @Override
            public void onFailure(Throwable throwable) {
                //发送失败的处理

            }

            @Override
            public void onSuccess(SendResult<String, Object> stringObjectSendResult) {
                //发送成功的处理
            }
        });
    }
}
