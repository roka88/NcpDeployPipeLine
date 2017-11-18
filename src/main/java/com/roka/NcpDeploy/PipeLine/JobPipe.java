package com.roka.NcpDeploy.PipeLine;

public interface JobPipe{

    Object call(Object result) throws Exception;
}
