package com.mfa;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Collection;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mfa.config.ApplicationProperties;



@SpringBootApplication
@RestController
@ComponentScan(basePackages = "com.mfa")
@EnableConfigurationProperties({ApplicationProperties.class})
public class MfaApplication {

	
	private static final Logger logger = LoggerFactory.getLogger(MfaApplication.class);

    private static ConfigurableApplicationContext ctx = null;
    
    private final Environment env;

    public MfaApplication(Environment env) {
        this.env = env;
    }

    
    @PostConstruct
    public void initApplication() {
        Collection<String> activeProfiles = Arrays.asList(env.getActiveProfiles());
        
    }

    /**
     * Main method, used to run the application.
     *
     * @param args the command line arguments.
     */
    public static void main(String[] args) {
        
    	SpringApplication app = new SpringApplication(MfaApplication.class);
        ctx  = app.run(args);
        Environment env = ctx.getEnvironment();
        
        logApplicationStartup(env);
        
    }

    private static void logApplicationStartup(Environment env) {
        String protocol = "http";
        if (env.getProperty("server.ssl.key-store") != null) {
            protocol = "https";
        }
        String serverPort = env.getProperty("server.port");
        String contextPath = env.getProperty("server.servlet.context-path");
        if (contextPath == null || (contextPath != null && "".equalsIgnoreCase(contextPath))) {
            contextPath = "/";
        }
        String hostAddress = "localhost";
        try {
            hostAddress = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            logger.warn("The host name could not be determined, using `localhost` as fallback");
        }
        logger.info("\n----------------------------------------------------------\n\t" +
                "Application '{}' is running! Access URLs:\n\t" +
                "Local: \t\t{}://localhost:{}{}\n\t" +
                "External: \t{}://{}:{}{}\n\t" +
                "Profile(s): \t{}\n----------------------------------------------------------",
            env.getProperty("spring.application.name"),
            protocol,
            serverPort,
            contextPath,
            protocol,
            hostAddress,
            serverPort,
            contextPath,
            env.getActiveProfiles());
    }
    
    public static <T> T getBean(Class<T> cls) {
		return ctx.getBean(cls);
	}

    @RequestMapping(value = "/")
    public void hello(HttpServletResponse response) throws IOException {
       response.sendRedirect("/MFAPage.html");
    }
}
