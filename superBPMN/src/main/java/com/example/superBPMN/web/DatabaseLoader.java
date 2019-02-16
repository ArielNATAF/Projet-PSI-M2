package com.example.superBPMN.web;



import com.example.superBPMN.Model.*;
// import com.example.superBPMN.repositories.DockerRepository;
import com.example.superBPMN.repositories.ResultRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Créé par Ariel NATAF, le 30/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */


/*

@Component
public class DatabaseLoader implements CommandLineRunner {
	private final DockerRepository repository;
	private final ResultRepository resultrepository;

	@Autowired
	public DatabaseLoader(DockerRepository repository, ResultRepository resultrepository){
		this.repository = repository;
		this.resultrepository =resultrepository;
	}

	@Override
	public void run	(String... strings) throws Exception{
		this.repository.save(new DockerImage("testcopycontainer",
				"ressources/docker verif extension/Dockerfile",
				"python:git", "./result.txt",
				"ressources/docker verif extension/result.txt"));
		this.repository.save(new DockerImage("alpine",
				"ressources/docker alpine/Dockerfile",
				"","",""));
		this.resultrepository.save(new Result("111111","test"));
	}
}

*/