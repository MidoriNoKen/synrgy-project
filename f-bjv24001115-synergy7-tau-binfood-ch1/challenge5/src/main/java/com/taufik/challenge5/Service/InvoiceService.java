package com.taufik.challenge5.Service;

import com.taufik.challenge5.Model.DTO.OrderInvoiceDTO;
import com.taufik.challenge5.Model.DTO.UserDTO;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class InvoiceService {
    public byte[] generateInvoice(UserDTO user, List<OrderInvoiceDTO> orders) throws Exception {
        Map<String, Object> parameters = new HashMap<>();

        InputStream reportStream = this.getClass().getResourceAsStream("/reports/invoice_template.jrxml");
        JasperReport jasperReport = JasperCompileManager.compileReport(reportStream);

        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(orders);

        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, dataSource);

        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
