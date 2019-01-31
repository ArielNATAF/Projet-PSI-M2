package com.example.superBPMN.Model;

/**
 * Créé par Ariel NATAF, le 29/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public enum DockerEnum {
	VERIF(new DockerImage("testcopycontainer",
			"ressources/docker verif extension/Dockerfile",
			"python:git", "./result.txt",
			"ressources/docker verif extension/result.txt")),
	ALPINE(new DockerImage("alpine",
			"ressources/docker alpine/Dockerfile",
			"","",""));

	private DockerImage dockerImage;

	DockerEnum(DockerImage dockerImage){
		this.dockerImage = dockerImage;
	}

	public DockerImage dockerImage(){
		return dockerImage;
	}
}
