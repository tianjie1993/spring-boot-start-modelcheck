package com.jettian.pojocheck.ascept;

import com.jettian.pojocheck.checkintf.PojoCheckIntf;
import com.jettian.pojocheck.config.PojoCheckConfig;
import com.jettian.pojocheck.annotation.PojoMatch;
import com.jettian.pojocheck.exception.PojoWrongException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author tianjie
 * @version V1.0
 * @description 实体类校验切面;
 * @date 2018/8/20 09:03
 * @email tj_work@yeah.net
 */
@Aspect
@Component
public class PojoCheckAscept {

    @Autowired
    private ApplicationContext applicationContext;

    @Pointcut("@annotation(com.jettian.pojocheck.annotation.EnablePojoCheck)")
    public void pointPojo(){}

    @Before("pointPojo()")
    public void checkPojo(JoinPoint point){
        Object [] objects = point.getArgs();
        if(null!=objects)
        {
            for(Object o : objects){
                if(o.getClass().getName().startsWith(applicationContext.getBean(PojoCheckConfig.class).getScanPackage())){
                    Field [] fields = o.getClass().getDeclaredFields();
                    for(Field field :fields)
                    {
                        PojoMatch pojoMatch = field.getAnnotation(PojoMatch.class);
                        if(null!=pojoMatch)
                        {
                            int length = pojoMatch.value();
                            boolean cannull = pojoMatch.cannull();
                            String re =pojoMatch.RE();
                            Class checkclz = pojoMatch.checkClass();
                            try
                            {
                                Method method1 = o.getClass().getMethod("get"+toUpperCaseFirstOne(field.getName()));
                                Object value = method1.invoke(o);
                                if(null!=value && value.getClass()==String.class)
                                {
                                    if((String.valueOf(value).length()>length))
                                    {
                                        throw new PojoWrongException("字符"+field.getName()+"长度超长");
                                    }
                                }
                                if(!cannull && (null==value || (null!=value && "".equals(value))))
                                {
                                    throw new PojoWrongException(("字符"+field.getName()+"内容不能为空或空字符串"));

                                }
                                if(!"".equals(re) && null!=value)
                                {
                                    Pattern pattern = Pattern.compile(re);
                                    Matcher matcher = pattern.matcher(value.toString());
                                    if(!matcher.matches())
                                    {
                                        throw new PojoWrongException(("字符"+field.getName()+"内容与正则要求不匹配"));
                                    }
                                }
                                if(checkclz!= PojoCheckIntf.class && null!=value)
                                {
                                   String result =  ((PojoCheckIntf)checkclz.newInstance()).check(value.toString());
                                   if(null!=result && !"".equals(result))
                                   {
                                       throw new PojoWrongException(("字符"+field.getName()+"内容与不符合校验，校验类："+checkclz.getName()));

                                   }
                                }
                            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | InstantiationException e)
                            {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public  String toUpperCaseFirstOne(String s){
        if(Character.isUpperCase(s.charAt(0)))
            return s;
        else
            return (new StringBuilder()).append(Character.toUpperCase(s.charAt(0))).append(s.substring(1)).toString();
    }
}
