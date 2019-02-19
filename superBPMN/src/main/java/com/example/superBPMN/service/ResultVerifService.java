package com.example.superBPMN.service;

import com.example.superBPMN.Model.DockerImage;
import com.example.superBPMN.Model.ResultVerif;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 17/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */
public interface ResultVerifService {
	List<ResultVerif> findAll();

	void deleteResultVerifbyId(Long Id_result);

	void saveOrUpdateResultVerif(ResultVerif resultVerif);

}
