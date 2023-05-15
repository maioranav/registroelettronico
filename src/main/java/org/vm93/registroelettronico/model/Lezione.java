package org.vm93.registroelettronico.model;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "lezioni")
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties("lezioni")
@Data @Builder
public class Lezione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable=false)
	private LocalDate data;
    
    @ManyToMany
	private List<Studente> presenze;
	
    
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
	private Corso corso;
	
}
