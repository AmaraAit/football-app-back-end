package com.foot.footballmatchsservice.metier;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.hibernate.sql.ast.tree.expression.Collation;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.foot.footballmatchsservice.dao.MatchAbstractRepository;
import com.foot.footballmatchsservice.dao.MatchsNextRepository;
import com.foot.footballmatchsservice.dao.MatchsRepository;
import com.foot.footballmatchsservice.entities.Equipe;
import com.foot.footballmatchsservice.entities.MatchAbstract;
import com.foot.footballmatchsservice.entities.Matchs;
import com.foot.footballmatchsservice.entities.MatchsNext;


@Service

public class MatchsServiceImpl implements IMatchService{

	/**
	 * @param matchsRepository
	 * @param matchsNextRepository
	 * @param abstractRepository
	 */
	MatchsRepository matchsRepository;
	MatchsNextRepository matchsNextRepository;
	MatchAbstractRepository abstractRepository;
	
	public MatchsServiceImpl(MatchsRepository matchsRepository, MatchsNextRepository matchsNextRepository,
			MatchAbstractRepository abstractRepository) {
		this.matchsRepository = matchsRepository;
		this.matchsNextRepository = matchsNextRepository;
		this.abstractRepository = abstractRepository;
	}
	
	
	
	

	

	@Override
	public Matchs saveMatch(Matchs m) {
		matchsRepository.save(m);
		return m;
	}

	@Override
	public List<Matchs> saveAllMatchs(List<Matchs> listMatchs) {
		matchsRepository.saveAll(listMatchs);
		return listMatchs;
	}
	
	

	@Override
	public Matchs updateMatchs(long id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<MatchAbstract> insertMatchs() {
		String url=null;
		String[] saisons= {"2010-2011",	"2011-2012","2012-2013",
				"2013-2014","2014-2015","2015-2016","2016-2017",
				"2017-2018","2018-2019","2019-2020","2020-2021",
				"2021-2022","2022-2023","2023-2024"};
	    int [] index= {9,12,20,11,13,23,32,37};
	    String[] championnat= {"Premier-League","La-Liga","Bundesliga","Serie-A",
	    		"Ligue-1","Eredivisie","Primeira-Liga","Belgian-Pro-League"};
	    String com="/calendrier/Calendrier-et-resultats-";
	    String h2tp="https://fbref.com/fr/comps/";
	    List<MatchAbstract> nextMatch=new ArrayList<>();
	    int i=0;
		for(String ch : championnat) {
			for (String s : saisons) {
				url=h2tp+index[i]+"/"+s+com+s+"-"+ch;
				for (MatchAbstract matchsNext : getListMatchs(url, s,ch)) {
					nextMatch.add(matchsNext);
				}
					
			}
			i+=1;
		}
		return nextMatch;
	}
	
	
	public List<MatchAbstract> getListMatchs(String url,String saison,String championnat) {
		
		List<MatchAbstract> listMatch=new ArrayList<>();
		try {
			
			Document doc = Jsoup.connect(url).get();
			Elements newsHeadlines = doc.select("table.stats_table");
			String d=null;
			String score;
			int bEI=-1;
			int bEO=-1;
			for (Element headline :  newsHeadlines.select("tr")) {
				d=headline.select("td[data-stat=date]").text();
				Elements scoreurl=headline.select("td[data-stat=score]");
				score=headline.select("td[data-stat=score]").text();;
				if(score!="" && !score.contains("(")) {
					String a="–";
					String b=" ";
				    score=score.replace(a, b);
				    System.out.println(d+"---------");
					String [] but=score.split(b);
					
					if(but.length>1) {
						bEI=Integer.parseInt(but[0]);
						bEO=Integer.parseInt(but[1]);
						System.out.println();
						if(d!="") {
						MatchAbstract m=new Matchs(d, championnat,headline.select("td[data-stat=home_team]").text() , headline.select("td[data-stat=away_team]").text(), bEI, bEO);
						abstractRepository.save(m);
						listMatch.add(m);		
						
						}
					}
				}else if(headline.select("td[data-stat=home_team]").text()!=""){
					System.out.println(d+"---------");
					MatchAbstract m=new MatchsNext(d, championnat,headline.select("td[data-stat=home_team]").text() , headline.select("td[data-stat=away_team]").text());
					abstractRepository.save(m);		
					listMatch.add(m);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return listMatch;
	}
	@Override
	public List<MatchAbstract> getMatchsSave() {
		
		return insertMatchs();
	}

	public List<Matchs>  getListMatchsByEquipe(String name,int numbreMatch){
		List<MatchAbstract> listeMatchs=abstractRepository.findAll();
		Collections.reverse(listeMatchs);
		List<Matchs> listeMatchsJouer=new ArrayList<>();
		List<Matchs> listeMatchsJouerExt=new ArrayList<>();
		int i=1;
		for (MatchAbstract matchs : listeMatchs) {
			if(matchs instanceof Matchs) {
				if(matchs.getEquipeInId().contains(name)){
					listeMatchsJouer.add((Matchs) matchs);
					i+=1;
				}else if(matchs.getEquipeOutId().contains(name)) {
					listeMatchsJouerExt.add((Matchs) matchs);
					i+=1;
				}
			}
			if(i>numbreMatch) {
				break;
			}
			
		}
		listeMatchsJouer.addAll(listeMatchsJouerExt);
		
		
		return listeMatchsJouer;
	}

	@Override
	public List<MatchAbstract> getlistNextMatch() {
		// TODO Auto-generated method stub
		return null;
	}
	public Equipe getPercentDeuxEquipeMarqueByEquipe(String name,int number) {
		double i=0.0;
		List<Matchs> l=getListMatchsByEquipe(name,number);
		Collections.sort(l, Matchs.ComparatorDate);
		int nombreMatchsJouer=l.size();
		int nombreMatchsJouerDomicile=0;
		int nombreMatchsJouerExterieurs=0;
		int nombreMatchsDeuxEquipeMarqueADom=0;
		int nombreMatchsDeuxEquipeMarqueExt=0;
	    int nombreButMarqueADomicile=0;
		int nombreButMarqueAExt=0;
		int nombreButEncaisseADom=0;
		int nombreButEncaisseAExt=0;
		int nombreMatchMarqueADomicile=0;
		int nombreMatchMarqueAExt=0;
		int nombreMatchEncaisseADom=0;
		int nombreMatchEncaisseAExt=0;
		
		for (Matchs m : l) {
			if(m.getButEquipeIn()>0 && m.getButEquipeOut()>0) {
				i+=1;
			}
			if(m.getEquipeInId().equals(name)) {
				nombreMatchsJouerDomicile += 1;
				System.out.println(m.toString()+" == > Match domicile");
				nombreButMarqueADomicile += m.getButEquipeIn();
				nombreButEncaisseADom += m.getButEquipeOut();
				if(m.getButEquipeIn()>0 && m.getButEquipeOut()>0) {
					
					nombreMatchsDeuxEquipeMarqueADom += 1;
				}
				if(m.getButEquipeIn()>0) {
					nombreMatchMarqueADomicile +=1;
				}
				if(m.getButEquipeOut()>0) {
					nombreMatchEncaisseADom +=1;
				}
				
			}else if(m.getEquipeOutId().equals(name)) {
				nombreMatchsJouerExterieurs += 1;
				System.out.println(m.toString()+" == > Match exterieur");
				nombreButMarqueAExt += m.getButEquipeOut();
				nombreButEncaisseAExt += m.getButEquipeIn();
				if(m.getButEquipeIn()>0 && m.getButEquipeOut()>0) {
					
					nombreMatchsDeuxEquipeMarqueExt += 1;
				}
				if(m.getButEquipeIn()>0) {
					nombreMatchEncaisseAExt +=1;
				}
				if(m.getButEquipeOut()>0) {
					nombreMatchMarqueAExt +=1;
				}
			}
		}
		Equipe e=Equipe.builder()
				.name(name)
				.nombreButEncaisseADom(nombreButEncaisseADom)
				.nombreButEncaisseAExt(nombreButEncaisseAExt)
				.nombreButMarqueADomicile(nombreButMarqueADomicile)
				.nombreButMarqueAExt(nombreButMarqueAExt)
				.nombreMatchsDeuxEquipeMarqueADom(nombreMatchsDeuxEquipeMarqueADom)
				.nombreMatchsDeuxEquipeMarqueExt(nombreMatchsDeuxEquipeMarqueExt)
				.nombreMatchsJouer(nombreMatchsJouer)
				.nombreMatchsJouerDomicile(nombreMatchsJouerDomicile)
				.nombreMatchsJouerExterieurs(nombreMatchsJouerExterieurs)
				.nombreMatchEncaisseADom(nombreMatchEncaisseADom)
				.nombreMatchEncaisseAExt(nombreMatchEncaisseAExt)
				.nombreMatchMarqueADomicile(nombreMatchMarqueADomicile)
				.nombreMatchMarqueAExt(nombreMatchMarqueAExt)
				.build();
	
		return e;
		
	}

	@Override
	public List<String> getAllEquipe() {
		List<String> listeEquipe=abstractRepository.findEquipe();
		return listeEquipe;
	}
	
	@Override
	public List<String> getAllLeague() {
		// TODO Auto-generated method stub
		return abstractRepository.findLeague();
	}

	@Override
	public List<String> getEquipesByLeague(String league) {
		// TODO Auto-generated method stub
		return abstractRepository.findEquipesByLeague(league);
	}

}
