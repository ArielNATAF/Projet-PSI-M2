package com.example.superBPMN.superBPMN.docker;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.BuildImageResultCallback;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Créé par Ariel NATAF, le 20/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public class DockerAction {

	public List<String> listImages(DockerClient dockerClient){

		List<String> list = new ArrayList<>();
		List<Image> images = dockerClient.listImagesCmd().exec();
		for (Image img : images){
			list.add(img.toString());
		}
		return list;
	}

	public String buildImageFromDockerFile(DockerClient dockerClient, String dockerfilePath, String tag){

		String imageId = dockerClient.buildImageCmd()
				.withDockerfile(new File("dockerfilePath"))
				.withPull(true)
				.withNoCache(true)
				.withTag("tag")
				.exec(new BuildImageResultCallback())
				.awaitImageId();

		return imageId;
	}

	public List<String> listContainers(DockerClient dockerClient){

		List<String> list = new ArrayList<>();
		List<Container> containers = dockerClient.listContainersCmd().exec();
		for (Container ctn : containers){
			list.add(ctn.toString());
		}
		return list;
	}
}
