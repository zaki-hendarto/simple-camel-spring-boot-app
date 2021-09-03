package xyz.tomd;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Value("${greeting}")
    private String greetingMessage; 
	
    @Override
    public void configure() {
        from("servlet:/hello")
                .setBody(constant(greetingMessage));
    }

}
