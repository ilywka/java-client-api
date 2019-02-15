package com.offbytwo.jenkins.model;

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
public class WorkflowJobRun extends BaseModel {

    private String id;
    private String name;
    @JsonProperty("status")
    private WfStatus result;
    private Long startTimeMillis;
    private Long endTimeMillis;
    private Long durationMillis;
    private Long queueDurationMillis;
    private Long pauseDurationMillis;
    private List<WfStage> stages;
    private List<WfChangeSet> changeSets;
    private String url;

    public WorkflowJobRun() {
    }

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

    public WfStatus getResult() {
        return result;
    }

    public void setResult(WfStatus result) {
        this.result = result;
    }

    public Long getStartTimeMillis() {
        return startTimeMillis;
    }

    public void setStartTimeMillis(Long startTimeMillis) {
        this.startTimeMillis = startTimeMillis;
    }

    public Long getEndTimeMillis() {
        return endTimeMillis;
    }

    public void setEndTimeMillis(Long endTimeMillis) {
        this.endTimeMillis = endTimeMillis;
    }

    public Long getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(Long durationMillis) {
        this.durationMillis = durationMillis;
    }

    public Long getQueueDurationMillis() {
        return queueDurationMillis;
    }

    public void setQueueDurationMillis(Long queueDurationMillis) {
        this.queueDurationMillis = queueDurationMillis;
    }

    public Long getPauseDurationMillis() {
        return pauseDurationMillis;
    }

    public void setPauseDurationMillis(Long pauseDurationMillis) {
        this.pauseDurationMillis = pauseDurationMillis;
    }

    public List<WfStage> getStages() {
        return stages;
    }

    public void setStages(List<WfStage> stages) {
        this.stages = stages;
    }

    public List<WfChangeSet> getChangeSets() {
        return changeSets;
    }

    public void setChangeSets(List<WfChangeSet> changeSets) {
        this.changeSets = changeSets;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void fillChangeSetsWfApi() throws IOException {
        String changesetsUrl = UrlUtils.join(url, "wfapi/changesets");
        List<WfChangeSet> changeSets = client.get(changesetsUrl, new TypeReference<List<WfChangeSet>>() {}, false);
        this.changeSets = changeSets;
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
