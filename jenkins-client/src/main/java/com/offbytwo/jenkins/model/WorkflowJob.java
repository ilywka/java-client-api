package com.offbytwo.jenkins.model;

import com.fasterxml.jackson.core.type.TypeReference;
import com.offbytwo.jenkins.client.util.UrlUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import java.io.IOException;
import java.util.List;

/**
 * @author Ilya_Sashnikau
 */
public class WorkflowJob extends BaseModel {

    private String name;
    private String url;
    private String description;
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
        String runsUrl = UrlUtils.join(url, "wfapi/runs");
        List<WorkflowJobRun> workflowJobRuns = client.get(runsUrl, new TypeReference<List<WorkflowJobRun>>() {
        }, false);
        if (CollectionUtils.isNotEmpty(workflowJobRuns)) {
            for (WorkflowJobRun workflowJobRun : workflowJobRuns) {
                fillWorkflowData(workflowJobRun);
            }
            this.runs = workflowJobRuns;
        }
    }

    private void fillWorkflowData(WorkflowJobRun workflowJobRun) throws IOException {
        String workflowJobRunUrl = UrlUtils.join(url, workflowJobRun.getId());
        workflowJobRun.setUrl(workflowJobRunUrl);
        workflowJobRun.setClient(client);
        if (CollectionUtils.isNotEmpty(workflowJobRun.getStages())) {
            fillStageData(workflowJobRun, workflowJobRunUrl);
        }
        workflowJobRun.fillChangeSetsWfApi();
    }

    private void fillStageData(WorkflowJobRun workflowJobRun, String workflowJobRunUrl) throws IOException {
        for (WfStage stage : workflowJobRun.getStages()) {
            stage.setClient(client);
            stage.setUrl(UrlUtils.join(workflowJobRunUrl, "execution/node/" + stage.getId()));
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
