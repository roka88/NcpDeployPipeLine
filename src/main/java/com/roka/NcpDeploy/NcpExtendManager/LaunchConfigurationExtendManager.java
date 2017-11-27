package com.roka.NcpDeploy.NcpExtendManager;


import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.model.MemberServerImageList;

import com.roka.NcpDeploy.NcpManager.LaunchConfigurationManager;
import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.List;
@Deprecated
public class LaunchConfigurationExtendManager extends LaunchConfigurationManager {

    public LaunchConfigurationExtendManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public LaunchConfigurationExtendManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }

    public JobPipe createLaunchConfigurationStaticWithImgNo(String launchConfigurationName, String serverImageProductCode, String serverProductCode, List<String> accessControlGroupConfigurationNoList, String loginKeyName, String userData) {

        return (result) -> {

            MemberServerImageList memberServerImageList = (MemberServerImageList)result;
            return createLaunchConfiguration(launchConfigurationName, serverImageProductCode, serverProductCode, memberServerImageList.getMemberServerImageList().get(0).getMemberServerImageNo(), loginKeyName, userData, accessControlGroupConfigurationNoList);
        };
    }
}
