package hello.boot.jpa.controller;


import hello.boot.jpa.domain.Member;
import hello.boot.jpa.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.persistence.GeneratedValue;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;


    @GetMapping("/member")
    public String CreateUserForm() {

        return "member";
    }

    @PostMapping("/member")
    public String CreateUser(@ModelAttribute Member member) {


        return "redirect:/main";
    }
}
