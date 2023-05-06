package org.vm93.registroelettronico.model;

import java.util.List;

import org.vm93.registroelettronico.auth.entity.User;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Studente")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class Studente extends User {

	@ManyToMany(mappedBy = "studenti")
	private List<Corso> corsi;
	
	@ManyToMany(mappedBy = "studenti")
	private List<Lezione> lezioni;
}
