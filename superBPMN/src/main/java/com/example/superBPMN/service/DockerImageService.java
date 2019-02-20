package com.example.superBPMN.service;

import com.example.superBPMN.Model.DockerImage;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 31/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

public interface DockerImageService {
	List<DockerImage> findAll();

	void deleteDockerImagebyId(String id);

	void saveOrUpdateDockerImage(DockerImage dockerImage);

}
