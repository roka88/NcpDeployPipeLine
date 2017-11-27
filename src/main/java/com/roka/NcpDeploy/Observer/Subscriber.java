package com.roka.NcpDeploy.Observer;

@Deprecated
public interface Subscriber{
    void add(JobObserver observer);
    void remove(JobObserver observer);
    void notifyAllObserver(Object result);
    void notifyAllObserverOfException(Exception e);

}
