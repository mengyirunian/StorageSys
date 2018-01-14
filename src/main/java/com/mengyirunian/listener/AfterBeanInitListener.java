package com.mengyirunian.listener;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.mengyirunian.annotation.FuncAction;
import com.mengyirunian.annotation.FuncParent;
import com.mengyirunian.config.RootConfig;
import com.mengyirunian.entity.Resource;
import com.mengyirunian.entity.ResourceCriteria;
import com.mengyirunian.mapper.ResourceExtMapper;
import com.mengyirunian.mapper.ResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;
import org.springframework.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by Jiaxiayuan on 2018/1/12
 */
@Slf4j
@Component
public class AfterBeanInitListener implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    private ResourceMapper resourceMapper;
    @Autowired
    private ResourceExtMapper resourceExtMapper;
    @Autowired
    private RootConfig rootConfig;
    @Autowired
    @Qualifier("transactionTemplateStorage")
    private TransactionTemplate transactionTemplate;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        addModuleAndAction(event);// 添加模块与功能
    }

    private void addModuleAndAction(ContextRefreshedEvent event) {
        ResourceCriteria criteria = new ResourceCriteria();
        criteria.createCriteria()
                .andValidEqualTo(0)
                .andServiceCodeEqualTo(rootConfig.getServiceCode())
                .andServiceNameEqualTo(rootConfig.getServiceName());
        List<Resource> resourcesExist = resourceMapper.selectByExample(criteria);
        // 根容器为Spring容器
        Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(FuncParent.class);
        log.info("-->获取FuncParent的类开始");
        List<Resource> runtimeResources = Lists.newArrayList();
        packageResource(beans, runtimeResources);
        log.info("-->获取FuncParent的类结束");
        List<Resource> addResources = checkResources(resourcesExist, runtimeResources);
        Map<String, List<Resource>> map = Maps.newHashMap();
        map.put("list", addResources);
        transactionTemplate.execute((TransactionCallback<Object>) status -> {
            resourceExtMapper.batchSave(map);
            return true;
        });
    }

    private List<Resource> checkResources(List<Resource> resourcesExist, List<Resource> runtimeResources) {
        if (CollectionUtils.isEmpty(resourcesExist)) {
            return runtimeResources;
        }
        List<Resource> addResources = Lists.newArrayList();
        for (Resource runtimeResource : runtimeResources) {
            boolean find = false;
            for (Resource targetResource : resourcesExist) {
                if (checkResource(runtimeResource, targetResource)) {
                    find = true;
                    continue;
                }
            }
            if (!find) {
                addResources.add(runtimeResource);
            }
        }
        return addResources;
    }

    private boolean checkResource(Resource runtimeResource, Resource targetResource) {
        return runtimeResource.getServiceCode().equals(targetResource.getServiceCode())
                && runtimeResource.getFuncParent().equals(targetResource.getFuncParent())
                && runtimeResource.getFuncAction().equals(targetResource.getFuncAction());
    }

    private void packageResource(Map<String, Object> beans, List<Resource> runtimeResources) {
        for (Object bean : beans.values()) {
            Annotation beanAnnotation = bean.getClass().getAnnotation(FuncParent.class);
            String funcParent = ((FuncParent) beanAnnotation).id();
            String funcParentName = ((FuncParent) beanAnnotation).name();
            Resource funcParentResource = new Resource();
            funcParentResource.setFuncAction(funcParent);
            funcParentResource.setFuncActionName(funcParentName);
            funcParentResource.setServiceCode(rootConfig.getServiceCode());
            funcParentResource.setServiceName(rootConfig.getServiceName());
            funcParentResource.setFuncParent("");
            funcParentResource.setFuncParentName("");
            funcParentResource.setFuncType(0);
            funcParentResource.setValid(0);
            funcParentResource.setCreateAt(new Date());
            funcParentResource.setUpdateAt(new Date());
            runtimeResources.add(funcParentResource);
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                for (Annotation annotation : method.getDeclaredAnnotations()) {
                    if (annotation.annotationType() == FuncAction.class) {
                        Resource funcActionResource = new Resource();
                        funcActionResource.setServiceCode(rootConfig.getServiceCode());
                        funcActionResource.setServiceName(rootConfig.getServiceName());
                        funcActionResource.setFuncParent(funcParent);
                        funcActionResource.setFuncParentName(funcParentName);
                        funcActionResource.setFuncAction(((FuncAction) annotation).id());
                        funcActionResource.setFuncActionName(((FuncAction) annotation).name());
                        funcActionResource.setFuncType(1);
                        funcActionResource.setValid(0);
                        funcActionResource.setCreateAt(new Date());
                        funcActionResource.setUpdateAt(new Date());
                        runtimeResources.add(funcActionResource);
                        log.info("该方法名: {}, 所属的Controller: {}, 含义: {}", method.getName(), ((FuncAction) annotation).parent(), ((FuncAction) annotation).name());
                    }
                }
            }
        }
    }
}
