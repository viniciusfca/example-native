package com.examplenative.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpResponse;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpMethod;

class HttpClientUtilsTest {

	@Test
	void sendMessageTest200_POST() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		var body = "{\n" + "	\"id\": 151744,\n" + "	\"idAtendimento\": 4470,\n"
				+ "	\"protocol\": \"85834d8b-4776-4d3a-99e5-5f2dfb3bcec7\",\n" + "	\"userId\": \"system\",\n"
				+ "	\"userName\": \"System\",\n" + "	\"body\": \"problemas scanner\",\n"
				+ "	\"timestamp\": \"2021-05-17T19:45:56.290+0000\",\n" + "	\"type\": \"MESSAGE_POSITION\",\n"
				+ "	\"transfered\": false,\n" + "	\"read\": true,\n" + "	\"html\": false,\n"
				+ "    \"projectIdChat\":\"r2d2-chat-boot-test-shsm\",\n" + "	\"attendanceWithBot\": true\n" + "}";

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 200);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", body, "", HttpMethod.POST);

		assertEquals(200, expectedResult.statusCode());
	}

	@Test
	void sendMessageTest_POST_401() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		var body = "{\n" + "	\"id\": 151744,\n" + "	\"idAtendimento\": 4470,\n"
				+ "	\"protocol\": \"85834d8b-4776-4d3a-99e5-5f2dfb3bcec7\",\n" + "	\"userId\": \"system\",\n"
				+ "	\"userName\": \"System\",\n" + "	\"body\": \"problemas scanner\",\n"
				+ "	\"timestamp\": \"2021-05-17T19:45:56.290+0000\",\n" + "	\"type\": \"MESSAGE_POSITION\",\n"
				+ "	\"transfered\": false,\n" + "	\"read\": true,\n" + "	\"html\": false,\n"
				+ "    \"projectIdChat\":\"r2d2-chat-boot-test-shsm\",\n" + "	\"attendanceWithBot\": true\n" + "}";

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 401);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", body, "", HttpMethod.POST);

		assertEquals(401, expectedResult.statusCode());
	}

	@Test
	void sendMessageTest200_PUT() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		var body = "{\n" + "	\"id\": 151744,\n" + "	\"idAtendimento\": 4470,\n"
				+ "	\"protocol\": \"85834d8b-4776-4d3a-99e5-5f2dfb3bcec7\",\n" + "	\"userId\": \"system\",\n"
				+ "	\"userName\": \"System\",\n" + "	\"body\": \"problemas scanner\",\n"
				+ "	\"timestamp\": \"2021-05-17T19:45:56.290+0000\",\n" + "	\"type\": \"MESSAGE_POSITION\",\n"
				+ "	\"transfered\": false,\n" + "	\"read\": true,\n" + "	\"html\": false,\n"
				+ "    \"projectIdChat\":\"r2d2-chat-boot-test-shsm\",\n" + "	\"attendanceWithBot\": true\n" + "}";

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 200);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", body, "", HttpMethod.PUT);

		assertEquals(200, expectedResult.statusCode());
	}
	
	@Test
	void sendMessageTest500_PUT() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		var body = "{\n" + "	\"id\": 151744,\n" + "	\"idAtendimento\": 4470,\n"
				+ "	\"protocol\": \"85834d8b-4776-4d3a-99e5-5f2dfb3bcec7\",\n" + "	\"userId\": \"system\",\n"
				+ "	\"userName\": \"System\",\n" + "	\"body\": \"problemas scanner\",\n"
				+ "	\"timestamp\": \"2021-05-17T19:45:56.290+0000\",\n" + "	\"type\": \"MESSAGE_POSITION\",\n"
				+ "	\"transfered\": false,\n" + "	\"read\": true,\n" + "	\"html\": false,\n"
				+ "    \"projectIdChat\":\"r2d2-chat-boot-test-shsm\",\n" + "	\"attendanceWithBot\": true\n" + "}";

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 500);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", body, "", HttpMethod.PUT);

		assertEquals(500, expectedResult.statusCode());
	}

	@Test
	void sendMessageTest200_GET() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 200);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", "", "", HttpMethod.GET);

		assertEquals(200, expectedResult.statusCode());
	}
	
	@Test
	void sendMessageTestIOException_GET() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 200);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenThrow(IOException.class);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", "", "", HttpMethod.GET);

		assertNull(expectedResult);
	}
	

	@Test
	void sendMessageTest200_DELETE() throws IOException, InterruptedException {
		var httpClient = Mockito.mock(HttpClient.class);

		HttpResponse response = new MockHttpResponse<String>(
				"{\n" + "    \"ok\": \"teste\",\n" + "    \"ok\": \"000\"\n" + "}", 200);

		Mockito.when(httpClient.send(Mockito.any(), Mockito.any())).thenReturn(response);

		var target = Mockito.spy(HttpClientUtils.class);
		target.setHttpClient(httpClient);

		var expectedResult = target.restCall("http://r2d2-dectect-intent.com", "", "", "", HttpMethod.DELETE);

		assertEquals(200, expectedResult.statusCode());
	}

}