package com.github.ravikanth720.datastructures;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

public class TimeAwareKV<K, V> {

    Map<K, TreeMap<LocalDateTime, V>> cache;

    public TimeAwareKV() {
        cache = new HashMap<>();
    }
    
    public V get(K key) {
        TreeMap<LocalDateTime, V> values = cache.get(key);
        if (values != null) {
            return values.lastEntry().getValue();
        }

        return null; 
    }

    public V get (K key, LocalDateTime time) {
        TreeMap<LocalDateTime, V> values = cache.get(key);
        if (values == null) return null;

        if (values.containsKey(time)) {
            return values.get(time);
        } else {
            LocalDateTime lower = values.floorKey(time);
            LocalDateTime higher = values.ceilingKey(time);

            if (Duration.between(lower, time).compareTo(Duration.between(time, higher)) < 0) {
                return values.get(lower);
            } else {
                return values.get(higher);
            }
        }
    }

    public void set (K key, V value, LocalDateTime time) {
        if (!cache.containsKey(key)) {
            cache.put(key, new TreeMap<LocalDateTime, V>());
        }

        cache.get(key).put(time, value);

        System.out.println(String.format("time = %s, key = %s, value = %s", time, key, value));
    }

    public void set (K key, V value) {
        set(key, value, LocalDateTime.now());
    }

    public static void main(String[] args) {
        TimeAwareKV<String, String> cache = new TimeAwareKV<>();
        cache.set("1", "one");
        cache.set("2", "two");

        LocalDateTime old = LocalDateTime.now().minusSeconds(60);
        cache.set("3", "three", old);
        
        LocalDateTime mid = LocalDateTime.now().minusSeconds(30);
        cache.set("3", "four", mid);

        LocalDateTime nw = LocalDateTime.now().minusSeconds(10);
        cache.set("3", "five", nw);

        System.out.println(cache.get("3"));
        
        System.out.println(cache.get("3", mid));
        
        System.out.println(cache.get("3", nw.minusSeconds(50)));

    }
}
