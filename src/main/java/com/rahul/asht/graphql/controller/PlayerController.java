package com.rahul.asht.graphql.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.GraphQlRequest;
import org.springframework.graphql.GraphQlResponse;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import com.rahul.asht.graphql.model.Player;
import com.rahul.asht.graphql.model.Team;
import com.rahul.asht.graphql.service.PlayerService;

@Controller
public class PlayerController {
	
	private final PlayerService playerService;
	
	@Autowired
	public PlayerController(PlayerService playerService) {
		this.playerService = playerService;
	}

	@QueryMapping
	public List<Player> findAll() {
		return playerService.findAll();
	}
	
	@QueryMapping
	public Optional<Player> findOne(@Argument Integer id) {
		return playerService.findOne(id);
	}
	
	@MutationMapping
	public Player create(@Argument String name, @Argument Team team) {
		return playerService.create(name, team);
	}
	
	public void tempMethod() {
		GraphQlResponse response;
		GraphQlRequest request;
	}
}
