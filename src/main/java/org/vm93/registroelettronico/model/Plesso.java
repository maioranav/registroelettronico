package org.vm93.registroelettronico.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

public class Plesso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
    
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<Corso> corsi;
	
}
