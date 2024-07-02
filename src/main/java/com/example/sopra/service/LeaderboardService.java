package com.example.sopra.service;

import com.example.sopra.entity.User;
import com.example.sopra.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.List;
import java.util.Objects;

@Service
public class LeaderboardService {

    @Autowired
    UserService userService;

    public String showLeaderboard(Model model) {
        List<User> users = userService.findAllUsers();
        List<User> usersBySales = userService.sortBySales(users);
        List<User> usersByBuys = userService.sortByBuys(users);
        User currentUser = userService.getCurrentUser();
        int salesRank = 1;
        int buysRank = 1;

        for(User user : usersBySales) {
            if(Objects.equals(user.getUserId(), currentUser.getUserId())){
                break;
            }
            salesRank++;
        }

        for(User user : usersByBuys) {
            if(Objects.equals(user.getUserId(), currentUser.getUserId())){
                break;
            }
            buysRank++;
        }

        model.addAttribute("usersBySales", usersBySales);
        model.addAttribute("usersByBuys", usersByBuys);
        model.addAttribute("currentUser", currentUser);
        model.addAttribute("salesRank", salesRank);
        model.addAttribute("buysRank", buysRank);

        return "leaderboard";
    }
}
