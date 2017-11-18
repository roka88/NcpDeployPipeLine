package com.roka.NcpDeploy.PipeLine;


import com.roka.NcpDeploy.Observer.Subscriber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

public class OperationPipeLine {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Queue<JobPipe> jobList = new LinkedList<>();
    private JobPipe priorityJob;

    private Subscriber subscriber;
    private ExecutorService executor;

    public OperationPipeLine job(JobPipe action) {
        jobList.add(action);
        return this;
    }

    public OperationPipeLine setPriorityJob(JobPipe jobPipe) {
        this.priorityJob = jobPipe;
        return this;
    }

    public OperationPipeLine subscribe(Subscriber subscriber) {
        this.subscriber = subscriber;
        return this;
    }

    private void notifyAllObserver(Object result) {
        if (null != subscriber && null != executor) {
            executor.execute(() -> subscriber.notifyAllObserver(result));
        }
    }

    private void notifyAllObserverOfException(Exception Err) {
        if (null != subscriber && null != executor) {
            executor.execute(() -> subscriber.notifyAllObserverOfException(Err));
        }
    }

    private void notifyWorkerShutdown() {
        if (null != executor) {
            executor.shutdown();
        }
    }


    public JobPipe getJobPipe() {
        return this.jobList.peek();
    }

    public void removeJobPipe(JobPipe jobPipe) {
        jobList.remove(jobPipe);
    }

    public OperationPipeLine observerWorker(ExecutorService executor) {
        this.executor = executor;
        return this;
    }


    public void operation() {
        Object result = null;
        while (!jobList.isEmpty()) {
            try {
                JobPipe action;

                if (null != priorityJob) {
                    action = priorityJob;
                } else {
                    action = jobList.peek();
                }

                result = action.call(result);
                notifyAllObserver(result);

                if (null != priorityJob) {
                    priorityJob = null;
                } else {
                    jobList.remove();
                }
            } catch (Exception e) {
                logger.error(e.toString());
                notifyAllObserverOfException(e);
                jobList.clear();
                priorityJob = null;

                notifyWorkerShutdown();
                return;
            }
        }
        notifyWorkerShutdown();
    }


}
