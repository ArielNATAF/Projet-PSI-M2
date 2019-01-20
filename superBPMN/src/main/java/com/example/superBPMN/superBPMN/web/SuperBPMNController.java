package com.example.superBPMN.superBPMN.web;

import com.amihaiemil.docker.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.io.File;
import java.util.Iterator;

/**
 * Créé par Ariel NATAF, le 23/11/2018.
 * Master 2 Classique, MIAGE Nanterre
 */

@RestController
public class SuperBPMNController {

	@RequestMapping("/")
	public String home(){
		return "Super BPMN";
	}

	@RequestMapping("/list")
	public String list(){
		try {
			DefaultDockerClientConfig.Builder config
					= DefaultDockerClientConfig.createDefaultConfigBuilder();
			DockerClient dockerClient = DockerClientBuilder
					.getInstance(config)
					.build();

			String imageId = dockerClient.buildImageCmd()
					.withDockerfile(new File("path/to/Dockerfile"))
					.withPull(true)
					.withNoCache(true)
					.withTag("alpine:git")
					.exec(new BuildImageResultCallback())
					.awaitImageId();

			List<Container> containers = dockerClient.listContainersCmd().exec();
			return containers.toString();
		}
		catch (Exception e){e.getMessage();
		return e.getMessage();}
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}
}
