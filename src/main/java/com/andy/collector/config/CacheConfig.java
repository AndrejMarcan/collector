package com.andy.collector.config;

import java.util.concurrent.TimeUnit;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.benmanes.caffeine.cache.Caffeine;
/*
 * Initial Capacity= [integer]: Initial cache space size
 * Maximum Size=[long]: Maximum number of caches
 * Maximum Weight=[long]: Maximum Weight of Cache
 * expireAfterAccess=[duration]: expires after a fixed time after the last write or access
 * expireAfterWrite=[duration]: After the last write, it expires for a fixed time
 * refreshAfterWrite=[duration]: refresh the cache at a fixed interval after creating or last updating the cache
 * weakKeys: Open a weak reference to key
 * Weak Values: Open weak references to value
 * softValues: Open the soft reference to value
 * recordStats: Developing Statistical Functions
 * 
 * 
 * When expireAfterWrite and expireAfterAccess colleagues exist, expireAfterWrite shall prevail.
 * Maximum Size and Maximum Weight cannot be used simultaneously
 * Weak Values and softValues cannot be used simultaneously
 * 
 */


//@Configuration
//@EnableCaching
public class CacheConfig {
	
//	@Bean
//	public Caffeine caffeineConfig() {
//	    return Caffeine.newBuilder().expireAfterWrite(1, TimeUnit.MINUTES);
//	}
//	
//	@Bean
//	public CacheManager cacheManager(Caffeine caffeine) {
//	    CaffeineCacheManager caffeineCacheManager = new CaffeineCacheManager();
//	    caffeineCacheManager.setCaffeine(caffeine);
//	    return caffeineCacheManager;
//	}
//	
//	@Bean
//	public CacheManager cacheManager(Caffeine caffeine) {
//		String specAsString = "initialCapacity=100,maximumSize=500,expireAfterAccess=1m,recordStats";
//		CaffeineCacheManager cacheManager = new CaffeineCacheManager("card_cache", "user_cache");
//		cacheManager.setAllowNullValues(false);
//		//cacheManager.setCacheSpecification(specAsString);
//		cacheManager.setCaffeine(caffeine);
//		return cacheManager;
//	}
//	
//	@Bean
//	public Caffeine caffeineConfig() {
//		return Caffeine.newBuilder().initialCapacity(100)
//									.maximumSize(500)
//									.expireAfterAccess(1, TimeUnit.MINUTES)
//									.weakKeys()
//									.recordStats();
//	}
}
