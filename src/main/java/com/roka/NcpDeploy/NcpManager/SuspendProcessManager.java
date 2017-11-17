package com.roka.NcpDeploy.NcpManager;


import com.ncloud.api.autoscaling.connection.SuspendProcessConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.List;

public class SuspendProcessManager extends SuspendProcessConnection {

    public SuspendProcessManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public SuspendProcessManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe suspendProcessesForJob(String autoScalingGroupName, List<String> scalingProcessCodeList) {
        return (result)-> super.suspendProcesses(autoScalingGroupName, scalingProcessCodeList);
    }


    public JobPipe resumeProcessesForJob(String autoScalingGroupName, List<String> scalingProcessCodeList) {
        return (result)-> super.resumeProcesses(autoScalingGroupName, scalingProcessCodeList);
    }


    public JobPipe getScalingProcessTypeListForJob() {
        return (result)-> super.getScalingProcessTypeList();
    }
}
