package tino.playtino.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin("*")
public class MainController {

    // 서버 health check
    @GetMapping("/")
    public String healthCheck(){
        return "server on";
    }
}
