package com.controller;

import com.dto.ProcessDto;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

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

    @RequestMapping("/list")
    public List<ProcessDto> queryDefinitionList(){
        ProcessDefinitionQuery query = repositoryService.createProcessDefinitionQuery();
        query.processDefinitionKey("myProcess_1");//从数据act_re_procdef获知
        query.orderByDeploymentId().desc();

        // 分页查询
        query.listPage(0, 10);
        List<ProcessDefinition> list = query.list();
        List<ProcessDto> dtoList = new ArrayList<>();
        for(ProcessDefinition def : list){
            ProcessDto dto = new ProcessDto();
            dto.setId(def.getId());
            dto.setName(def.getName());
            dto.setKey(def.getKey());
            dto.setDeploymentId(def.getDeploymentId());
            dto.setResourceName(def.getResourceName());
            dto.setVersion(def.getVersion());
            dtoList.add(dto);
        }
        return dtoList;
    }

    /**
     * 执行工作流流程
     */
    @RequestMapping("/execute")
    public void executeResumeProcess(){

        // 根据bpmn文件部署流程
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/simple-process.bpmn")
                .addClasspathResource("processes/simple-process.png")
                .deploy();

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
