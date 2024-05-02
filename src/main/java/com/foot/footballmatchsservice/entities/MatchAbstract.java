package com.foot.footballmatchsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public abstract class MatchAbstract {
	
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long matchId;
	private String date;
	private String league;
	private String equipeInId;
	private String equipeOutId;
	
	public MatchAbstract() {
		
	}
	public MatchAbstract(String date, String league, String equipeInId, String equipeOutId) {
		
		this.date = date;
		this.league = league;
		this.equipeInId = equipeInId;
		this.equipeOutId = equipeOutId;
	}

}
