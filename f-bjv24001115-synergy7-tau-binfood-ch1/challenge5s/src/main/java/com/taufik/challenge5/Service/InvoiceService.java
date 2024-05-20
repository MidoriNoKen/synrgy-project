package com.taufik.challenge5.Service;

public interface InvoiceService {
    byte[] generateInvoice(Long userId);

    byte[] generateReportingMerchant(Long merchantId, String period);
}
