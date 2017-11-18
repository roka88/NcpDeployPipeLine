package com.roka.NcpDeploy.OtherManager;

import com.roka.NcpDeploy.PipeLine.JobPipe;

public class StopJobManager {

    public JobPipe stop() {
        return (result) -> {
            throw new Exception("Stop Jop PipeLine");
        };
    }
}
