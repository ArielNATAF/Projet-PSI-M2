package com.example.superBPMN.repositories;

import com.example.superBPMN.Model.ResultVerif;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.repository.CrudRepository;

/**
 * Créé par Ariel NATAF, le 30/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public interface ResultVerifRepository extends MongoRepository<ResultVerif,Long> {
}
