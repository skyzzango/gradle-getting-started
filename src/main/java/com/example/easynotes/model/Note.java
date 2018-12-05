package com.example.easynotes.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

@Entity // class as a persistent Java class.
@Table(name = "notes") // details of the table that this entity will be mapped to.
@EntityListeners(AuditingEntityListener.class) // We have already done this in our Note model with the annotation
@JsonIgnoreProperties(value = {"createdAt", "updatedAt"}, allowGetters = true) // Jackson annotation.
@Getter // All field Getter
@Setter // All field Setter
public class Note implements Serializable {

	@Id // primary key.
	@GeneratedValue(strategy = GenerationType.IDENTITY) // primary key generation strategy.(Auto Increment field)
	private Long id;

	@NotBlank // field is not null or empty.
	private String title;

	@NotBlank
	private String content;

	@Column(nullable = false, updatable = false) // properties of the column
	@Temporal(TemporalType.TIMESTAMP) // java.util.Date and java.util.Calendar classes.
	@CreatedDate
	private Date createdAt;

	@Column(nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@LastModifiedDate
	private Date updatedAt;

	// Getters and Setters ... (Omitted for brevity)
}
