package br.com.harmoniacriativa.api.asaas;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
@RequiredArgsConstructor
public class AsaasClient {

    private final RestClient restClient = RestClient.create();

//    @Value("${asaas.base-url}")
    private String baseUrl;

//    @Value("${asaas.api-key}")
    private String apiKey;

    public PaymentListResponse listarPagamentos() {
        return restClient.get()
            .uri(baseUrl + "/payments")
            .header("accept", "application/json")
            .header("access_token", apiKey)
            .retrieve()
            .body(PaymentListResponse.class);
    }
}
