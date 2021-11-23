package com.samtha.ipldashboard.controller;

import com.samtha.ipldashboard.model.Team;
import com.samtha.ipldashboard.repository.MatchRepository;
import com.samtha.ipldashboard.repository.TeamRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TeamController {
    
    private static final Logger log = LoggerFactory.getLogger(TeamController.class);
    @Autowired
    private TeamRepository teamRepository;
    @Autowired
    private MatchRepository matchRepository;
    

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        log.info("Getting team {}", teamName);
        Team team= teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatchesByTeam(teamName,0,4));

        return team;
    }
}
