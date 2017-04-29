package com.myproject.javamail.JavaMailApp;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.mail.Session;

public class App 
{
    public static void main( String[] args )
    {
    	Properties prop = null;
    	InputStream input = null;
    	
    	//load smtp configuration file    	
    	prop = loadSmtpConfig(input, prop);    	    
    	
    	Session session = Session.getInstance(prop, new GMailAuthenticator("susil.itr@gmail.com", "S@hoo4110"));
    	EmailUtil.sendEmail(session, "susillipi11march@gmail.com", "Lipina Test mail", "Lipi I Love you");
    	
    	    	
    }    
    static Properties loadSmtpConfig(InputStream input, Properties props) {
    	
    	props = new Properties();
    	
    	try {
    		String filename = "config.properties";
    		input = App.class.getClassLoader().getResourceAsStream(filename);
    		
    		if(input==null){
    			System.out.println("Sorry, unable to find " + filename);
    		    return props;
    		} else {
    			System.out.println("SMTP configuraiton file loaded successfully");
    			props.load(input);
    		}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e2) {
					e2.printStackTrace();
				}
			}
		}
    	return props;
    }
     
}
