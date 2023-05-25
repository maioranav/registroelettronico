package org.vm93.registroelettronico.model;

import java.util.List;

import org.springframework.security.core.Transient;
import org.vm93.registroelettronico.auth.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Transient
@DiscriminatorValue("Studente")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@JsonIgnoreProperties(value={"password", "roles", "lezioni"}, allowSetters= true)
public class Studente extends User {

	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.DETACH)
	private List<Corso> corsi;
	
	@ManyToMany(mappedBy = "presenze",fetch = FetchType.LAZY)
	private List<Lezione> lezioni;
}
