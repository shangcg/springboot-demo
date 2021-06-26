package com.shangcg.redis.aop1;

import org.springframework.cache.Cache;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.SimpleCacheErrorHandler;
import org.springframework.lang.Nullable;
import org.springframework.util.function.SingletonSupplier;

/**
 *  执行缓存操作的基础组件，附加缓存异常处理器
 */
public abstract class AbstractCacheInvoker {

    protected SingletonSupplier<CacheErrorHandler> errorHandler;

    protected AbstractCacheInvoker() {
        // 不做任何事情的异常处理器
        this.errorHandler = SingletonSupplier.of(SimpleCacheErrorHandler::new);
    }

    protected AbstractCacheInvoker(CacheErrorHandler errorHandler) {
        this.errorHandler = SingletonSupplier.of(errorHandler);
    }

    public void setErrorHandler(CacheErrorHandler errorHandler) {
        this.errorHandler = SingletonSupplier.of(errorHandler);
    }

    public CacheErrorHandler getErrorHandler() {
        return this.errorHandler.obtain();
    }

    /**
     *  执行缓存 Get 操作
     */
    protected Cache.ValueWrapper doGet(Cache cache, Object key) {
        try {
            return cache.get(key);
        }
        catch (RuntimeException ex) {
            getErrorHandler().handleCacheGetError(ex, cache, key);
            return null;  // If the exception is handled, return a cache miss
        }
    }

    /**
     *  执行缓存 Put 操作
     */
    protected void doPut(Cache cache, Object key, @Nullable Object result) {
        try {
            cache.put(key, result);
        }
        catch (RuntimeException ex) {
            getErrorHandler().handleCachePutError(ex, cache, key, result);
        }
    }

    /**
     *  执行缓存 Evict 操作
     */
    protected void doEvict(Cache cache, Object key) {
        try {
            cache.evict(key);
        }
        catch (RuntimeException ex) {
            getErrorHandler().handleCacheEvictError(ex, cache, key);
        }
    }

    /**
     *  执行缓存 Clear 操作
     */
    protected void doClear(Cache cache) {
        try {
            cache.clear();
        }
        catch (RuntimeException ex) {
            getErrorHandler().handleCacheClearError(ex, cache);
        }
    }
}
