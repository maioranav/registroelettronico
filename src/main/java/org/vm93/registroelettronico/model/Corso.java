package org.vm93.registroelettronico.model;

import java.util.List;

import jakarta.persistence.Entity;
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
@Table(name = "corsi")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class Corso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @ManyToOne
	private Plesso plesso;
	
    @ManyToOne
	private Docente docente;
    
    @ManyToMany(mappedBy = "corso")
	private List<Lezione> lezioni;
	
    @ManyToMany
	private List<Studente> studenti;
	
}
