package com.foot.footballmatchsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Entity
@Data
@NoArgsConstructor

public class MatchsNext extends MatchAbstract{

	

	public MatchsNext(String d, String championnat, String text, String text2) {
		super(d, championnat,  text, text2);
	}

	
	

}
