package com.roka.NcpDeploy.NcpManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.server.connection.ServerConnection;

import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.List;

public class ServerManager extends ServerConnection {

    public enum HealthCheckType {
        RUN("RUN"), NSTOP("NSTOP");

        public String type;

        HealthCheckType(String type) {
            this.type = type;
        }

    }

    public ServerManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);

    }

    public ServerManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);

    }

    public JobPipe startServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result) -> super.startServerInstances(serverInstanceNoList);
    }

    public JobPipe stopServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result) -> super.stopServerInstances(serverInstanceNoList);
    }

    public JobPipe terminateServerInstancesForJob(List<String> serverInstanceNoList)  {
        return (result)-> super.terminateServerInstances(serverInstanceNoList);
    }

    public JobPipe getServerInstanceListForJob(List<String> serverInstanceNoList, Integer pageNo, Integer pageSize, String serverInstanceStatusCode, String internetLineTypeCode, String regionNo, String baseBlockStorageDiskTypeCode, String baseBlockStorageDiskDetailTypeCode, String sortedBy, String sortingOrder)  {
        return (result)-> super.getServerInstanceList(serverInstanceNoList, pageNo, pageSize, serverInstanceStatusCode, internetLineTypeCode, regionNo, baseBlockStorageDiskTypeCode, baseBlockStorageDiskDetailTypeCode, sortedBy, sortingOrder);
    }

    public JobPipe createServerInstancesForJob(String serverImageProductCode, String serverProductCode, String memberServerImageNo, String serverName, String serverDescription, String loginKeyName, Boolean isProtectServerTermination, Integer serverCreateCount, Integer serverCreateStartNo, String internetLineTypeCode, String feeSystemTypeCode, String userData, String zoneNo, List<String> accessControlGroupConfigurationNoList) {
        return (result)-> super.createServerInstances(serverImageProductCode, serverProductCode, memberServerImageNo, serverName, serverDescription, loginKeyName, isProtectServerTermination, serverCreateCount, serverCreateStartNo, internetLineTypeCode, feeSystemTypeCode, userData, zoneNo, accessControlGroupConfigurationNoList);
    }

    public JobPipe getRootPasswordForJob(String serverInstanceNo, String privateKey) {
        return (result)-> super.getRootPassword(serverInstanceNo, privateKey);
    }

    public JobPipe rebootServerInstancesForJob(List<String> serverInstanceNoList) {
        return (result)-> super.rebootServerInstances(serverInstanceNoList);
    }

    public JobPipe changeServerInstanceSpecForJob(String serverInstanceNo, String serverProductCode) {
        return (result)-> super.changeServerInstanceSpec(serverInstanceNo, serverProductCode);
    }

}
