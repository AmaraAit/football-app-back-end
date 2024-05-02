package com.foot.footballmatchsservice.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Equipe {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	private int nombreMatchsJouer;
	private int nombreMatchsJouerDomicile;
	private int nombreMatchsJouerExterieurs;
	private int nombreMatchsDeuxEquipeMarqueADom;
	private int nombreMatchsDeuxEquipeMarqueExt;
    private int nombreButMarqueADomicile;
	private int nombreButMarqueAExt;
	private int nombreButEncaisseADom;
	private int nombreButEncaisseAExt;
	private int nombreMatchMarqueADomicile;
	private int nombreMatchMarqueAExt;
	private int nombreMatchEncaisseADom;
	private int nombreMatchEncaisseAExt;
	
}
