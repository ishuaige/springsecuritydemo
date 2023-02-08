package com.niuma.demor.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author niuma
 * @create 2023-02-05 23:35
 */
@RestController
public class RController {

    @GetMapping("/r/getR1")
    public String GetR1(){
        return "这是资源服务的R1";
    }

    @GetMapping("/r/getR2")
    @PreAuthorize("hasAuthority('admin')")
    public String GetR2(){
        return "这是资源服务的R2,只有admin才能访问喔";
    }
}
