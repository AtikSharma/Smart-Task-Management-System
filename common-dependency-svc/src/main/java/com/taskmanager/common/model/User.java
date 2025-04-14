package com.taskmanager.common.model;

import java.time.LocalDateTime;

import com.taskmanager.common.enums.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder(toBuilder = true)
public class User extends UserBase {

	private Status status;

	private LocalDateTime createdAt;

	private LocalDateTime updatedAt;
}
