package com.taufik.challenge6.Services;

import net.sf.jasperreports.engine.JRException;
import org.springframework.stereotype.Service;

import com.taufik.challenge6.Models.Dtos.Order.OrderReceiptDto;

@Service
public interface JasperService {
    byte[] getOrderReport(OrderReceiptDto orderReceiptDto, String format) throws JRException;
}
