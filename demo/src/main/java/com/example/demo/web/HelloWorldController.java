package com.example.demo.web;

/**
 * Créé par Ariel NATAF, le 13/10/2018.
 * Master 2 Classique, MIAGE Nanterre
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorldController {

	@RequestMapping("/hello")
	public String sayHello() {
		return "Hello  World !";
	}

}