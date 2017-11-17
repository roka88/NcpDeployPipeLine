package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.image.connection.MemberServerImageConnection;
import com.roka.NcpDeploy.PipeLine.JobPipe;

import java.util.List;

public class ServerImageManager extends MemberServerImageConnection {

    public enum HealthCheckType {
        INIT("INIT"), CREAT("CREAT");

        public String type;

        HealthCheckType(String type) {
            this.type = type;
        }

    }


    public ServerImageManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ServerImageManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe getMemberServerImageListForJob(List<String> memberServerImageNoList, Integer pageNo, Integer pageSize, String regionNo, List<String> platformTypeCodeList, String sortedBy, String sortingOrder) {
        return (result) -> super.getMemberServerImageList(memberServerImageNoList, pageNo, pageSize, regionNo, platformTypeCodeList, sortedBy, sortingOrder);
    }


    public JobPipe deleteMemberServerImagesForJob(List<String> memberServerImageNoList) {
        return (result) -> super.deleteMemberServerImages(memberServerImageNoList);
    }


    public JobPipe createMemberServerImageForJob(String memberServerImageName, String memberServerImageDescription, String serverInstanceNo) {
        return (result) -> super.createMemberServerImage(memberServerImageName, memberServerImageDescription, serverInstanceNo);
    }
}
