package org.renix.updater.util;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Cache {
    private Map<String, Long> fileSizeMap = new HashMap<String, Long>();
    private Map<String, String> fileMD5Map = new HashMap<String, String>();
    private Set<String> dirSet = new HashSet<String>();

    private Map<String, Long> localFileSizeMap = new HashMap<String, Long>();
    private Map<String, String> localFileMD5Map = new HashMap<String, String>();
    private Set<String> localDirSet = new HashSet<String>();



    private Set<String> fileAddSet = new HashSet<String>();
    private Set<String> fileDelSet = new HashSet<String>();
    private Set<String> fileModSet = new HashSet<String>();

    private Set<String> dirAddSet = new HashSet<String>();
    private Set<String> dirDelSet = new HashSet<String>();
    private static Cache cache = null;

    private Cache() {

    }
    
    public static synchronized void init(){
        
    }

    public static synchronized Cache getInstance() {
        if (cache == null) {
            cache = new Cache();
        }
        return cache;
    }

    /**
     * @return the fileAddSet
     */
    public Set<String> getFileAddSet() {
        return fileAddSet;
    }

    /**
     * @param fileAddSet the fileAddSet to set
     */
    public void setFileAddSet(Set<String> fileAddSet) {
        this.fileAddSet = fileAddSet;
    }

    /**
     * @return the fileDelSet
     */
    public Set<String> getFileDelSet() {
        return fileDelSet;
    }

    /**
     * @param fileDelSet the fileDelSet to set
     */
    public void setFileDelSet(Set<String> fileDelSet) {
        this.fileDelSet = fileDelSet;
    }

    /**
     * @return the fileModSet
     */
    public Set<String> getFileModSet() {
        return fileModSet;
    }

    /**
     * @param fileModSet the fileModSet to set
     */
    public void setFileModSet(Set<String> fileModSet) {
        this.fileModSet = fileModSet;
    }

    /**
     * @return the dirAddSet
     */
    public Set<String> getDirAddSet() {
        return dirAddSet;
    }

    /**
     * @param dirAddSet the dirAddSet to set
     */
    public void setDirAddSet(Set<String> dirAddSet) {
        this.dirAddSet = dirAddSet;
    }

    /**
     * @return the dirDelSet
     */
    public Set<String> getDirDelSet() {
        return dirDelSet;
    }

    /**
     * @param dirDelSet the dirDelSet to set
     */
    public void setDirDelSet(Set<String> dirDelSet) {
        this.dirDelSet = dirDelSet;
    }

    /**
     * @return the fileSizeMap
     */
    public Map<String, Long> getFileSizeMap() {
        return fileSizeMap;
    }

    /**
     * @param fileSizeMap the fileSizeMap to set
     */
    public void setFileSizeMap(Map<String, Long> fileSizeMap) {
        this.fileSizeMap = fileSizeMap;
    }

    /**
     * @return the fileMD5Map
     */
    public Map<String, String> getFileMD5Map() {
        return fileMD5Map;
    }

    /**
     * @param fileMD5Map the fileMD5Map to set
     */
    public void setFileMD5Map(Map<String, String> fileMD5Map) {
        this.fileMD5Map = fileMD5Map;
    }

    /**
     * @return the dirSet
     */
    public Set<String> getDirSet() {
        return dirSet;
    }

    /**
     * @param dirSet the dirSet to set
     */
    public void setDirSet(Set<String> dirSet) {
        this.dirSet = dirSet;
    }

    /**
     * @return the localFileSizeMap
     */
    public Map<String, Long> getLocalFileSizeMap() {
        return localFileSizeMap;
    }

    /**
     * @param localFileSizeMap the localFileSizeMap to set
     */
    public void setLocalFileSizeMap(Map<String, Long> localFileSizeMap) {
        this.localFileSizeMap = localFileSizeMap;
    }

    /**
     * @return the localFileMD5Map
     */
    public Map<String, String> getLocalFileMD5Map() {
        return localFileMD5Map;
    }

    /**
     * @param localFileMD5Map the localFileMD5Map to set
     */
    public void setLocalFileMD5Map(Map<String, String> localFileMD5Map) {
        this.localFileMD5Map = localFileMD5Map;
    }

    /**
     * @return the localDirSet
     */
    public Set<String> getLocalDirSet() {
        return localDirSet;
    }

    /**
     * @param localDirSet the localDirSet to set
     */
    public void setLocalDirSet(Set<String> localDirSet) {
        this.localDirSet = localDirSet;
    }
}
