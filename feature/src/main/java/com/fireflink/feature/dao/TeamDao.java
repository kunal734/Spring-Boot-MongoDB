package com.fireflink.feature.dao;

import com.fireflink.feature.models.entity.Team;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Optional;

public interface TeamDao {

    Team saveTeam(Team team);

    Optional<Team> findByTeamName(String teamName);

    Page<Team> findAllTeamNames(int pageNo, int pageSize);

    Page<Team> findAllTeams(int pageNo, int pageSize);

    String deleteTeam(String id);

    List<Team> findActiveUsersByName(String teamName);
}
