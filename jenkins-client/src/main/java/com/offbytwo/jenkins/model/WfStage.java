package com.offbytwo.jenkins.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.offbytwo.jenkins.client.util.UrlUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.IOException;
import java.util.List;

/**
 * @author Ilya_Sashnikau
 */
public class WfStage extends BaseModel {

    private String id;
    private String name;
    private String execNode;
    private WfStatus status;
    private Long startTimeMillis;
    private Long durationMillis;
    private Long pauseDurationMillis;
    @JsonProperty("stageFlowNodes")
    private List<WfExecutionNode> executionNodes;
    @JsonIgnore
    private String url;

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

    public List<WfExecutionNode> getExecutionNodes() {
        return executionNodes;
    }

    public void setExecutionNodes(List<WfExecutionNode> executionNodes) {
        this.executionNodes = executionNodes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void fillNodes() throws IOException {
        WfStage wfStage = client.get(UrlUtils.join(url, "/wfapi"), new TypeReference<WfStage>() {}, false);
        if (wfStage != null) {
            this.executionNodes = wfStage.getExecutionNodes();
        }
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
