package com.taskmanager.report.entity;

import java.time.LocalDateTime;
import java.util.UUID;

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
@Table(name = "reports")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ReportEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	private String reportType;

	@Column(nullable = false)
	private String generatedBy;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String content;

	private LocalDateTime createdAt = LocalDateTime.now();
}
