package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.autoscaling.connection.AutoScalingPolicyConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.List;
@Deprecated
public class AutoScalingPolicyManager extends AutoScalingPolicyConnection {

    public AutoScalingPolicyManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingPolicyManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public JobPipe getAutoScalingPolicyListForJob(List<String> policyNameList, String autoScalingGroupName, Integer pageNo, Integer pageSize) {
        return (result) -> super.getAutoScalingPolicyList(policyNameList, autoScalingGroupName, pageNo, pageSize);
    }

    public JobPipe putScalingPolicyForJob(String policyName, String autoScalingGroupName, String adjustmentTypeCode, Integer scalingAdjustment, Integer cooldown, Integer minAdjustmentStep) {
        return (result) -> super.putScalingPolicy(policyName, autoScalingGroupName, adjustmentTypeCode, scalingAdjustment, cooldown, minAdjustmentStep);
    }

    public JobPipe deletePolicyForJob(String policyName, String autoScalingGroupName) {
        return (result) -> super.deletePolicy(policyName, autoScalingGroupName);
    }

    public JobPipe executePolicyForJob(String policyName, String autoScalingGroupName, Boolean honorCooldown) {
        return (result) -> super.executePolicy(policyName, autoScalingGroupName, honorCooldown);
    }

    public JobPipe getAdjustmentTypeListForJob() {
        return (result) -> super.getAdjustmentTypeList();
    }
}
