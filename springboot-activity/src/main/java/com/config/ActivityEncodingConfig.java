package com.config;

import org.activiti.spring.SpringProcessEngineConfiguration;
import org.activiti.spring.boot.ProcessEngineConfigurationConfigurer;
import org.springframework.stereotype.Component;

/**
 * @ClassName ActivityEncodingConfig
 * @Description TODO
 * @Author Administrator
 * @Date 2019/1/20 10:02
 * @Version 1.0
 */
@Component
public class ActivityEncodingConfig implements ProcessEngineConfigurationConfigurer {
    @Override
    public void configure(SpringProcessEngineConfiguration processEngineConfiguration) {
        processEngineConfiguration.setActivityFontName("宋体");
        processEngineConfiguration.setLabelFontName("宋体");
        processEngineConfiguration.setAnnotationFontName("宋体");
    }
}
