package com.ordermanager.app.services;

import java.util.HashMap;
import java.util.Map;

public class BumexMemcached {

    private static BumexMemcached bumexMemcached = null;

    private Map<String, Object> cache;

    private BumexMemcached() {
        super();
        cache = new HashMap<>();
    }

    public static BumexMemcached getInstance(){
        if( bumexMemcached == null ){
            bumexMemcached = new BumexMemcached();
        }
        return bumexMemcached;
    }

    public void set(String key, Object value){
        cache.put(key, value);
    }

    public Object get(String key){
        return cache.get(key);
    }

    public void delete(String key){
        cache.remove(key);
    }

    public static BumexMemcached getBumexMemcached() {
        return bumexMemcached;
    }

    public static void setBumexMemcached(BumexMemcached bumexMemcached) {
        BumexMemcached.bumexMemcached = bumexMemcached;
    }

}
