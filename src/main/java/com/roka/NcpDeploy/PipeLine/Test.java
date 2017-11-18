package com.roka.NcpDeploy.PipeLine;

import com.roka.NcpDeploy.OtherManager.StopJobManager;

public class Test {
    public void test() {
        OperationPipeLine operationPipeLine = new OperationPipeLine();
        operationPipeLine.setPriorityJob(new StopJobManager().stop());
    }
}
