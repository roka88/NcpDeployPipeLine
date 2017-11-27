package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.autoscaling.connection.AutoScalingGroupConnection;
import com.ncloud.api.connection.NcloudApiRequest;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.List;
@Deprecated
public class AutoScalingGroupManager extends AutoScalingGroupConnection {

    // HLTHY (healthy): INSVC (in service), PNDNG (Pending status), TMNNG(Terminating)

    /*
        Auto-Scaling 관련 자원 사용 한계치

        고객 별 Auto Scaring Group 수 : 10
        Auto Scaling Group 당 로드밸런서 수 : 10
        Auto Scaling Group 당 zone 수 : 10
        Auto Scaling Group의 max size : 30
        고객 별 Launch Configuration : 100
        Launch Configuration 당 ACG 수 : 5
        Auto Scaling Group 당 scheduled action : 100
        Auto Scaling Group 당 scaling policy : 100
     */


    /*
        resumeProcesses와 suspendProcesses 액션(action)에서 사용되는 Scaling 처리 유형
        LAUNCH (LANCH): 서버 인스턴스 생성 및 서비스 투입
        TERMINATE (TERMT): 서버 인스턴스 반납
        HEALTH CHECK (HTHCK): 서버 인스턴스 헬스 체크
        REPLACE UNHEALTHY (RPUNH): 헬스에 문제가 있는 서버 인스턴스 교체
        ZONE REBALANCE (ZNRBL): Zone별 Auto Scaling Group소속 서버 인스턴스 수 rebalancing
        SCHEDULED ACTIONS (SCACT): 스케줄에 따른 액션
        ADD TO LOAD BALANCER (ADTLB): 로드밸런서에 서버 인스턴스 추가
        ALARM NOTIFICATION(ALMNO): 알람 통보
     */


    public enum AutoScalingHealthCheckType {
        SVR("SVR"), LOADB("LOADB");

        public String type;

        AutoScalingHealthCheckType(String type) {
            this.type = type;
        }

    }

    public enum HealthCheckType {
        INSVC("INSVC"), PNDNG("PNDNG"), TMNNG("TMNNG");

        public String type;

        HealthCheckType(String type) {
            this.type = type;
        }

    }

    public AutoScalingGroupManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public AutoScalingGroupManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe getAutoScalingGroupListForJob(List<String> autoScalingGroupNameList, Integer pageNo, Integer pageSize, String sortedBy, String sortingOrder) {
        return (result) -> super.getAutoScalingGroupList(autoScalingGroupNameList, pageNo, pageSize, sortedBy, sortingOrder);
    }


    public JobPipe createAutoScalingGroupForJob(String autoScalingGroupName, String launchConfigurationName, Integer desiredCapacity, Integer minSize, Integer maxSize, Integer defaultCooldown, AutoScalingHealthCheckType healthCheckTypeCode, Integer healthCheckGracePeriod, List<String> zoneNoList, List<String> loadBalancerNameList) {
        // TODO : 서버가 운영중이되고 로드벨런서가 상태까지 체크하는 시간이 평균적으로 6~8분 정도 됨. 체크 시간이 짧을 경우 AutoScalingGroup이 자체적으로 서버가 문제가 있다고 생각하고 인스턴스 내리고 다시 만드는 뫼비우스의 띠에 빠짐
        Integer miniMumCooldown = Math.max(600, defaultCooldown);
        Integer miniMumHealthCheckGracePeriod = Math.max(600, healthCheckGracePeriod);
        return (result) -> super.createAutoScalingGroup(autoScalingGroupName, launchConfigurationName, desiredCapacity, minSize, maxSize, miniMumCooldown, healthCheckTypeCode.type, miniMumHealthCheckGracePeriod, zoneNoList, loadBalancerNameList);
    }


    public JobPipe updateAutoScalingGroupForJob(String autoScalingGroupName, String launchConfigurationName, Integer desiredCapacity, Integer minSize, Integer maxSize, Integer defaultCooldown, AutoScalingHealthCheckType healthCheckTypeCode, Integer healthCheckGracePeriod, List<String> zoneNoList) {
        return (result) -> super.updateAutoScalingGroup(autoScalingGroupName, launchConfigurationName, desiredCapacity, minSize, maxSize, defaultCooldown, healthCheckTypeCode.type, healthCheckGracePeriod, zoneNoList);
    }


    public JobPipe deleteAutoScalingGroupForJob(String autoScalingGroupName) {
        return (result) -> super.deleteAutoScalingGroup(autoScalingGroupName);
    }


    public JobPipe setDesiredCapacityForJob(String autoScalingGroupName, Integer desiredCapacity) {
        return (result) -> super.setDesiredCapacity(autoScalingGroupName, desiredCapacity);
    }


    public JobPipe terminateServerInstanceInAutoScalingGroupForJob(String serverInstanceNo, Boolean shouldDecrementDesiredCapacity) {
        return (result) -> super.terminateServerInstanceInAutoScalingGroup(serverInstanceNo, shouldDecrementDesiredCapacity);
    }


    public JobPipe setServerInstanceHealthForJob(String healthStatusCode, String serverInstanceNo, Boolean shouldRespectGracePeriod) {
        return (result) -> super.setServerInstanceHealth(healthStatusCode, serverInstanceNo, shouldRespectGracePeriod);
    }
}
