package com.roka.NcpDeploy.PipeLine;

@Deprecated
public interface JobPipe{

    Object call(Object result) throws Exception;
}
