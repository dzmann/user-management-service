package com.example.usermanagementservice.config;

import com.example.usermanagementservice.converter.CredentialsConverter;
import org.dozer.CustomConverter;
import org.dozer.DozerBeanMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class DozerConfiguration {

    @Bean
    public DozerBeanMapper dozerBeanMapper() {
        List<String> mappingFiles = Arrays.asList(
                "dozer-bean-mappings.xml"
        );
        DozerBeanMapper dozerBean = new DozerBeanMapper();
        dozerBean.setMappingFiles(mappingFiles);
        return dozerBean;
    }
}
