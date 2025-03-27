package com.gym.gym_management;

import java.net.InetAddress;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication

public class GymManagementApplication {

	public static void main(String[] args) {
		          ConfigurableApplicationContext context = SpringApplication.run(GymManagementApplication.class, args);
//                        context.getBean(User)
                          
                System.out.println("com.gym.gym_management.GymManagementApplication.main()");
                    try {
                
                    
                    System.out.println("Address "+String.valueOf(InetAddress.getLoopbackAddress().getHostAddress()) + " Port "+ InetAddress.getLoopbackAddress().getHostName());
            } catch (Exception e) {
            }
                
	}

}
