package org.vm93.registroelettronico.model;

import java.util.List;

import org.springframework.security.core.Transient;
import org.vm93.registroelettronico.auth.entity.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@DiscriminatorValue("Docente")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
@JsonIgnoreProperties(value={"password", "roles"}, allowSetters= true)
public class Docente extends User {
	
	@ManyToMany(mappedBy = "docente", fetch = FetchType.LAZY)
	private List<Corso> corsi;

}
