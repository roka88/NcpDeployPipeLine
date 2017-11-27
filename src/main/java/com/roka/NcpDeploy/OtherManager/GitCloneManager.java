package com.roka.NcpDeploy.OtherManager;



import com.roka.NcpDeploy.PipeLine.JobPipe;
import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.UsernamePasswordCredentialsProvider;
import org.slf4j.LoggerFactory;

import java.io.File;
@Deprecated
public class GitCloneManager {

    public JobPipe clone(String repositoryPath, String downloadDirPath, String userName, String password) {
        return (result)-> {


            File file = new File(downloadDirPath);
            Git git = Git.cloneRepository().setURI(repositoryPath).setDirectory(file).setCredentialsProvider(new UsernamePasswordCredentialsProvider(userName, password)).call();

            return git;
        };
    }

    public JobPipe removeDir(String removeDirPath) {
        return (result)-> {

            if (removeDirPath.equals("/") || removeDirPath.equals("./") || removeDirPath.equals("~/")) {
                LoggerFactory.getLogger(this.getClass()).info("Caution!! : removeDirPath / or ./ or ~/ is incorrect");
                throw new Exception("You Must Not Root Directory Path");
            }
            DefaultExecutor executor = new DefaultExecutor();
            Integer exitValue = executor.execute(CommandLine.parse("rm -rf "+removeDirPath));

            return exitValue.toString();
        };
    }

}
