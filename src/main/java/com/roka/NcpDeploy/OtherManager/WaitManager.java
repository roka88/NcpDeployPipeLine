package com.roka.NcpDeploy.OtherManager;


import com.roka.NcpDeploy.PipeLine.JobPipe;
@Deprecated
public class WaitManager {

    public JobPipe waitJob(long waitTimeMills) {
        return (result) -> {
            Thread.sleep(waitTimeMills);
            return result;
        };
    }
}
