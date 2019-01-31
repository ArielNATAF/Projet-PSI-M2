package com.example.superBPMN.web;

import com.example.superBPMN.Model.ContainerCommand;
import com.example.superBPMN.Model.DockerClientSingleton;
import com.example.superBPMN.Model.DockerEnum;
import com.example.superBPMN.Model.DockerImage;
import com.example.superBPMN.docker.DockerAction;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javafx.scene.image.Image;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.util.List;


/**
 * Créé par Ariel NATAF, le 23/11/2018.
 * Master 2 Classique, MIAGE Nanterre
 */

@RestController
public class SuperBPMNController {

	@RequestMapping("/home")
	public String home(){
		return "Super BPMN";
	}

	@RequestMapping("/list")
	public String list(){
		try {
			DockerClient dockerClient = DockerClientSingleton.getInstance().dockerClient;

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
			DockerImage dockerImage = new DockerImage("verif",
					"ressources/docker verif extension/Dockerfile");
			DockerClient dockerClient = DockerClientSingleton.getInstance().dockerClient;
			DockerAction da = new DockerAction();
			String imageID = da.buildImageFromDockerFile(dockerClient, dockerImage);


			dockerImage.setImageId(imageID);
			return dockerImage.getImageId();
		}
		catch (Exception e){e.getMessage();
			return e.getMessage();}
	}

	@RequestMapping("/createContainer")
	public String createContainer() {
		DockerClient dockerClient = DockerClientSingleton.getInstance().dockerClient;

		ContainerCommand cmd = new ContainerCommand();
		cmd.setCmd("-bind_ip_all");
		cmd.setName("mongo");
		cmd.setHostName("baeldung");
		cmd.setEnv("MONGO_LATEST_VERSION=3.6");
		cmd.setPortBindings("9999:27017");
		cmd.setWithBinds("/Users/baeldung/mongo/data/db:/data/db");

		GsonBuilder builder = new GsonBuilder();
		Gson gson = builder.create();

		DockerAction da = new DockerAction();

		return da.createContainerAction(dockerClient, gson.toJson(cmd));
	}


	@RequestMapping("/copyFromContainer")
	public String copyFromContainer(){
		DockerClient dockerClient = DockerClientSingleton.getInstance().dockerClient;

		DockerImage dockerImage = DockerEnum.VERIF.dockerImage();
		DockerAction da =new DockerAction();

		return da.copyFileFromContainer(dockerClient, dockerImage);
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}

}
