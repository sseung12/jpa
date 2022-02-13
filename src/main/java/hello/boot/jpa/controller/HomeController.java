package hello.boot.jpa.controller;

import hello.boot.jpa.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {



    @RequestMapping("/")
    public String home() {
        return "home";
    }
}
