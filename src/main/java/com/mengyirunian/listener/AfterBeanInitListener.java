package com.mengyirunian.listener;

import com.mengyirunian.annotation.FuncAction;
import com.mengyirunian.annotation.FuncParent;
import com.mengyirunian.entity.Resource;
import com.mengyirunian.entity.ResourceCriteria;
import com.mengyirunian.mapper.ResourceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
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

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        ResourceCriteria criteria = new ResourceCriteria();
        criteria.createCriteria().andValidEqualTo(0);
        List<Resource> resources = resourceMapper.selectByExample(criteria);
        // 根容器为Spring容器
        Map<String, Object> beans = event.getApplicationContext().getBeansWithAnnotation(FuncParent.class);
        log.info("-->获取FuncParent的类开始");
        for (Object bean : beans.values()) {
            Method[] methods = bean.getClass().getMethods();
            for (Method method : methods) {
                for(Annotation annotation : method.getDeclaredAnnotations()){
                    if(annotation.annotationType() == FuncAction.class) {
                        log.info("该方法名: {}, 所属的Controller: {}, 含义: {}", method.getName(), ((FuncAction)annotation).parent(), ((FuncAction)annotation).name());
                    }
                }
            }
        }
        log.info("-->获取FuncParent的类结束");

    }
}
