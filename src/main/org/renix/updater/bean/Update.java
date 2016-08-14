package org.renix.updater.bean;

import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class Update {
    private String appName;
    private String url;
    private String iconUrl;
    private List<String> cmdList;
    private Set<String> fileSets;
    private Set<String> dirSets;
    private Version version;
    private Map<String,Version> versionMap = new HashMap<String, Version>();
    /**
     * @return the appName
     */
    public String getAppName() {
        return appName;
    }
    /**
     * @param appName the appName to set
     */
    public void setAppName(String appName) {
        this.appName = appName;
    }
    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }
    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }
    /**
     * @return the iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }
    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    /**
     * @return the cmdList
     */
    public List<String> getCmdList() {
        return cmdList;
    }
    /**
     * @param cmdList the cmdList to set
     */
    public void setCmdList(List<String> cmdList) {
        this.cmdList = cmdList;
    }
    /**
     * @return the fileSets
     */
    public Set<String> getFileSets() {
        return fileSets;
    }
    /**
     * @param fileSets the fileSets to set
     */
    public void setFileSets(Set<String> fileSets) {
        this.fileSets = fileSets;
    }
    /**
     * @return the dirSets
     */
    public Set<String> getDirSets() {
        return dirSets;
    }
    /**
     * @param dirSets the dirSets to set
     */
    public void setDirSets(Set<String> dirSets) {
        this.dirSets = dirSets;
    }
    /**
     * @return the version
     */
    public Version getVersion() {
        return version;
    }
    /**
     * @param version the version to set
     */
    public void setVersion(Version version) {
        this.version = version;
    }
    /**
     * @return the versionMap
     */
    public Map<String, Version> getVersionMap() {
        return versionMap;
    }
    /**
     * @param versionMap the versionMap to set
     */
    public void setVersionMap(Map<String, Version> versionMap) {
        this.versionMap = versionMap;
    }
    
}
