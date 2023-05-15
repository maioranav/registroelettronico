package org.vm93.registroelettronico.config;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.registroelettronico.model.Lezione;
import org.vm93.registroelettronico.model.Studente;
import org.vm93.registroelettronico.service.CorsoService;
import org.vm93.registroelettronico.service.StudenteService;

import com.github.javafaker.Faker;

@Configuration
public class LezioneConfig {
	
	@Autowired CorsoService corsoServ;
	@Autowired StudenteService studServ;

	@Bean
	@Scope("prototype")
	public Lezione lezioneFake() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Lezione l = new Lezione();
		l.setCorso(corsoServ.getRandom());
		Date d = fake.date().between(new Date(123, 1, 5), new Date(124, 5, 5));
		LocalDate d2 = new java.sql.Date(d.getTime()).toLocalDate();
		l.setData(d2);
		List<Studente> list = new ArrayList<>();
		for (int i = 0; i <5; i++) {			
			list.add(studServ.getRandom());
		}
		l.setPresenze(list);
		return l;
	}
	
}
