package com.example.superBPMN.Model;

/**
 * Créé par Ariel NATAF, le 29/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public class DockerImage {
	private String imageId;
	private String imageName;
	private String containerFile;

	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	private String dockerfilePath;

	public DockerImage(String imageName, String dockerfilePath,  String imageId) {
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

	public String getImageId() {
		return imageId;
	}

	public void setImageId(String imageId) {
		this.imageId = imageId;
	}

	public String getContainerFile() {
		return containerFile;
	}

	public void setContainerFile(String containerFile) {
		this.containerFile = containerFile;
	}

	public String getDockerfilePath() {
		return dockerfilePath;
	}

	public void setDockerfilePath(String dockerfilePath) {
		this.dockerfilePath = dockerfilePath;
	}
}
