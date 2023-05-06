package org.vm93.registroelettronico.model;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "messaggi")
@NoArgsConstructor
@AllArgsConstructor
@Data @Builder
public class Messaggio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    
    @Column(nullable = false)
	private String msg;
    
    @OneToOne
	private Docente docente;
    
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Corso corso;
}
