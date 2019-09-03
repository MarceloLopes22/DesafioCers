package br.com.cers.notification.entity;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "notification", schema = "cers")
public class Notification implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY, generator = "native")
	@GenericGenerator(name = "native", strategy = "native")
	@Column(name = "idnotification", updatable = false, nullable = false)
	private Integer idnotification;

	@NotNull(message = "Title is mandatory")
	@NotBlank(message = "Title is mandatory")
	@NotEmpty(message = "Title is mandatory")
	@Column(name = "title")
	@Size(max = 100)
	private String title;

	@NotNull(message = "Descriptionn is mandatory")
	@NotBlank(message = "Descriptionn is mandatory")
	@NotEmpty(message = "Descriptionn is mandatory")
	@Column(name = "descriptionn")
	@Size(max = 100)
	private String description;

	@Column(columnDefinition = "date_publication")
	private LocalDate date_publication;

	@Column(columnDefinition = "date_visualization")
	private LocalDate date_visualization;

	public Notification() {
	}

	public Notification(String title, String description, LocalDate date_publication, LocalDate date_visualization) {
		this.title = title;
		this.description = description;
		this.date_publication = date_publication;
		this.date_visualization = date_visualization;
	}

	public Integer getIdnotification() {
		return idnotification;
	}

	public void setIdnotification(Integer idnotification) {
		this.idnotification = idnotification;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDate_publication() {
		return date_publication;
	}

	public void setDate_publication(LocalDate date_publication) {
		this.date_publication = date_publication;
	}

	public LocalDate getDate_visualization() {
		return date_visualization;
	}

	public void setDate_visualization(LocalDate date_visualization) {
		this.date_visualization = date_visualization;
	}
}
