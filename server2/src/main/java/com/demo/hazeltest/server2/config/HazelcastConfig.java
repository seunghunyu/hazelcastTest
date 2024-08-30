package com.demo.hazeltest.server2.config;

import com.hazelcast.config.Config;
import com.hazelcast.config.JoinConfig;
import com.hazelcast.config.MapConfig;
import com.hazelcast.config.NetworkConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {
    @Bean
    public Config hazelConfig() {
        Config config = new Config();

        config.addMapConfig(new MapConfig().setName("onCMS"));

        NetworkConfig networkConfig = config.getNetworkConfig();
        networkConfig.setPort(5702); // 또는 5702, 5703 등
        networkConfig.setPortAutoIncrement(false);

        JoinConfig joinConfig = networkConfig.getJoin();

        joinConfig.getMulticastConfig().setEnabled(false);
        joinConfig.getTcpIpConfig()
                .setEnabled(true)
                .addMember("192.168.24.136:5701")  // server1 8080
                .addMember("192.168.24.136:5702")  // server2 8081
                .addMember("192.168.24.136:5701"); // server3 8082
        return config;
    }
}
