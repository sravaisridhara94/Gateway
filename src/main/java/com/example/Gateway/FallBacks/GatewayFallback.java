package com.example.Gateway.FallBacks;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fallback")
public class GatewayFallback {

    @RequestMapping("/accountsFallback")
    public String accountsFallback(){
        return "Account-Service is Down. Please try again later.";
    }

    @RequestMapping("/customersFallback")
    public String customersFallback(){
        return "Customer-Service is Down. Please try again later.";
    }

}
