package springtest.com.spring.example;

import java.util.Arrays;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class GreetingMessage implements InitializingBean, BeanNameAware, BeanFactoryAware, 
			ApplicationContextAware, DisposableBean{
	
	private GreetingMessage() {
		System.out.println("COnstructor");
	}
	
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		System.out.println("after properties method");		
	}
	
	public void init() {
		System.out.println("init method call");
	}

	@Override
	public void setApplicationContext(ApplicationContext appContext) throws BeansException {
		System.out.println("setApplicationContext method of Aware bean is called");
        System.out.println("setApplicationContext:: Bean Definition Names="
                + Arrays.toString(appContext.getBeanDefinitionNames()));
		
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		System.out.println("setBeanFactory method of Aware bean is called");
	    System.out.println("setBeanFactory:: Aware bean singleton = " + beanFactory.isSingleton("gretingBean"));
		
	}

	@Override
	public void setBeanName(String beanName) {
		System.out.println("setBeanName method of Aware bean is called");
	    System.out.println("setBeanName:: Bean Name defined in context = " + beanName);
		
	}	
	public void destroy() {
		System.out.println("Custom destroy method");
	}
	
}
