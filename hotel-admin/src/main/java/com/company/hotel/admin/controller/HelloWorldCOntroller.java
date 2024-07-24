package com.company.hotel.admin.controller;

import com.company.hotel.admin.entity.ResultMsg;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class HelloWorldCOntroller {
    @GetMapping("/helloworld")
    public ResultMsg helloWorld(){
        return ResultMsg.build(200, "你好");
    }
}
