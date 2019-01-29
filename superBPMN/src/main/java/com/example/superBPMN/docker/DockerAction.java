package com.example.superBPMN.docker;

import com.example.superBPMN.Model.DockerImage;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
				.withDockerfile(new File(dockerfilePath))
				.withPull(true)
				.withNoCache(true)
				.withTag(tag)
				.exec(new BuildImageResultCallback())
				.awaitImageId();

		return imageId;
	}


	public String createContainerAction(DockerClient dockerClient, String createContainerCmd){

		return createContainerCmd;
	}

	public List<String> listContainers(DockerClient dockerClient){

		List<String> list = new ArrayList<>();
		List<Container> containers = dockerClient.listContainersCmd().exec();
		for (Container ctn : containers){
			list.add(ctn.toString());
		}
		return list;
	}

	public String copyFileFromContainer(DockerClient dockerClient, DockerImage dockerImage){

		// Data
		String imageId = dockerImage.getImageId();
		String containerName = dockerImage.getImageName() + "_Container";
		String containerFile = dockerImage.getContainerFile();
		String hostFile = "/Users/arielnataf/Desktop/M2/PSI/Projet PSI M2/superBPMN/ressources/docker verif extension/result.txt";

		// Docker client
		// Create container
		try (CreateContainerCmd createContainer = dockerClient
				.createContainerCmd(imageId).withName(containerName)) {
			createContainer.withTty(true);
			createContainer.exec();
		}

		// Start container
		dockerClient.startContainerCmd(containerName).exec();

		// Copy file from container
		try (TarArchiveInputStream tarStream = new TarArchiveInputStream(
				dockerClient.copyArchiveFromContainerCmd(containerName,
						containerFile).exec())) {
			unTar(tarStream, new File(hostFile));
		}catch (IOException e){e.getLocalizedMessage();}

		// Stop container ?
		//dockerClient.killContainerCmd(containerName).exec();
		//dockerClient.stopContainerCmd(containerName).exec();

		// Remove container
		dockerClient.removeContainerCmd(containerName).exec();

		return null;
	}

	public static void unTar(TarArchiveInputStream tis, File destFile)
			throws IOException {
		TarArchiveEntry tarEntry = null;
		while ((tarEntry = tis.getNextTarEntry()) != null) {
			if (tarEntry.isDirectory()) {
				if (!destFile.exists()) {
					destFile.mkdirs();
				}
			} else {
				FileOutputStream fos = new FileOutputStream(destFile);
				IOUtils.copy(tis, fos);
				fos.close();
			}
		}
		tis.close();
	}


}
