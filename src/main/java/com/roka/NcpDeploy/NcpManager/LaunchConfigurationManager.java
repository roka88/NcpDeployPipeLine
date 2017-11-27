package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.autoscaling.connection.LaunchConfigurationConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.List;
@Deprecated
public class LaunchConfigurationManager extends LaunchConfigurationConnection {

    public LaunchConfigurationManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public LaunchConfigurationManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe getLaunchConfigurationListForJob(List<String> launchConfigurationNameList, Integer pageNo, Integer pageSize, String sortedBy, String sortingOrder) {
        return (result) -> super.getLaunchConfigurationList(launchConfigurationNameList, pageNo, pageSize, sortedBy, sortingOrder);
    }


    public JobPipe createLaunchConfigurationForJob(String launchConfigurationName, String serverImageProductCode, String serverProductCode, String memberServerImageNo, String loginKeyName, String userData, List<String> accessControlGroupConfigurationNoList) {
        return (result) -> super.createLaunchConfiguration(launchConfigurationName, serverImageProductCode, serverProductCode, memberServerImageNo, loginKeyName, userData, accessControlGroupConfigurationNoList);
    }


    public JobPipe deleteAutoScalingLaunchConfigurationForJob(String launchConfigurationName) {
        return (result) -> super.deleteAutoScalingLaunchConfiguration(launchConfigurationName);
    }
}
