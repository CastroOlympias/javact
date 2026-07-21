package com.david.fitness_bff.configurations;

import com.hazelcast.config.Config; // Added Import
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.cache.CacheManager;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.cache.RedisCacheManager;
import com.hazelcast.spring.cache.HazelcastCacheManager;

@Configuration
public class UnifiedMemoryConfiguration {

    @Bean
    public CacheManager cacheManager() {
        // Tier 1: Attempt Cloud Redis Connection
        try {
            LettuceConnectionFactory cloudFactory = new LettuceConnectionFactory("cloud-redis-url", 6379);
            cloudFactory.afterPropertiesSet();
            cloudFactory.getConnection().ping(); // Test the line
            System.out.println("🟢 Tier 1: Unified Memory initialized on Cloud Redis.");
            return RedisCacheManager.create(cloudFactory);
        } catch (Exception e) {
            System.out.println("⚠️ Tier 1 Cloud Redis unavailable. Dropping to Tier 2...");
        }

        // Tier 2: Attempt Local Redis Connection
        try {
            LettuceConnectionFactory localFactory = new LettuceConnectionFactory("localhost", 6379);
            localFactory.afterPropertiesSet();
            localFactory.getConnection().ping();
            System.out.println("🟡 Tier 2: Unified Memory initialized on Local Redis.");
            return RedisCacheManager.create(localFactory);
        } catch (Exception e) {
            System.out.println("⚠️ Tier 2 Local Redis unavailable. Deploying Tier 3 Embedded P2P Cluster...");
        }

        // Tier 3: No Redis. Initialize Hazelcast P2P Cluster with explicit instance name
        Config hzConfig = new Config();
        hzConfig.setInstanceName("fitness-bff-cluster"); // Solves 'instanceName must contain text'
        
        HazelcastInstance hazelcastInstance = Hazelcast.getOrCreateHazelcastInstance(hzConfig);
        System.out.println("🔴 Tier 3 Active: Hazelcast P2P cluster unified memory ('fitness-bff-cluster') online.");
        return new HazelcastCacheManager(hazelcastInstance);
    }
}