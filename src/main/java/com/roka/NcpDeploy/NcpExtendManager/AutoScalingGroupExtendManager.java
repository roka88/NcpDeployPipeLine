package com.roka.NcpDeploy.NcpExtendManager;


import com.ncloud.api.autoscaling.model.AutoScalingGroupList;
import com.ncloud.api.autoscaling.model.InAutoScalingGroupServerInstance;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.NcpManager.AutoScalingGroupManager;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.Collections;
import java.util.List;
@Deprecated
public class AutoScalingGroupExtendManager extends AutoScalingGroupManager {

    public AutoScalingGroupExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingGroupExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public JobPipe checkServerStatusOfAutoScalingInService(String autoScalingGroupNameList, long statusCheckTimeMills, long defaultCooldown) {

        return (result) -> {

            AutoScalingGroupList autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
            boolean isAllInService = isServerInstancesInAutoScalingInService(autoScalingGroupList);
            while (!isAllInService) {
                Thread.sleep(statusCheckTimeMills);
                autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
                isAllInService = isServerInstancesInAutoScalingInService(autoScalingGroupList);
            }
            Thread.sleep(defaultCooldown);
            return autoScalingGroupList;
        };
    }

    public JobPipe checkServerStatusOfAutoScalingInTerminated(String autoScalingGroupNameList, long statusCheckTimeMills, long defaultCooldown) {

        return (result) -> {

            AutoScalingGroupList autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
            boolean isAllInTerminated = isServerInstancesInAutoScalingInTerminate(autoScalingGroupList);
            while (!isAllInTerminated) {
                Thread.sleep(statusCheckTimeMills);
                autoScalingGroupList = getAutoScalingGroupList(Collections.singletonList(autoScalingGroupNameList), null, null, null, null);
                isAllInTerminated = isServerInstancesInAutoScalingInTerminate(autoScalingGroupList);
            }
            Thread.sleep(defaultCooldown);
            return autoScalingGroupList;
        };
    }


    private boolean isServerInstancesInAutoScalingInTerminate(AutoScalingGroupList result) throws Exception{
        List<InAutoScalingGroupServerInstance> list = result.getAutoScalingGroupList().get(0).getInAutoScalingGroupServerInstanceList();

        for (int i=0;i <list.size();i++) {
            if (list.get(i).getLifecycleState().getCode().equals(HealthCheckType.INSVC.type) || list.get(i).getLifecycleState().getCode().equals(HealthCheckType.PNDNG.type)) {
                return false;
            }
        }
        return true;
    }

    private boolean isServerInstancesInAutoScalingInService(AutoScalingGroupList result) throws Exception{
        List<InAutoScalingGroupServerInstance> list = result.getAutoScalingGroupList().get(0).getInAutoScalingGroupServerInstanceList();

        if (0 == list.size()) {
            return false;
        }
        for (int i=0;i <list.size();i++) {
            if (list.get(i).getLifecycleState().getCode().equals(HealthCheckType.TMNNG.type) || list.get(i).getLifecycleState().getCode().equals(HealthCheckType.PNDNG.type)) {
                return false;
            }
        }
        return true;

    }
}
