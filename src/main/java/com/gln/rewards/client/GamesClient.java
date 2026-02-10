package com.gln.rewards.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * FeignClient for calling games-service.
 * Uses service discovery via Kubernetes - no hardcoded URLs needed.
 */
@FeignClient(name = "games-service")
public interface GamesClient {

    @GetMapping("/games/test")
    String test();

    @GetMapping("/games/info")
    String getGameInfo();
}
