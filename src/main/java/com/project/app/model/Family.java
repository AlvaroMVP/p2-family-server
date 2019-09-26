package com.project.app.model;

import java.time.LocalDate;
import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "family")
public class Family {
	
	 /**
	 * ID.
	 */
	  @Id
	  private String id;
	  /**
	 * fullname.
	 */
	  @NotEmpty(message = "'fullname cannot be empty!")
	 private String fullname;
	  /**
	 * gender.
	 */
	  @NotEmpty(message = "'gender' cannot be empty!")
	 private String gender;
	  /**
	 * birthdate.
	 */
	  @NotNull
	  @JsonFormat(pattern = "yyyy-MM-dd", shape = Shape.STRING)
	 private Date birthdate;
	  /**
	 * typeID.
	 */
	  @NotEmpty(message = "'typeID' cannot be empty!")
	 private String typeID;
	  /**
	 * numberID.
	 */
	  @NotEmpty(message = "'numberDocument' cannot be empty!")
	  @Size(min = 8, max = 8,message = "'document number can only have 8 characters")
	 private String numberDocument;
	  /**
	 * createAt.
	 */
	  @NotNull
	  @DateTimeFormat(pattern = "yyyy-MM-dd")
	 private Date createdAt = new Date();
	  /**
	 * idFamily.
	 */
	  @NotEmpty(message = "'idFamily' cannot be empty!")
	 private String idFamily;
}
