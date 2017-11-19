# NcpDeployPipeLine

PipeLine만 담겨있는 라이브러리로 직접 실행 파일을 만들려면 라이브러리만 사용하면 됨
라이브러리의 Pipe도 확장가능함. Ncp-Api library에 의존성이 있음

### 변경점
<pre>
0.0.2 Once Priority Job 추가
0.0.1 초기버전
</pre>

### 의존성
* ncloud-api-0.2.5.jar 가 가져야 할 의존성
<pre>
compile group: 'commons-codec', name: 'commons-codec', version: '1.4'
compile group: 'commons-lang', name: 'commons-lang', version: '2.3'
compile group: 'commons-logging', name: 'commons-logging', version: '1.1.1'
compile group: 'org.codehaus.jettison', name: 'jettison', version: '1.3.3'
compile group: 'com.jcraft', name: 'jsch', version: '0.1.44-1'
compile group: 'log4j', name: 'log4j', version: '1.2.14'
compile group: 'org.slf4j', name: 'slf4j-api', version: '1.6.0'
compile group: 'stax', name: 'stax-api', version: '1.0.1'
compile group: 'xmlpull', name: 'xmlpull', version: '1.1.3.1'
compile group: 'xpp3', name: 'xpp3_min', version: '1.1.4c'
compile group: 'com.thoughtworks.xstream', name: 'xstream', version: '1.4.2'
compile group: 'org.slf4j', name: 'slf4j-log4j12', version: '1.6.0'
</pre>
* NcpDeploy가 가 가져야 할 의존성

<pre>
compile files('libs/ncloud-api-0.2.5.jar')
compile group: 'org.eclipse.jgit', name: 'org.eclipse.jgit', version: '4.9.0.201710071750-r'
compile group: 'org.apache.commons', name: 'commons-exec', version: '1.3'
</pre>


### OperationPipeLine

1. 기본적으로 Job실행시 예외가 생기면 PipeLine은 종료가 됩니다. Job 내부에서 예외처리시는 제외
2. Job 자체는 일반큐에 넣고 하나씩 실행합니다.
3. Job의 Operation은 메인 스레드에서 실행됩니다. 변경하고 싶을 시 새로운 스레드에서 operation을 실행하면 됩니다
4. priorityJob을 설정할 수 있습니다. priorityJob은 실행 전까지 하나만 등록할 수 있으며, Job Operation이 실행 도중에 넣을 수 있으며 현재 진행중인 Job 완료 후 바로 실행됩니다


![alt text](https://github.com/roka88/NcpDeployPipeLine/blob/master/pipeline.png)


### JobPipe
<pre>
public interface JobPipe {
    Object call(Object result) throws Exception;
}
</pre>

### Job Regist
<pre>
OperationPipeLine operationPipeLine = new OperationPipeLine();
        operationPipeLine
                .job((result) -> {
                    // TODO : 실행 되거나 가공해서 반환할 Job
                    return result;
                })
                .job((result) -> {
                    // TODO : 실행 되거나 가공해서 반환할 Job
                    return result;
                })
                .operation();

</pre>

### Subscriber And JobObserver
<pre>
public interface Subscriber {
    void add(JobObserver observer);
    void remove(JobObserver observer);
    void notifyAllObserver(Object result);
    void notifyAllObserverOfException(Exception e);
}

</pre>

<pre>
public interface JobObserver {
    void notifyStatus(Object response);
}
</pre>

### Subscriber Regist
<pre>
OperationPipeLine operationPipeLine = new OperationPipeLine();
        LogSubscriber logSubscriber = new LogSubscriber();
        logSubscriber.add((result)-> {
            if (result instanceof Exception) {
                // TODO : Job 실행 중 오류 발생 시
                LoggerFactory.getLogger(this.getClass()).info(result.toString());
            } else {
                LoggerFactory.getLogger(this.getClass()).info(result.toString());
            }
        });

        operationPipeLine.subscribe(logSubscriber);
        operationPipeLine.observerWorker(Executors.newSingleThreadExecutor());
        operationPipeLine.job((result)-> {
            // TODO : 실행 되거나 가공해서 반환할 Job
            return result;
        }).operation();
</pre>

### NcpManager
1. 기본적으로 ncloud-api-0.2.5.jar에 의존성이 있음
2. ncloud-api-0.2.5.jar의 기본 Api들이 Job으로 감싸여있음
3. ncloud-api-0.2.5.jar 모든 Api들이 포함되어 있지는 않음. 필요시 직접 구현
<pre>
NcloudApiRequest ncloudApiRequest = new NcloudApiRequest();

.....
.....

AutoScalingGroupManager autoScalingGroupManager = new AutoScalingGroupManager(ncloudApiRequest);        
AutoScalingLogManager autoScalingLogManager = new AutoScalingLogManager(ncloudApiRequest);
AutoScalingPolicyManager autoScalingPolicyManager = new AutoScalingPolicyManager(ncloudApiRequest);
LaunchConfigurationManager launchConfigurationManager = new LaunchConfigurationManager(ncloudApiRequest);
ProductManager productManager = new ProductManager(ncloudApiRequest);
ScheduledActionManager scheduledActionManager = new ScheduledActionManager(ncloudApiRequest);
ServerImageManager serverImageManager = new ServerImageManager(ncloudApiRequest);
ServerManager serverManager = new ServerManager(ncloudApiRequest);
SuspendProcessManager suspendProcessManager = new SuspendProcessManager(ncloudApiRequest);

</pre>


### NcpExtendManager
1. 기본적으로 ncloud-api-0.2.5.jar에 의존성이 있음
2. ncloud-api-0.2.5.jar의 기본 Api들이 Blue/Grean Deploy에 이용할 몇 가지 Job으로 감싸여있음
3. ncloud-api-0.2.5.jar 모든 Api들이 포함되어 있지는 않음. 필요시 직접 구현

<pre>
NcloudApiRequest ncloudApiRequest = new NcloudApiRequest();
.....
.....
AutoScalingGroupExtendManager autoScalingGroupExtendManager = new AutoScalingGroupExtendManager(ncloudApiRequest);
InstanceImgExtendManager instanceImgExtendManager = new InstanceImgExtendManager(ncloudApiRequest);
LaunchConfigurationExtendManager launchConfigurationExtendManager = new LaunchConfigurationExtendManager(ncloudApiRequest);
ServerExtendManager serverExtendManager = new ServerExtendManager(ncloudApiRequest);
</pre>


### OtherManager
<pre>
BuildManager buildManager = new BuildManager();
GitCloneManager gitCloneManager = new GitCloneManager();
LogManager logManager = new LogManager();
StopJobManager stopJobManager = new StopJobManager();
UploadFileManager uploadFileManager = new UploadFileManager();
WaitManager waitManager = new WaitManager();
</pre>


