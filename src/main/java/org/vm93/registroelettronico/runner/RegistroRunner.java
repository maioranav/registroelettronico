package org.vm93.registroelettronico.runner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import org.vm93.registroelettronico.model.Plesso;
import org.vm93.registroelettronico.repo.PlessoRepository;

@Component
public class RegistroRunner implements ApplicationRunner {

	@Autowired PlessoRepository plessoRepo;

	@Override 
	public void run(ApplicationArguments args) throws Exception {
		if (!plessoRepo.existsById(1l)) {
			Plesso p = new Plesso();
			p.setId(1l);
			p.setName("Polo Universitario Principale");
			plessoRepo.save(p);
		}
		}
}