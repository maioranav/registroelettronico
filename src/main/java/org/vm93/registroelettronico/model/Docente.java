package org.vm93.registroelettronico.model;

import java.util.List;

import org.vm93.registroelettronico.auth.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("Docente")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@JsonIgnoreProperties({"password", "roles"})
public class Docente extends User {
	
	@JsonIgnore
	@ManyToMany(mappedBy = "docente")
	private List<Corso> corsi;

}
