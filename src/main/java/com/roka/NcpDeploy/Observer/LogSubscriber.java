package com.roka.NcpDeploy.Observer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
@Deprecated
public class LogSubscriber implements Subscriber {

    List<JobObserver> subscribes = Collections.synchronizedList(new ArrayList<>());

    @Override
    public void add(JobObserver observer) {
        subscribes.add(observer);
    }

    @Override
    public void remove(JobObserver observer) {
        subscribes.remove(observer);
    }

    @Override
    public void notifyAllObserver(Object result) {
        for (JobObserver observer : subscribes) {
            observer.notifyStatus(result);
        }
    }

    @Override
    public void notifyAllObserverOfException(Exception e) {
        for (JobObserver observer : subscribes) {
            observer.notifyStatus(e);
        }
    }
}
