package com.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
 
/**
 * 对应的技术文章的链接是http://leaks.wanari.com/2017/11/28/how-to-make-custom-usernamepasswordauthenticationfilter-with-spring-security/
 * @author dell
 *
 */
@RestController
@RequestMapping("/test")
public class AuthenticationTestController {
    @RequestMapping(
        method = RequestMethod.GET
    )
    public ResponseEntity<String> test() {
        return ResponseEntity.ok("You can only see this after a successful login :)");
    }
}
