package com.foot.footballmatchsservice.web;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.foot.footballmatchsservice.entities.Equipe;
import com.foot.footballmatchsservice.entities.MatchAbstract;
import com.foot.footballmatchsservice.entities.Matchs;
import com.foot.footballmatchsservice.metier.IMatchService;

@RestController
@CrossOrigin(origins = "http://localhost:3001/")					
public class MatchsController {
	IMatchService iMatchService;

	public MatchsController(IMatchService iMatchService) {
		this.iMatchService = iMatchService;
	}
	@RequestMapping(value = "/insert",method = RequestMethod.GET)
	public List<MatchAbstract> getMatchs() throws Exception{
		return iMatchService.getMatchsSave();
	}
	@RequestMapping(value = "/matchsinsert",method = RequestMethod.GET)
	public List<MatchAbstract> getAllMatchs() throws Exception{
		return iMatchService.getMatchsSave();
	}
	@RequestMapping(value = "/equipe/{name}",method = RequestMethod.GET)
	public List<Matchs> getMatchsByEquipe(@PathVariable String name){
		List<Matchs> liste=iMatchService.getListMatchsByEquipe(name,70);
		//Collections.sort(liste, Matchs.ComparatorDate);
		return liste;
	}
	@RequestMapping(value = "/{name}",method = RequestMethod.GET)
	public List<Matchs> getLastMatchsByEquipe(@PathVariable String name){
		List<Matchs> liste=iMatchService.getListMatchsByEquipe(name,20);
		//Collections.sort(liste, Matchs.ComparatorDate);
		return liste;
	}
	@RequestMapping(value = "/stat/{number}/{name}",method = RequestMethod.GET)
	public Equipe getStatByEquipe(@PathVariable String name,@PathVariable int number){
		//List<Matchs> liste=iMatchService.getListMatchsByEquipe(name,number);
		Equipe p=iMatchService.getPercentDeuxEquipeMarqueByEquipe(name,number);
		return p;
	}
	@RequestMapping(value = "/equipes/list",method = RequestMethod.GET)
	public List<String> getAllEquipe(){
		
		return iMatchService.getAllEquipe();
	}
	
	@RequestMapping(value = "/Leagues",method = RequestMethod.GET)
	public List<String> getAllLeague(){
		
		return iMatchService.getAllLeague();
	}
	@RequestMapping(value = "/Leagues/{league}",method = RequestMethod.GET)
	public List<String> getAllLeague(@PathVariable String league){
		
		return iMatchService.getEquipesByLeague(league);
	}
	
	
	

}
