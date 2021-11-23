package com.samtha.ipldashboard.repository;

import java.util.List;

import com.samtha.ipldashboard.model.Match;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {

    List <Match> getByTeam1OrTeam2OrderByDateDesc(String teamName1, String teamName2,Pageable pageable);

    default List <Match> findLatestMatchesByTeam(String teamName,int pageNo,int pageSize){
        return getByTeam1OrTeam2OrderByDateDesc(teamName,teamName, PageRequest.of(pageNo,pageSize));
    }
}
