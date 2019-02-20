package com.example.superBPMN.service;

import com.example.superBPMN.Model.DockerImage;
import com.example.superBPMN.repositories.DockerImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 03/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@Service
public class DockerImageServiceImpl implements DockerImageService {

	@Autowired
	DockerImageRepository dockerRepository;

	@Override
	public List<DockerImage> findAll() {
		return (List<DockerImage>) dockerRepository.findAll();
	}

	@Override
	public void deleteDockerImagebyId(String id) {
		dockerRepository.deleteById(id);
	}

	@Override
	public void saveOrUpdateDockerImage(DockerImage dockerImage) {
		dockerRepository.save(dockerImage);
	}
}
