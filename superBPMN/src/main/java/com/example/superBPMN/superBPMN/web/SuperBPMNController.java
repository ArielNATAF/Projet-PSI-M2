package com.example.superBPMN.superBPMN.web;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 23/11/2018.
 * Master 2 Classique, MIAGE Nanterre
 */
@RestController
public class SuperBPMNController {

	@RequestMapping("/")
	public String home(){
		return "pouet";
	}

	@RequestMapping("/list")
	public String list(){
		try {
			DockerClient dockerClient = DockerClientBuilder.getInstance().build();

			List<Container> containers = dockerClient.listContainersCmd()
					.withShowSize(true)
					.withShowAll(true)
					.withStatusFilter("exited").exec();

			return "a";
		}
		catch (Exception e){e.getMessage();
		return e.getMessage();}
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}
}
