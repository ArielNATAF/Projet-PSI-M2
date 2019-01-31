package com.example.superBPMN.Model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Créé par Ariel NATAF, le 29/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@Data
@Entity
public class DockerImage {

	private @Id @GeneratedValue Long Id;
	private String imageName;
	private String dockerfilePath;
	private String imageId;
	private String containerFile;
	private String hostFile;

	private DockerImage(){}

	public DockerImage(String imageName, String dockerfilePath, String imageId,
					   String containerFile, String hostFile) {
		this.imageName = imageName;
		this.imageId = imageId;
		this.dockerfilePath = dockerfilePath;
		this.containerFile = containerFile;
		this.hostFile = hostFile;
	}

	public DockerImage(String imageName, String dockerfilePath, String imageId, String containerFile) {
		this.imageName = imageName;
		this.imageId = imageId;
		this.dockerfilePath = dockerfilePath;
		this.containerFile = containerFile;
	}

	public DockerImage(String imageName, String dockerfilePath, String imageId) {
		this.imageName = imageName;
		this.imageId = imageId;
		this.dockerfilePath = dockerfilePath;
	}

	public DockerImage(String imageName, String dockerfilePath) {
		this.imageName = imageName;
		this.dockerfilePath = dockerfilePath;
	}

	public DockerImage(String imageName ) {
		this.imageName = imageName;
	}
}
