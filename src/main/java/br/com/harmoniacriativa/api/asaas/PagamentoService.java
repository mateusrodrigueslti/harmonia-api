package br.com.harmoniacriativa.api.asaas;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PagamentoService {

    private final AsaasClient asaasClient;

    public PaymentListResponse listarPagamentos() {
        return asaasClient.listarPagamentos();
    }
}
