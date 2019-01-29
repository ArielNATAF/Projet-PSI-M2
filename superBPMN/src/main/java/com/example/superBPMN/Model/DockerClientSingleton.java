package com.example.superBPMN.Model;

import com.github.dockerjava.api.DockerClient;
import com.github.dockerjava.core.DefaultDockerClientConfig;
import com.github.dockerjava.core.DockerClientBuilder;

/**
 * Créé par Ariel NATAF, le 27/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public class DockerClientSingleton {

	public DockerClient dockerClient;

	private DockerClientSingleton( )
	{
		DefaultDockerClientConfig.Builder config = DefaultDockerClientConfig.createDefaultConfigBuilder();
		dockerClient = DockerClientBuilder
			.getInstance(config)
			.build();
	}

	private static class SingletonHolder
	{
		private final static DockerClientSingleton instance = new DockerClientSingleton();
	}

	public static DockerClientSingleton getInstance()
	{
		return SingletonHolder.instance;
	}


}
