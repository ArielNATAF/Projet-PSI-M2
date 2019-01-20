package com.example.superBPMN.superBPMN.docker;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 20/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public class dockerAction {

	public List<String> listImage(){

		List<Container> containers = dockerClient.listContainersCmd().exec();
	}
}
