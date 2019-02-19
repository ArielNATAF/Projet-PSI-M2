package com.example.superBPMN.service;

import com.example.superBPMN.Model.ResultVerif;
import com.example.superBPMN.repositories.ResultVerifRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 17/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@Service
public class ResultVerifServiceImpl implements ResultVerifService {

	@Autowired
	ResultVerifRepository resultVerifRepository;

	@Override
	public List<ResultVerif> findAll() {
		return (List<ResultVerif>) resultVerifRepository.findAll();
	}

	@Override
	public void deleteResultVerifbyId(Long Id_result) {
		resultVerifRepository.deleteById(Id_result);
	}

	@Override
	public void saveOrUpdateResultVerif(ResultVerif resultVerif) {
		resultVerifRepository.save(resultVerif);
	}
}
