package com.example.springbootmq.kafka.config;

import org.springframework.context.annotation.Configuration;

/**
 * 首先Kafka的生产者发送消息，会带有一个标识，Kafka收到消息后，会根据这个标识来存放到一个指定的topic中，
 * 一个topic保存的是一类的消息，每个topic里面会有多个分区（partition），
 *  每一个分区是一个log文件，而这个文件可以存到其他服务器，起到分布式集群和负载均衡
 *
 * 接收一个消息，然后到达topic中，再找到分区，在log文件的尾部添加这条消息
 *
 *
 * 数据消费过程（Consume）：
 * 对于消费者，不是以单独的形式存在的，每一个消费者属于一个 consumer group，
 * 一个 group 包含多个 consumer。特别需要注意的是：订阅 Topic 是以一个消费组来订阅的，
 * 发送到 Topic 的消息，只会被订阅此 Topic 的每个 group 中的一个 consumer 消费。
 * 如果所有的 Consumer 都具有相同的 group，那么就像是一个点对点的消息系统；
 * 如果每个 consumer 都具有不同的 group，那么消息会广播给所有的消费者。
 *
 * 具体说来，这实际上是根据 partition 来分的，一个 Partition，只能被消费组里的一个消费者消费，
 * 但是可以同时被多个消费组消费，消费组里的每个消费者是关联到一个 partition 的，
 * 因此有这样的说法：对于一个 topic,同一个 group 中不能有多于 partitions 个数的 consumer 同时消费,
 * 否则将意味着某些 consumer 将无法得到消息。
 */
@Configuration
public class KafkaConfig {


    // 创建topic

}
