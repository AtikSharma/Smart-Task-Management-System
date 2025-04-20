package com.taskmanager.common.util;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class URLBuilder {

	private String protocol;
	private String host;
	private String port;
	private String serviceName;
	private List<String> pathSegments = new ArrayList<>();
	private Map<String, String> queryParams = new LinkedHashMap<>();

	public static Builder builder() {
		return new Builder();
	}

	private String encode(String value) {
		try {
			return URLEncoder.encode(value, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("Error encoding URL component: " + value, e);
		}
	}

	public String build() {

		StringBuilder url = new StringBuilder(protocol + "://");

		if (host != null && port != null) {
			url.append(host);
			url.append(":" + port);
		} else if (serviceName != null) {
			url.append(serviceName + "/");
		}

		// Append path segments
		for (String segment : pathSegments) {
			if (!url.toString().endsWith("/")) {
				url.append("/");
			}
			url.append(encode(segment));
		}

		// Append query parameters
		if (!queryParams.isEmpty()) {
			url.append("?");
			queryParams.forEach((key, value) -> {
				url.append(encode(key)).append("=").append(encode(value)).append("&");
			});
			url.deleteCharAt(url.length() - 1); // Remove trailing &
		}

		return url.toString();
	}

	public static class Builder {

		private final URLBuilder urlBuilder;

		public Builder() {
			this.urlBuilder = new URLBuilder();
		}

		private String protocol;
		private String host;
		private String port;
		private String serviceName;
		private List<String> pathSegments = new ArrayList<>();
		private Map<String, String> queryParams = new LinkedHashMap<>();

		public Builder protocol(String protocol) {
			this.urlBuilder.protocol = protocol;
			return this;
		}

		public Builder host(String host) {
			this.urlBuilder.host = host;
			return this;
		}

		public Builder port(String port) {
			this.urlBuilder.port = port;
			return this;
		}

		public Builder serviceName(String serviceName) {
			this.urlBuilder.serviceName = serviceName;
			return this;
		}

		public Builder addPathSegment(String segment) {
			this.urlBuilder.pathSegments.add(segment);
			return this;
		}

		public Builder addQueryParam(String key, String value) {
			this.urlBuilder.queryParams.put(key, value);
			return this;
		}

		public String build() {
			return this.urlBuilder.build();
		}
	}

}
