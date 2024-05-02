package com.foot.footballmatchsservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foot.footballmatchsservice.entities.Matchs;

@Repository
public interface MatchsRepository extends JpaRepository<Matchs, Long>{
	
	
	@Query(value="SELECT m FROM Matchs m  WHERE m.equipeInId LIKE ?1 Order By m.date DESC")
	List<Matchs> findByEquipeInId(String name);
	
	@Query(value="SELECT m FROM Matchs m  WHERE m.equipeOutId LIKE ?1 Order By m.date DESC")
	List<Matchs> findByEquipeOutId(String name);

}
