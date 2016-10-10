package org.renix.updater.bean;

import java.util.Date;

/**
 * @ClassName: Version
 * @Description: 版本信息bean
 * @author renzx
 * @date 2016年10月10日
 */
public class Version {
    private Integer release;
    private String tag;
    private String desp;
    private Date updateTime;

    /**
     * @return the release
     */
    public Integer getRelease() {
        return release;
    }

    /**
     * @param release the release to set
     */
    public void setRelease(Integer release) {
        this.release = release;
    }

    /**
     * @return the tag
     */
    public String getTag() {
        return tag;
    }

    /**
     * @param tag the tag to set
     */
    public void setTag(String tag) {
        this.tag = tag;
    }

    /**
     * @return the desp
     */
    public String getDesp() {
        return desp;
    }

    /**
     * @param desp the desp to set
     */
    public void setDesp(String desp) {
        this.desp = desp;
    }

    /**
     * @return the updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime the updateTime to set
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
