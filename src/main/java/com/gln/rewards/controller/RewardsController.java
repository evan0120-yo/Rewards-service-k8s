package com.gln.rewards.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.gln.rewards.client.GamesClient;

@RestController
@RequestMapping("/rewards")
public class RewardsController {

    @Value("${server.port}")
    private String serverPort;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private GamesClient gamesClient;

    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok("Rewards Service OK - Port: " + serverPort);
    }

    /**
     * Demo: Call games-service using RestTemplate with LoadBalancer
     */
    @GetMapping("/call-games-rest")
    public ResponseEntity<?> callGamesViaRestTemplate() {
        String url = "http://games-service/games/test";
        String result = restTemplate.getForObject(url, String.class);
        return ResponseEntity.ok("RestTemplate result: " + result);
    }

    /**
     * Demo: Call games-service using FeignClient (declarative)
     */
    @GetMapping("/call-games-feign")
    public ResponseEntity<?> callGamesViaFeign() {
        String result = gamesClient.test();
        return ResponseEntity.ok("Feign result: " + result);
    }

    @GetMapping("/info")
    public ResponseEntity<?> getRewardsInfo() {
        return ResponseEntity.ok("Rewards Service v1.0.0 running on port " + serverPort);
    }
}
