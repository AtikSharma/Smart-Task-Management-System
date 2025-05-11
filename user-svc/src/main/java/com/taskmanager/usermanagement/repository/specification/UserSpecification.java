package com.taskmanager.usermanagement.repository.specification;

import java.util.UUID;

import org.springframework.data.jpa.domain.Specification;

import com.taskmanager.common.util.StringUtils;
import com.taskmanager.usermanagement.entity.UserEntity;

import jakarta.persistence.criteria.Predicate;

public class UserSpecification {

	public static Specification<UserEntity> matchIdentifier(String identifier) {
		return (root, query, cb) -> {
			Predicate predicate = null;
			if (StringUtils.isValidUUID.test(identifier)) {
				predicate = cb.equal(root.get("id"), UUID.fromString(identifier));
			} else if (StringUtils.isValidEmail.test(identifier)) {
				predicate = cb.equal(root.get("email"), identifier);
			} else {
				predicate = cb.equal(root.get("username"), identifier);
			}
			return predicate;
		};
	}
}
