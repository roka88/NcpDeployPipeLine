package com.roka.NcpDeploy.NcpExtendManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.model.MemberServerImageList;
import com.roka.NcpDeploy.NcpManager.ServerImageManager;
import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.Collections;
import java.util.List;

public class InstanceImgExtendManager extends ServerImageManager {

    public InstanceImgExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public InstanceImgExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe waitChangedServerImgStatusOfStaticImgNo(HealthCheckType currentStatus, HealthCheckType completeStatus, long statusCheckTimeMills) {
        return (result)-> {

            MemberServerImageList memberServerImageList = (MemberServerImageList)result;
            return waitChangedServerImgStatus(Collections.singletonList(memberServerImageList.getMemberServerImageList().get(0).getMemberServerImageNo()), currentStatus, completeStatus, statusCheckTimeMills).call(result);
        };
    }

    public JobPipe waitChangedServerImgStatus(List<String> serverImgNo, HealthCheckType currentStatus, HealthCheckType completeStatus, long statusCheckTimeMills){
        return (result)-> {
            String status = currentStatus.type;
            MemberServerImageList waitResult = null;
            while (!status.equals(completeStatus.type)) {
                Thread.sleep(statusCheckTimeMills);
                waitResult = getMemberServerImageList(serverImgNo, null, null, null, null, null, null);
                status = waitResult.getMemberServerImageList().get(0).getMemberServerImageStatus().getCode();
            }
            return waitResult;
        };
    }

}
