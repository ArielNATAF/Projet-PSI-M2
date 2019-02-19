package com.example.superBPMN.web;

import com.example.superBPMN.Model.ResultVerif;
import com.example.superBPMN.service.ResultVerifService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 17/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/resultVerifs")
public class ResultVerifController {


	final
	ResultVerifService resultVerifService;

	@Autowired
	public ResultVerifController(ResultVerifService resultVerifController) {
		this.resultVerifService = resultVerifController;
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<ResultVerif> result = (List<ResultVerif>) resultVerifService.findAll();
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@DeleteMapping
	public void deleteResultVerifbyId(@RequestParam("Id_result") Long Id_result) {
		resultVerifService.deleteResultVerifbyId(Id_result);
	}

	@PostMapping()
	public ResponseEntity<?> addorUpdateResultVerif(@RequestBody ResultVerif
															resultVerif) {
		resultVerifService.saveOrUpdateResultVerif(resultVerif);
		return new ResponseEntity("ResultVerif added succcessfully", HttpStatus.OK);

	}
}
