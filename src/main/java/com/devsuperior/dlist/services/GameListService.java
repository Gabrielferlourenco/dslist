package com.devsuperior.dlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dlist.dto.GameListDTO;
import com.devsuperior.dlist.dto.GameMinDTO;
import com.devsuperior.dlist.entities.GameList;
import com.devsuperior.dlist.repositories.GameListRepository;

import jakarta.transaction.Transactional;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Transactional
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
}
