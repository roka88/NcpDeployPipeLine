package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.autoscaling.connection.AutoScalingLogConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.List;

public class AutoScalingLogManager extends AutoScalingLogConnection {

    public AutoScalingLogManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingLogManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public JobPipe getAutoScalingActivityLogListForJob(List<String> activityNoList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingActivityLogList(activityNoList, autoScalingGroupName, pageNo, pageSize);
    }


    public JobPipe getAutoScalingConfigurationLogListFoJob(List<String> configurationNoList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingConfigurationLogList(configurationNoList, autoScalingGroupName, pageNo, pageSize);
    }
}
