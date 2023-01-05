package com.examplenative.util;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

@Component
public class HttpClientUtils {

	private HttpClient httpClient;
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private static final String AUTH = "Authorization";
	private static final String CONTENT_TYPE = "Content-Type";

	public HttpResponse<String> restCall(String url, String auth, String body, String contentType, HttpMethod method) {

		HttpResponse<String> response = null;
		int retries = 1;

		do {

			logger.info("Inicio chamada {}", url);
			HttpRequest request = null;
			switch (method.toString()) {
			case "POST":
				request = HttpRequest.newBuilder(URI.create(url)).header(CONTENT_TYPE, contentType).header(AUTH, auth)
						.POST(HttpRequest.BodyPublishers.ofString(body)).build();
				break;
			case "PUT":
				request = HttpRequest.newBuilder(URI.create(url)).header(CONTENT_TYPE, contentType).header(AUTH, auth)
						.PUT(HttpRequest.BodyPublishers.ofString(body)).build();
				break;
			case "GET":
				request = HttpRequest.newBuilder(URI.create(url)).header(CONTENT_TYPE, contentType).header(AUTH, auth)
						.GET().build();
				break;
			default:
				request = HttpRequest.newBuilder(URI.create(url)).header(CONTENT_TYPE, contentType).header(AUTH, auth)
						.DELETE().build();
				break;
			}

			try {

				if (httpClient == null) {
					logger.info("Criando instancia https");
					httpClient = HttpClient.newHttpClient();
				}

				logger.info("Efetuando chamada");
				response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

				if (response == null || response != null && (response.statusCode() != 200
						&& response.statusCode() != 204 && response.statusCode() != 202)) {
					logger.error("Failed. URL: {}", url);
				} else {
					logger.info("Success send To: {} Body: {}", url, body);
				}

			} catch (IOException | InterruptedException e) {
				logger.error("Failed URL: {} Error: {}", url, e.getMessage());
				Thread.currentThread().interrupt();
			}

			retries--;

		} while ((response == null
				|| response.statusCode() != 200 && response.statusCode() != 204 && response.statusCode() != 202)
				&& retries > 0);

		return response;
	}

	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

}