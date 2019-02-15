package com.offbytwo.jenkins.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.offbytwo.jenkins.client.util.UrlUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Ilya_Sashnikau
 */
public class WorkflowJob extends BaseModel {

    private String name;
    private String url;
    private String description;
    @JsonProperty("builds")
    private List<WorkflowJobRun> runs;

    public WorkflowJob() {
    }

    public WorkflowJob(String name, String url) {
        this.name = name;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<WorkflowJobRun> getRuns() {
        return runs;
    }

    public void setRuns(List<WorkflowJobRun> runs) {
        this.runs = runs;
    }

    public void fillBuildsWfApi() throws IOException {
        if (CollectionUtils.isNotEmpty(runs)) {
            List<WorkflowJobRun> runsWithDetails = new ArrayList<>();
            for (WorkflowJobRun run : runs) {
                WorkflowJobRun wfRunsWithDetails = client.get(run.getUrl() + "/wfapi", new TypeReference<WorkflowJobRun>() {}, false);
                wfRunsWithDetails.setUrl(run.getUrl());
                fillWorkflowData(wfRunsWithDetails);
                runsWithDetails.add(wfRunsWithDetails);
            }
            runs = runsWithDetails;
        }
    }

    private void fillWorkflowData(WorkflowJobRun workflowJobRun) throws IOException {
        workflowJobRun.setClient(client);
        if (CollectionUtils.isNotEmpty(workflowJobRun.getStages())) {
            fillStageData(workflowJobRun);
        }
        workflowJobRun.fillChangeSetsWfApi();
    }

    private void fillStageData(WorkflowJobRun workflowJobRun) throws IOException {
        for (WfStage stage : workflowJobRun.getStages()) {
            stage.setClient(client);
            stage.setUrl(UrlUtils.join(workflowJobRun.getUrl(), "execution/node/" + stage.getId()));
            stage.fillNodes();
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
