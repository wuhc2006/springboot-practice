package com;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringbootActivityApplicationTests {

    @Test
    public void contextLoads() {
    }

    /**
     * 发布流程
     */
    @Test
    public void deploy() throws Exception{
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 获取仓库服务的实例
        Deployment deployment = processEngine.getRepositoryService().createDeployment()
                .addClasspathResource("processes/simple-process.bpmn")
                .addClasspathResource("processes/simple-process.png")
                .deploy();
        System.out.println("发布的id为："+deployment.getId()+"，name为："+deployment.getName());
    }

    /**
     * 启动流程
     */
    @Test
    public void startProcess() throws Exception{
        // 获取流程引擎
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 启动流程
        ProcessInstance instance = processEngine.getRuntimeService().startProcessInstanceByKey("myProcess_1");
        System.out.println("pid: "+ instance.getId() + ",activitiId: " + instance.getActivityId());
    }

    @Test
    public void queryMyTask() throws Exception{
        // 制定任务对象
        String assignee = "张三";
        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 查询任务列表
        List<Task> taskList = processEngine.getTaskService()
                .createTaskQuery()
                .taskAssignee(assignee)
                .list();
        for(Task task : taskList){
            System.out.println("task id: " + task.getId() + ", task name: " + task.getName());
            System.out.println("********************************");
        }
    }

    @Test
    public void completeTask() throws Exception{
        String taskId = "101";

        // 获取流程引擎对象
        ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
        // 完成任务
        processEngine.getTaskService().complete(taskId);
        System.out.println("完成任务！");
    }
}

