package org.github.hoorf.system.gateway;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public interface SysCacheGateway {

    <T> void setCacheObject(String key, T value);

    <T> void setCacheObject(String key, T value, Integer timeout, TimeUnit timeUnit);

    boolean expire(String key, long timeout);

    boolean expire(String key, long timeout, TimeUnit unit);

    <T> T getCacheObject(String key);

    boolean deleteObject(String key);

    long deleteObject(Collection collection);

    <T> long setCacheList(String key, List<T> dataList);

    <T> List<T> getCacheList(String key);

    //<T> BoundSetOperations<String, T> setCacheSet(String key, Set<T> dataSet);

    <T> Set<T> getCacheSet(String key);

    <T> void setCacheMap(String key, Map<String, T> dataMap);

    <T> Map<String, T> getCacheMap(String key);

    <T> void setCacheMapValue(String key, String hKey, T value);

    <T> T getCacheMapValue(String key, String hKey);

    <T> List<T> getMultiCacheMapValue(String key, Collection<Object> hKeys);

    Collection<String> keys(String pattern);
}

