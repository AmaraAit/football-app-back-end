package com.foot.footballmatchsservice.entities;

import java.util.Comparator;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@ToString
public class Matchs extends MatchAbstract{
	
	
	private int butEquipeIn;
	private int butEquipeOut;
	
	public Matchs(String d, String championnat, String text, String text2, int bEI, int bEO) {
		super(d,championnat,text,text2);
		this.butEquipeIn = bEI;
		this.butEquipeOut = bEO;
	}

	
	
	@Override
	public String toString() {
		
		return super.toString();
	}
	public static Comparator<Matchs> ComparatorDate = new Comparator<Matchs>() {
		@Override
		public int compare(Matchs o1, Matchs o2) {
			// TODO Auto-generated method stub
			return o1.getDate().compareTo(o2.getDate());
		}
		 };
	
	
	
	
	

}
