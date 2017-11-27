package com.roka.NcpDeploy.OtherManager;


import com.roka.NcpDeploy.PipeLine.JobPipe;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
@Deprecated
public class BuildManager {

    public JobPipe gradleBuild(String buildPath) {
        return (result)-> {

            DefaultExecutor executor = new DefaultExecutor();
            String command = new StringBuilder("gradle build").append(" ").append("--project-dir").append(" ").append(buildPath).toString();
            Integer exitValue = executor.execute(CommandLine.parse(command));

            return exitValue.toString();
        };
    }
}
