package springtest.com.spring.example;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

/**
 * Hello world!
 *
 */
public class SpringTest 
{
    public static void main( String[] args )
    {
        
        BeanFactory factory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader((BeanDefinitionRegistry) factory);
        reader.loadBeanDefinitions(new ClassPathResource("spring-beans.xml"));
        
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-beans.xml");
       
        GreetingMessage msg = (GreetingMessage) context.getBean("greetingMessage");
        String greeting = msg.getMessage();
        System.out.println(greeting);
        
        ((AbstractApplicationContext) context).registerShutdownHook();
        
        ((AbstractApplicationContext) context).close();
        
        
    }
}
