package com.jettian.pojocheck.config;

import com.jettian.pojocheck.ascept.PojoCheckAscept;
import com.jettian.pojocheck.exception.PojoWrongExcepetionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tianjie
 * @version V1.0
 * @description 长度校验自动配置类;
 * @date 2018/8/20 10:28
 * @email tj_work@yeah.net
 */

@Configuration
public class PojoCheckAutoConfig {

    @Bean
    public PojoWrongExcepetionHandler getExceptionHander(){
        return new PojoWrongExcepetionHandler();
    }

    @Bean
    public PojoCheckAscept getLengCheckAscept(){
        return new PojoCheckAscept();
    }
}
