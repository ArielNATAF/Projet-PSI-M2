package com.example.superBPMN.superBPMN.web;

import com.example.superBPMN.superBPMN.Model.DockerChoice;
import com.example.superBPMN.superBPMN.docker.DockerAction;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;


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

			DockerAction da = new DockerAction();

			List<String> listImages = da.listImages(dockerClient);
			return listImages.toString();
		}
		catch (Exception e){e.getMessage();
		return e.getMessage();}
	}

	@RequestMapping("/buildImage")
	public String buildImage(){
		try {
			DefaultDockerClientConfig.Builder config
					= DefaultDockerClientConfig.createDefaultConfigBuilder();
			DockerClient dockerClient = DockerClientBuilder
					.getInstance(config)
					.build();

			String imageId = dockerClient.buildImageCmd()
					.withDockerfile(new File("ressources/docker alpine/Dockerfile"))
					.withPull(true)
					.withNoCache(true)
					.withTag("alpine:git")
					.exec(new BuildImageResultCallback())
					.awaitImageId();

			return imageId;
		}
		catch (Exception e){e.getMessage();
			return e.getMessage();}
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}

	@Controller
	public static class DockerChoiceController {

		@GetMapping("/dockerChoice")
		public String dockerChoiceForm(Model model) {
			model.addAttribute("dockerChoice", new DockerChoice());
			return "dockerChoice";
		}

		@PostMapping("/dockerChoice")
		public String dockerChoiceSubmit(@ModelAttribute DockerChoice dockerChoice) {
			return "result";
		}

	}
}
