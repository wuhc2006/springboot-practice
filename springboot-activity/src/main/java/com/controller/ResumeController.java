package com.controller;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ResumeController
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/19 16:44
 * @Version 1.0
 */
@RestController
@RequestMapping("/resume")
public class ResumeController {

    @Autowired
    RepositoryService repositoryService;

    @Autowired
    RuntimeService runtimeService;

    @Autowired
    TaskService taskService;

    /**
     * 执行工作流流程
     */
    @RequestMapping("/execute")
    public void executeResumeProcess(){

        // 根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment().addClasspathResource("processes/my-process.bpmn").deploy();

        // 获取流程定义
        ProcessDefinition definition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

        // 启动流程定义，返回流程实例
        ProcessInstance instance = runtimeService.startProcessInstanceById(definition.getId());
        System.out.println("流程创建成功，当前流程实例id为："+ instance.getId());

        // 第一次执行流程任务
        Task task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("第1次执行前，任务名称：" + task.getName());
        taskService.complete(task.getId());

        // 第二次执行流程任务
        task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("第2次执行前，任务名称：" + task.getName());
        taskService.complete(task.getId());

        task = taskService.createTaskQuery().processInstanceId(instance.getId()).singleResult();
        System.out.println("任务执行完毕:" + task);



    }
}
