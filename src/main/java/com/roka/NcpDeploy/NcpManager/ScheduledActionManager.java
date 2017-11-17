package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.autoscaling.connection.ScheduledActionConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.List;

public class ScheduledActionManager extends ScheduledActionConnection {

    public ScheduledActionManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ScheduledActionManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe getScheduledActionListForJob(String autoScalingGroupName, List<String> scheduledActionNameList, String startTime, String endTime, Integer pageNo, Integer pageSize, String sortedBy, String sortingOrder) {
        return (result) -> super.getScheduledActionList(autoScalingGroupName, scheduledActionNameList, startTime, endTime, pageNo, pageSize, sortedBy, sortingOrder);
    }


    public JobPipe putScheduledUpdateGroupActionForJob(String autoScalingGroupName, String scheduledActionName, Integer desiredCapacity, Integer minSize, Integer maxSize, String startTime, String endTime, String recurrenceInKST) {
        return (result) -> super.putScheduledUpdateGroupAction(autoScalingGroupName, scheduledActionName, desiredCapacity, minSize, maxSize, startTime, endTime, recurrenceInKST);
    }


    public JobPipe deleteScheduledActionForJob(String autoScalingGroupName, String scheduledActionName) {
        return (result) -> super.deleteScheduledAction(autoScalingGroupName, scheduledActionName);
    }
}
