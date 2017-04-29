package com.myproject.javamail.JavaMailApp;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

public class GMailAuthenticator extends Authenticator {
	
	private String user;
    private String pw;
    
    public GMailAuthenticator (String username, String password) {
       super();
       this.user = username;
       this.pw = password;
    }
    
   public PasswordAuthentication getPasswordAuthentication()  {
      return new PasswordAuthentication(user, pw);
   }

}
