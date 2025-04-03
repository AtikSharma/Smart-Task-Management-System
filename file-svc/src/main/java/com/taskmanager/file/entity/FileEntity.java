package com.taskmanager.file.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "files")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String taskId;

	@Column(nullable = false)
	private String uploadedBy;

	@Column(nullable = false)
	private String fileName;

	@Column(nullable = false)
	private String fileType;

	private Long fileSize;

	@Column(nullable = false)
	private String storagePath;

	@Column(nullable = false)
	private LocalDateTime uploadedAt;
}
