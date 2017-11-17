package com.roka.NcpDeploy.NcpManager;

import com.ncloud.api.connection.NcloudApiRequest;
import com.ncloud.api.product.connection.ProductConnection;
import com.roka.NcpDeploy.PipeLine.JobPipe;


import java.util.List;

public class ProductManager extends ProductConnection {


    public ProductManager(NcloudApiRequest ncloudApiRequest) {
        super(ncloudApiRequest);
    }

    public ProductManager(NcloudApiRequest ncloudApiRequest, int httpConnectionTimeout) {
        super(ncloudApiRequest, httpConnectionTimeout);
    }


    public JobPipe getServerImageProductListForJob(String exclusionProductCode, String productCode, List<String> platformTypeCodeList, Integer blockStorageSize, String regionNo) {
        return (result) -> super.getServerImageProductList(exclusionProductCode, productCode, platformTypeCodeList, blockStorageSize, regionNo);
    }


    public JobPipe getServerProductListForJob(String exclusionProductCode, String productCode, String serverImageProductCode, String regionNo) {
        return (result) -> super.getServerProductList(exclusionProductCode, productCode, serverImageProductCode, regionNo);
    }
}
