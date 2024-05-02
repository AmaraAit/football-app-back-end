package com.foot.footballmatchsservice.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.foot.footballmatchsservice.entities.MatchAbstract;
import com.foot.footballmatchsservice.entities.Matchs;
@Repository
public interface MatchAbstractRepository extends JpaRepository<MatchAbstract, Long>{
	@Query(value="SELECT DISTINCT equipeInId  FROM Matchs ")
	List<String> findEquipe();
	
	@Query(value="SELECT DISTINCT league  FROM Matchs ")
	List<String> findLeague();
	
	@Query(value="SELECT DISTINCT equipeInId  FROM Matchs m WHERE m.league LIKE ?1 ")
	List<String> findEquipesByLeague(String league);
	
	@Query(value="SELECT m   FROM Matchs m WHERE m.league LIKE ?1 ")
	List<MatchAbstract> findMatchsByLeague(String league);
}
