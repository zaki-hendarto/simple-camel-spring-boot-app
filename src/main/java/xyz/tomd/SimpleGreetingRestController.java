package xyz.tomd;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class SimpleGreetingRestController {
    @Value("${greeting}")
    private String greetingMessage;

    @Value("${fileMessage}")
    private String fileMessage;

    @GetMapping("/greeting")
    public Map<String, String> greeting() {
        HashMap<String, String> map = new HashMap<>();
        map.put("greeting", greetingMessage);
        map.put("message", "");
        if (fileMessage.length() > 0) {
            File file = new File(fileMessage);
            System.out.println("File Message :" + fileMessage + " is exist " + file.exists());
            if (file.exists()) {
                try {
                    map.put("message", FileUtils.readFileToString(file, Charset.defaultCharset()));

                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        return map;
    }
}
