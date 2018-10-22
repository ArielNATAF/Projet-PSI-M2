package com.example.demo.web;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * Créé par Ariel NATAF, le 14/10/2018.
 * Master 2 Classique, MIAGE Nanterre
 */
public class TestsBPMN {

	public String checkCaps(String path){

		String fileName = path;
		List<String> list = new ArrayList<>();

		//read file into stream, try-with-resources
		try (Stream<String> stream = Files.lines(Paths.get(fileName))) {

			list = stream
					.filter(line -> line.matches(".*name=\"\\p{javaLowerCase}.*"))
					.collect(Collectors.toList());

		} catch (IOException e) {
			e.printStackTrace();
		}

		list.forEach(System.out::println);
		if(list.size()>0)
		{
			return "Un ou des éléments ont un nom ne commencant pas par une majuscule.<br>" + list;
		}
		return "Tout les noms d\'éléments commencent par une majuscule.";
	}

}
