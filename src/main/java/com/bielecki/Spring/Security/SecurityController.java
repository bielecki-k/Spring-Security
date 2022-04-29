package com.bielecki.Spring.Security;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller

public class SecurityController {

    @GetMapping("/secured-basic")
    public String securedPage(){
        return "secured-basic";
    }

    @GetMapping("/secured-form")
    public String formSecuredPage(){
        return "secured-form";
    }

    @GetMapping("/secured-form/login")
    public String formLogin(){
        return "login"; //my customized login form/website
    }

}
