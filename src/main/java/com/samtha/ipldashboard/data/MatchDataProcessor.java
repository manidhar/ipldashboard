package com.samtha.ipldashboard.data;


import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.batch.item.ItemProcessor;

import com.samtha.ipldashboard.model.Match;

public class MatchDataProcessor implements ItemProcessor<MatchInput, Match>{
	

	  private static final Logger log = LoggerFactory.getLogger(MatchDataProcessor.class);

	  @Override
	  public Match process(final MatchInput matchInput) throws Exception {
	    Match match= new Match();
	    match.setId(Long.parseLong(matchInput.getId()));
	    match.setCity(matchInput.getCity());
	    match.setDate(LocalDate.parse(matchInput.getDate()));
	    match.setManOftheMatch(matchInput.getPlayer_of_match());
	    match.setVenue(matchInput.getVenue());
	    match.setNeutralVenue(matchInput.getNeutral_venue());
	    
	    // Making sure that team1 is the team which plays first innings and team 2 always plays the second innings. According we will set the team 1 and team2
	    // We will be reorder the teams based on toss results
	    
	    String firstInningsTeam;
	    String secondInningsTeam;
	    
	    if("bat".equals(matchInput.getToss_decision())) {
	    	firstInningsTeam=matchInput.getToss_winner();
	    	secondInningsTeam=matchInput.getToss_winner().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
	    }else {
	    	secondInningsTeam=matchInput.getToss_winner();
	    	firstInningsTeam=matchInput.getToss_winner().equals(matchInput.getTeam1())?matchInput.getTeam2():matchInput.getTeam1();
	    }
	    match.setTeam1(getTeamName(firstInningsTeam));
	    match.setTeam2(getTeamName(secondInningsTeam));
	    match.setTossDecision(matchInput.getToss_decision());
	    match.setTossWinner(matchInput.getToss_winner());
	    match.setEleminator(matchInput.getEleminator());
	    match.setUmpire1(matchInput.getUmpire1());
	    match.setUmpire2(matchInput.getUmpire2());
	    match.setResult(matchInput.getResult());
	    match.setResultMargin(matchInput.getResult_margin());
	    match.setWinMethod(matchInput.getMethod());
	    match.setMatchWinner(matchInput.getWinner());
	    return match;
	  }
	  
	  private String getTeamName(String teamName) {
		  String result=teamName;
		  if("Delhi Daredevils".equals(teamName)) {
			  result="Delhi Capitals";
		  }else if("Deccan Chargers".equals(teamName)) {
			  result="Sunrisers Hyderabad";
		  }else if("Pune Warriors".equals(teamName)) {
			  result="Rising Pune Supergiants";
		  }
		  return result;
	  }
}
