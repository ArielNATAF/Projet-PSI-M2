package com.example.superBPMN.Model;

import lombok.Data;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Créé par Ariel NATAF, le 30/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@Data
@Entity
public class Result {
	private @javax.persistence.Id
	@GeneratedValue
	Long Id_result;
	private String imageId;
	private String comment;

	private Result(){}

	public Result(String imageId, String comment) {
		this.imageId = imageId;
		this.comment = comment;
	}
}
