package xyz.tomd;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Component
public class MySpringBootRouter extends RouteBuilder {

    @Value("${greeting}")
    private String greetingMessage;

    @Value("${fileMessage}")
    private String fileMessage;

    @Override
    public void configure() {
        StringBuffer messageContents = new StringBuffer();
        messageContents.append("<html><body>");

        messageContents.append(greetingMessage);
        messageContents.append("<br/>");

        if (fileMessage.length() > 0) {
            File file = new File(fileMessage);
            System.out.println("File Message :"+fileMessage+" is exist "+file.exists());
            if (file.exists()) {
                try {
                    List<String> lines = FileUtils.readLines(file, StandardCharsets.UTF_8);
                    for(String line:lines) {
                        messageContents.append(line);
                        messageContents.append("<br/>");
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }

        messageContents.append("</body></html>");
        from("servlet:/hello")
                .setBody(constant(messageContents));
    }

}
