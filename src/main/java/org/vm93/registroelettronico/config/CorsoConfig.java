package org.vm93.registroelettronico.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.service.DocenteService;
import org.vm93.registroelettronico.service.PlessoService;
import org.vm93.registroelettronico.service.StudenteService;

@Configuration
public class CorsoConfig {
	
	@Autowired DocenteService docServ;
	@Autowired StudenteService studServ;
	@Autowired PlessoService plessoServ;
	
	@Bean
	@Scope("prototype")
	public Corso fakeCorso() {
		Corso c = new Corso();
		c.setPlesso(plessoServ.getRandom());
		c.setDocente(docServ.getRandom());
		List<Studente> studenti = new ArrayList<>();
		for (int i = 0; i < 10; i++) {			
			studenti.add(studServ.getRandom());
		}
		c.setStudenti(studenti);
		return c;
	}

}
