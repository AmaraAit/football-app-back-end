package com.foot.footballmatchsservice.metier;

import java.util.ArrayList;
import java.util.List;

import com.foot.footballmatchsservice.entities.Equipe;
import com.foot.footballmatchsservice.entities.MatchAbstract;
import com.foot.footballmatchsservice.entities.Matchs;

public interface IMatchService {
	
	public List<String> getAllEquipe();
	
	public List<String> getEquipesByLeague(String league);
	
	public List<String> getAllLeague();
	
	public Matchs saveMatch(Matchs m);
	
	public List<Matchs> saveAllMatchs(List<Matchs> listMatchs);
	
	public List<MatchAbstract> getMatchsSave();
	
	public Matchs updateMatchs(long id);
	
	public List<Matchs>  getListMatchsByEquipe(String name,int numbreMatch);
	
	public Equipe getPercentDeuxEquipeMarqueByEquipe(String name,int number);
	
	public List<MatchAbstract> getlistNextMatch();
	

	

}
