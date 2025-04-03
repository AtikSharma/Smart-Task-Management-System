package com.taskmanager.notification.entity;

import java.time.LocalDateTime;

import com.taskmanager.common.enums.NotificationStatus;
import com.taskmanager.common.enums.NotificationType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "notifications")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NotificationEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.UUID)
	private String id;

	@Column(nullable = false)
	private String userId;

	private String taskId;

	@Column(columnDefinition = "TEXT", nullable = false)
	private String message;

	@Enumerated(EnumType.STRING)
	private NotificationType type;

	@Enumerated(EnumType.STRING)
	private NotificationStatus status = NotificationStatus.PENDING;

	private LocalDateTime createdAt;

}
