package com.jettian.pojocheck.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jettian.pojocheck.config.PojoCheckConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@ControllerAdvice
public class PojoWrongExcepetionHandler {

	@Autowired
	private ApplicationContext applicationContext;
	

	  @ExceptionHandler(value= PojoWrongException.class)
	  public Object  MethodArgumentNotValidHandler(HttpServletResponse response, Exception exception)   {
	  	try
			{
				 exception.printStackTrace();
				 PrintWriter writer = response.getWriter();
				 ObjectMapper objectMapper = new ObjectMapper();
				 writer.write(objectMapper.writeValueAsString(applicationContext.getBean(PojoCheckConfig.class).pack(exception.getMessage())));
				 writer.flush();
			}
			  catch (IOException e)
			{
				 e.printStackTrace();
			}
			return null;
	  	}

}  
