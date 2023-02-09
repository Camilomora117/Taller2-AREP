package edu.escuelaing.arem.ASE.app;

import java.util.concurrent.ConcurrentHashMap;

public class Cache {

    private static Cache instance;
    private static ConcurrentHashMap<String, String> cache;

    /**
     * Constructor Class
     */
    public Cache(){
        cache = new ConcurrentHashMap<String, String>();
    }

    /**
     * True if the movie is cache, false otherwise
     * @param title title of the movie
     * @return Boolean
     */
    public boolean isOnCache(String title){
        return cache.containsKey(title);
    }

    /**
     * Add movie to cache
     * @param title title of the movie
     * @param json movie information
     */
    public void addMovie(String title, String json){
        cache.put(title, json);
    }

    /**
     * Description of the movie
     * @param title title of the movie
     * @return String movie information
     */
    public String getMovieDescription(String title){
        return cache.get(title);
    }

    /**
     * Initializer class
     * @return instance class
     */
    public static Cache getInstance() {
        if(instance == null){
            instance = new Cache();
        }
        return instance;
    }

    /**
     * Cache size
     * @return int cache size
     */
    public int size(){
        return cache.size();
    }

    /**
     * Clean cache
     */
    public void clear(){
        cache.clear();
    }
}