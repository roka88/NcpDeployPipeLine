package com.roka.NcpDeploy.NcpExtendManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.server.model.ServerInstanceList;

import com.roka.NcpDeploy.NcpManager.ServerManager;
import com.roka.NcpDeploy.PipeLine.JobPipe;
import org.slf4j.LoggerFactory;

import java.util.List;

public class ServerExtendManager extends ServerManager {

    public ServerExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ServerExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public JobPipe waitChangedServerStatus(List<String> serverInstanceNoList, HealthCheckType currentStatus, ServerManager.HealthCheckType completeStatus, long statusCheckTimeMills) {
        return (result) -> {
            String status = currentStatus.type;
            ServerInstanceList waitResult = null;
            while (!status.equals(completeStatus.type)) {
                Thread.sleep(statusCheckTimeMills);
                waitResult = getServerInstanceList(serverInstanceNoList, null, null, null, null, null, null, null, null, null);
                status = waitResult.getServerInstanceList().get(0).getServerInstanceStatus().getCode();
                LoggerFactory.getLogger(this.getClass()).info("Instance Current Status : " + status);
            }
            return waitResult;
        };
    }
}
