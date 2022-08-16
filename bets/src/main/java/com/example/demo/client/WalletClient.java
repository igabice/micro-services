package com.example.demo.client;




import java.io.IOException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.example.demo.rest.response.BalanceResponse;


@Component
public class WalletClient {

	public BalanceResponse getBalance(Long accountId) throws RestClientException, IOException {

		String baseUrl = "http://wallet:8091/api/wallet/balance/" + accountId;
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<BalanceResponse> response = null;
		try {
			response = restTemplate.exchange(baseUrl, HttpMethod.GET, getHeaders(), BalanceResponse.class);
		} catch (Exception ex) {
			System.out.println(ex);
		}
		return response.getBody();
	}

	private static HttpEntity<?> getHeaders() throws IOException {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		return new HttpEntity<>(headers);
	}
}