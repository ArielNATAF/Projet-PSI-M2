package com.example.superBPMN.Model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Créé par Ariel NATAF, le 30/01/2019.
 * Master 2 Classique, MIAGE Nanterre
 */

@Data
@Document
public class ResultVerif implements Serializable {
	private @javax.persistence.Id
	@GeneratedValue
	Long Id_result;
	private String time;
	private String imageId;
	private String parameter;
	private String comment;

	private ResultVerif(){}

	public ResultVerif(String time, String imageId, String parameter, String comment) {
		this.time = time;
		this.imageId = imageId;
		this.comment = comment;
		this.parameter=parameter;
	}
}
