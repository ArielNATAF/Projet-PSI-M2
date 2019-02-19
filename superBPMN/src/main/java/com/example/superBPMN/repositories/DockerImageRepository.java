package com.example.superBPMN.repositories;

import com.example.superBPMN.Model.DockerImage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Créé par Ariel NATAF, le 03/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

public interface DockerImageRepository extends MongoRepository<DockerImage,String> {
}
