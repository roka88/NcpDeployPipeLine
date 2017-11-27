package com.roka.NcpDeploy.OtherManager;



import com.roka.NcpDeploy.PipeLine.JobPipe;
import org.slf4j.LoggerFactory;
@Deprecated
public class LogManager {

    public JobPipe resultLog() {
        return (result)-> {
            LoggerFactory.getLogger(this.getClass()).info("result: "+result.toString());
            return result;
        };
    }

    public JobPipe log(String log) {
        return (result)-> {
            LoggerFactory.getLogger(this.getClass()).info(log);
            return result;
        };
    }
}
