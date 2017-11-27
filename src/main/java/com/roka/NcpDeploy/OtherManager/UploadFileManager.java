package com.roka.NcpDeploy.OtherManager;


import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import com.roka.NcpDeploy.PipeLine.JobPipe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
@Deprecated
public class UploadFileManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    public JobPipe uploadFile(String userName, String host, Integer port, String password, String filePath, String uploadPath) {

        return (result)-> {
            JSch jsch = new JSch();
            Session session = jsch.getSession(userName, host,port);
            session.setConfig("StrictHostKeyChecking", "no");
            session.setPassword(password);
            session.connect();
            Channel channel = session.openChannel("sftp");
            channel.connect();
            ChannelSftp channelSftp = (ChannelSftp) channel;
            File file = new File(filePath);
            FileInputStream fis = new FileInputStream(file);
            logger.info("File uploading - "+ file.getAbsolutePath());
            channelSftp.cd(uploadPath);
            channelSftp.put(fis, file.getName());
            fis.close();
            logger.info("File uploaded successfully - "+ file.getAbsolutePath());

            channel.disconnect();
            session.disconnect();
            return "ok";
        };
    }
}
