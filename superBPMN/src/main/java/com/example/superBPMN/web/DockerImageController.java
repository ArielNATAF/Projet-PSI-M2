package com.example.superBPMN.web;

import com.example.superBPMN.Model.DockerImage;
import com.example.superBPMN.service.DockerImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Créé par Ariel NATAF, le 03/02/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/dockerImages")
public class DockerImageController {

	final
	DockerImageService dockerImageService;

	@Autowired
	public DockerImageController(DockerImageService dockerImageService) {
		this.dockerImageService = dockerImageService;
	}

	@GetMapping
	public ResponseEntity<?> getAll() {
		List<DockerImage> result = dockerImageService.findAll();
		return new ResponseEntity(result, HttpStatus.OK);
	}

	@DeleteMapping
	public void deleteDockerImagebyId(@RequestParam("id") String id) {
		dockerImageService.deleteDockerImagebyId(id);
	}

//	@DeleteMapping
	public void deleteDockerImagebyImageName(@RequestParam("imageName") String imageName) {
		dockerImageService.deleteDockerImagebyImageName(imageName);
	}

	@PostMapping()
	public ResponseEntity<?> addorUpdateDockerImage(@RequestBody DockerImage
															dockerImage) {
		dockerImageService.saveOrUpdateDockerImage(dockerImage);
		return new ResponseEntity("DockerImage added succcessfully", HttpStatus.OK);

	}
}