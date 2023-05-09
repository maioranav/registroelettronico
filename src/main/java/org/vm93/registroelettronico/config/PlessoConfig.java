package org.vm93.registroelettronico.config;

import java.util.Locale;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.vm93.registroelettronico.model.Plesso;

import com.github.javafaker.Faker;

@Configuration
public class PlessoConfig {

	
	@Bean
	@Scope("prototype")
	public Plesso fakePlesso() {
		Faker fake = Faker.instance(new Locale("it-IT"));
		Plesso p = new Plesso();
		p.setName(fake.harryPotter().character());
		return p;
	}
	
}
