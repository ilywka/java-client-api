package com.offbytwo.jenkins.model;

/**
 * @author Ilya_Sashnikau
 */
public enum WfStatus {
    NOT_EXECUTED,
    ABORTED,
    SUCCESS,
    IN_PROGRESS,
    PAUSED_PENDING_INPUT,
    FAILED,
    UNSTABLE,;
}
