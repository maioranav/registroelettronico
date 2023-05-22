package org.vm93.registroelettronico.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "corsi")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@JsonIgnoreProperties(value = {"docente"}, allowSetters = true)
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    private String name;
    
    @ManyToOne
	private Plesso plesso;
	
    @ManyToOne
	private Docente docente;
    
    @ManyToMany(mappedBy = "corso", fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JsonIgnore
	private List<Lezione> lezioni;
	
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
	private List<Studente> studenti;
    
    @OneToMany(mappedBy= "corso", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Messaggio> messaggi;
	
}
