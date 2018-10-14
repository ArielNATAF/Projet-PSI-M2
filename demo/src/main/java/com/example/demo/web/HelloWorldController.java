package com.example.demo.web;

/**
 * Créé par Ariel NATAF, le 13/10/2018.
 * Master 2 Classique, MIAGE Nanterre
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
public class HelloWorldController {

	@RequestMapping("/")
	public String home() {
		TestsBPMN tests = new TestsBPMN();

		return tests.checkCaps("ressources/diag.bpmn");
	}

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}

}