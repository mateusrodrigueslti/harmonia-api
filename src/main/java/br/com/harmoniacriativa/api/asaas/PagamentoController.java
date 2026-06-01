package br.com.harmoniacriativa.api.asaas;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PagamentoController {

    private final PagamentoService pagamentoService;

    @GetMapping("/pagamentos")
    public PaymentListResponse listarPagamentos() {
        return pagamentoService.listarPagamentos();
    }
}
