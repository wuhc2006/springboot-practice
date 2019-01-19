package com;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.junit.Test;

/**
 * @ClassName CreateTableTest
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/19 18:53
 * @Version 1.0
 */
public class CreateTableTest {

    @Test
    public void createTable(){
        //引擎配置
        ProcessEngineConfiguration configurer = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
        configurer.setJdbcDriver("com.mysql.jdbc.Driver");
        configurer.setJdbcUrl("jdbc:mysql://localhost:3306/springboot-activity?useUnicode=true&characterEncoding=utf8");
        configurer.setJdbcUsername("root");
        configurer.setJdbcPassword("root");
        configurer.setDatabaseSchema("act");
        configurer.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

        // 获取流程引擎对象
        ProcessEngine processEngine = configurer.buildProcessEngine();

    }
}
