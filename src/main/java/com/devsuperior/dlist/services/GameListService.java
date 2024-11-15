package com.devsuperior.dlist.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.devsuperior.dlist.dto.GameListDTO;
import com.devsuperior.dlist.entities.GameList;
import com.devsuperior.dlist.projections.GameMinProjection;
import com.devsuperior.dlist.repositories.GameListRepository;
import com.devsuperior.dlist.repositories.GameRepository;

import jakarta.transaction.Transactional;

@Service
public class GameListService {
	
	@Autowired
	private GameListRepository gameListRepository;
	
	@Autowired
	private GameRepository gameRepository;
	
	@Transactional
	public List<GameListDTO> findAll(){
		List<GameList> result = gameListRepository.findAll();
		return result.stream().map(x -> new GameListDTO(x)).toList();
	}
	
	@Transactional
	public void move(Long listId, int sourceIndex, int destinationIndex) {
		
		List<GameMinProjection> list = gameRepository.searchByList(listId);
		
		GameMinProjection obj = list.remove(sourceIndex);
		list.add(destinationIndex,obj);
		
		int min = sourceIndex < destinationIndex ? sourceIndex : destinationIndex;
		int max = sourceIndex < destinationIndex ? destinationIndex :sourceIndex;
		
		for(int i = min; i <= max; i++) {
			gameListRepository.updateBelongingPosition(listId, list.get(i).getId(), i);
		}
	}

	
}
