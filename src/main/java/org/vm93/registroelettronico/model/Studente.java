package org.vm93.registroelettronico.model;

import java.util.List;

import org.vm93.registroelettronico.auth.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@JsonIgnoreProperties({"password", "roles"})
public class Studente extends User {

	@ManyToMany(mappedBy = "studenti" ,fetch = FetchType.LAZY)
	private List<Corso> corsi;
	
	@ManyToMany(mappedBy = "presenze",fetch = FetchType.LAZY)
	private List<Lezione> lezioni;
}
