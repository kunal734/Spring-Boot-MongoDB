package com.fireflink.feature.dao.impl;

import com.fireflink.feature.dao.TeamDao;
import com.fireflink.feature.models.entity.Team;
import com.fireflink.feature.repository.TeamRepository;
import com.fireflink.feature.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class TeamDaoImpl implements TeamDao {

    private final TeamRepository teamRepository;
    private final UserRepository userRepository;

    @Override
    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }

    @Override
    public Optional<Team> findByTeamName(String teamName) {
        return teamRepository.findByTeamName(teamName);
    }

    @Override
    public Page<Team> findAllTeamNames(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teamRepository.findAll(pageable);
    }

    @Override
    public Page<Team> findAllTeams(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return teamRepository.findAll(pageable);
    }

    @Override
    public String deleteTeam(String id) {
        return teamRepository.findById(id).map(team -> {
                    if (userRepository.existsByTeam(team.getTeamName())) {
                        return "Cannot Delete Team as Users are assigned with this Team.";
                    }
                    teamRepository.delete(team);
                    return "Team Deleted Successfully.";
                })
                .orElse("Team Not Found.");
    }

    @Override
    public List<Team> findActiveUsersByName(String teamName) {
        return null;
    }
}
