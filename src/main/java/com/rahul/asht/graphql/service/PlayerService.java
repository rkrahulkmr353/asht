package com.rahul.asht.graphql.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import com.rahul.asht.graphql.model.Player;
import com.rahul.asht.graphql.model.Team;

import jakarta.annotation.PostConstruct;

@Service
public class PlayerService {
	
	private List<Player> players = new ArrayList<>();
	
	AtomicInteger id = new AtomicInteger(0);
	
	@PostConstruct
	public void init() {
		players.add(new Player(id.incrementAndGet(), "MS Dhoni", Team.CSK));
		players.add(new Player(id.incrementAndGet(), "Rohit Sharma", Team.MI));
		players.add(new Player(id.incrementAndGet(), "Jasprit Bumrah", Team.CSK));
		players.add(new Player(id.incrementAndGet(), "Rishab Pant", Team.DC));
		players.add(new Player(id.incrementAndGet(), "Suresh Raina", Team.GT));
		
	}
	
	public List<Player> findAll() {
		return players;
	}
	
	public Optional<Player> findOne(Integer id) {
		return players.stream()
					.filter(player -> player.Id() == id).findFirst();
	}
	
	public Player create(String name, Team team) {
		Player player = new Player(id.incrementAndGet(), name, team);
		players.add(player);
		return player;
	}
	
	public Player delete(Integer id) {
		Player player = players.stream()
				.filter(p -> p.Id() == id)
				.findFirst()
				.orElseThrow(() -> new IllegalArgumentException("No player with given Id found"));
		
		players.remove(player);
		return player;
	}
	
	public Player update(Integer id, String name, Team team) {
		Player updatedPlayer = new Player(id, name, team);
		Optional<Player> optional = players.stream()
				.filter(p -> p.Id() == id).findFirst();
		
		if (optional.isPresent()) {
			Player player = optional.get();
			int index = players.indexOf(player);
			players.set(index, player);
		} else {
			throw new IllegalArgumentException("Invalid Player detail");
		}
		
		return updatedPlayer;
	}
	
}
