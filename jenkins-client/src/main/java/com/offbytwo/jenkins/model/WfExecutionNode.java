package com.offbytwo.jenkins.model;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

/**
 * @author Ilya_Sashnikau
 */
public class WfExecutionNode {

    private String id;
    private String name;
    private String execNode;
    private WfStatus status;
    private Long startTimeMillis;
    private Long durationMillis;
    private Long pauseDurationMillis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getExecNode() {
        return execNode;
    }

    public void setExecNode(String execNode) {
        this.execNode = execNode;
    }

    public WfStatus getStatus() {
        return status;
    }

    public void setStatus(WfStatus status) {
        this.status = status;
    }

    public Long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(Long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public Long getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(Long durationMillis) {
        this.durationMillis = durationMillis;
    }

    public Long getPauseDurationMillis() {
        return pauseDurationMillis;
    }

    public void setPauseDurationMillis(Long pauseDurationMillis) {
        this.pauseDurationMillis = pauseDurationMillis;
    }

    @Override
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }
}
