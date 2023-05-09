package org.vm93.registroelettronico.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.registroelettronico.model.Corso;
import org.vm93.registroelettronico.model.Docente;
import org.vm93.registroelettronico.model.Messaggio;
import org.vm93.registroelettronico.service.CorsoService;
import org.vm93.registroelettronico.service.DocenteService;

@Configuration
public class MessaggioConfig {
	@Autowired DocenteService docServ;
	@Autowired CorsoService corsoServ;
	
	@Bean
	@Scope("prototype")
	public Messaggio fakeMessaggio() {
		Messaggio m = new Messaggio();
		Corso c = corsoServ.getRandom();
		Docente d = c.getDocente(); 
		m.setCorso(c);
		m.setDocente(d);
		m.setMsg("Questo è un messaggio di prova rilasciato solo ed esclusivamente a scopo di test");
		return m;
	}
}
