package br.com.harmoniacriativa.api.asaas;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record PaymentListResponse(
    Boolean hasMore,
    Integer totalCount,
    Integer limit,
    Integer offset,
    List<PaymentResponse> data
) {
    public record PaymentResponse(
        String id,
        LocalDate dateCreated,
        String customer,
        BigDecimal value,
        BigDecimal netValue,
        String description,
        String billingType,
        String pixTransaction,
        String status,
        LocalDate dueDate,
        LocalDate originalDueDate,
        LocalDate paymentDate,
        LocalDate clientPaymentDate,
        Integer installmentNumber,
        String invoiceUrl,
        String invoiceNumber,
        String externalReference,
        Boolean deleted,
        Boolean anticipated,
        Boolean anticipable,
        LocalDate creditDate,
        LocalDate estimatedCreditDate,
        String transactionReceiptUrl,
        String nossoNumero,
        String bankSlipUrl
    ) { }
}
