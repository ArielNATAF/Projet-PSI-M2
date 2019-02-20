package com.example.superBPMN.docker;

import com.example.superBPMN.Model.DockerImage;
import com.example.superBPMN.Model.ResultVerif;
import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.api.command.CreateContainerCmd;
import com.github.dockerjava.api.command.ExecCreateCmdResponse;
import com.github.dockerjava.api.model.Container;
import com.github.dockerjava.api.model.Image;
import com.github.dockerjava.core.command.BuildImageResultCallback;
import com.github.dockerjava.core.command.ExecStartResultCallback;
import org.apache.commons.compress.archivers.tar.TarArchiveEntry;
import org.apache.commons.compress.archivers.tar.TarArchiveInputStream;
import org.apache.commons.io.IOUtils;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import java.io.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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

	public String buildImageFromDockerFile(DockerClient dockerClient, DockerImage dockerImage){

		String imageId = dockerClient.buildImageCmd()
				.withDockerfile(new File(dockerImage.getDockerfilePath()))
				.withPull(true)
				.withNoCache(true)
				.exec(new BuildImageResultCallback())
				.awaitImageId();

		return imageId;
	}

	public String copyFileFromContainer(DockerClient dockerClient, DockerImage dockerImage, String filename){

		// Data
		String imageId = dockerImage.getImageId();
		String containerName = dockerImage.getImageName() + "_Container";
		String containerFile = dockerImage.getContainerFile();
		String hostFile = dockerImage.getHostFile();

		// Docker client
		// Create container
		try (CreateContainerCmd createContainer = dockerClient
				.createContainerCmd(imageId)
				.withName(containerName)
				.withCmd(filename)

		) {
			createContainer.withTty(true);
			createContainer.exec();
		}

		// Start container
		dockerClient.startContainerCmd(containerName).exec();

		try {
			// pas particulierement content d'avoir ça. semble résoudre un bug.
			// objectivement cette partie ne devrait servir à rien.
			// MAIS suite à de trop nombreux tatonnements puis
			// à jeu de Mikado de l'enfer, la fonction, inutile, ne fonctionne qu'avec cette requete
			// et aucune fonction pour stopper le container avec de le détruire.

			// POURQUOI ? je vous le demande mais j'ai plus le temps.

			ExecCreateCmdResponse execCreateCmdResponse = dockerClient.execCreateCmd(containerName)
				.withAttachStdout(true)
				.withCmd("echo", "\"toto\">>result.txt")
				.exec();
			dockerClient.execStartCmd(execCreateCmdResponse.getId())
					.exec(new ExecStartResultCallback(System.out, System.err))
					.awaitCompletion();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		// Copy file from container
		try (TarArchiveInputStream tarStream = new TarArchiveInputStream(
				dockerClient.copyArchiveFromContainerCmd(containerName,
						containerFile).exec())) {
			unTar(tarStream, new File(hostFile));
		}catch (IOException e){e.getLocalizedMessage();}

		try {
			BufferedReader br = new BufferedReader(new FileReader(hostFile));
			String line = null;

			StringBuffer resulttext= new StringBuffer();;
			resulttext.append("For file:"+filename+" ; ");
			resulttext.append("result:");
			while ((line = br.readLine()) != null) {
				resulttext.append(" "+line+",");
			}

			// Stop container
			//dockerClient.killContainerCmd(containerName).exec();
			//dockerClient.stopContainerCmd(containerName).exec();

			// Remove container
			dockerClient.removeContainerCmd(containerName).exec();

			Timestamp ts = new Timestamp(System.currentTimeMillis());
			ResultVerif resultVerif = new ResultVerif(String.valueOf(ts),
						imageId,containerFile,resulttext.toString());
			System.out.println(resultVerif.toString());

			postResultVerif(resultVerif);

		} catch (IOException e) {
			e.printStackTrace();
		}
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


	public void postResultVerif(ResultVerif resultVerif) {

		try {
			Client client = Client.create();

			WebResource webResource = client
					.resource("http://localhost:8080/resultVerifs");

			String input = "{"+
					"\"time\":\""+ resultVerif.getTime() +"\""+
					",\"imageId\":\""+resultVerif.getImageId()+"\""+
					",\"parameter\":\"" +resultVerif.getParameter()+"\""+
					",\"comment\":\"" +resultVerif.getComment()+"\"}";

			System.out.println(input);
			ClientResponse response = webResource.type("application/json")
					.post(ClientResponse.class, input);

			if (response.getStatus() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
						+ response.getStatus());
			}

			System.out.println("Output from Server .... \n");
			String output = response.getEntity(String.class);
			System.out.println(output);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
