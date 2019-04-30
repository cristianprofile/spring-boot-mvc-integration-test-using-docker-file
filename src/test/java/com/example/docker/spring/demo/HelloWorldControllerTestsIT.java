package com.example.docker.spring.demo;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class HelloWorldControllerTestsIT {

	@Test
	public void contextLoads() throws IOException {
		HttpClient httpClient = HttpClientBuilder.create().build();
		HttpGet getRequest = new HttpGet("http://localhost:8080/greeting");
		//Set the API media type in http accept header
		getRequest.addHeader("accept", "application/xml");
		//Send the request; It will immediately return the response in HttpResponse object
		HttpResponse response = httpClient.execute(getRequest);
		HttpEntity entity = response.getEntity();
		String responseString = EntityUtils.toString(entity, "UTF-8");
		assertEquals(200, response.getStatusLine().getStatusCode());
		Assert.assertEquals(HelloController.RESULT,responseString);
		System.out.println("responseString = " + responseString);
	}

}
