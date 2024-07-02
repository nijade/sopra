package com.example.sopra.controller;

import com.example.sopra.service.LeaderboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LeaderboardController {

    @Autowired
    LeaderboardService leaderboardService;

    @GetMapping("/leaderboard")
    public String showLeaderboard(Model model) {
        return leaderboardService.showLeaderboard(model);
    }


}
