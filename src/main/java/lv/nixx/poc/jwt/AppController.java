package lv.nixx.poc.jwt;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {

    @GetMapping("/hello")
    public String hello() {
        return "Hello from secured resource!";
    }

    @GetMapping("/admin")
    public String admin() {
        return "Hello Admin from secured resource!";
    }

    @GetMapping("/ro")
    public String ro() {
        return "Hello Ro from secured resource!";
    }

    @GetMapping("/power")
    public String power() {
        return "Hello Power from secured resource!";
    }


}

