// package com.taufik.challenge5.Service.Impl;

// import com.taufik.challenge5.Model.DTO.OrderDTO;
// import com.taufik.challenge5.Service.InvoiceService;
// import com.taufik.challenge5.Service.OrderService;
// import com.taufik.challenge5.Service.UserService;
// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.stereotype.Service;

// import java.io.ByteArrayOutputStream;
// import java.io.IOException;
// import java.util.List;

// import com.itextpdf.kernel.pdf.PdfDocument;
// import com.itextpdf.kernel.pdf.PdfWriter;
// import com.itextpdf.layout.Document;
// import com.itextpdf.layout.element.Paragraph;

// @Service
// public class InvoiceServiceImpl implements InvoiceService {

// @Autowired
// private OrderService orderService;

// @Autowired
// private UserService userService;

// @Override
// public byte[] generateInvoice(Long userId) {
// List<OrderDTO> orders = orderService.getOrdersByUserId(userId);

// // Generate PDF content
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// try (PdfWriter writer = new PdfWriter(baos);
// PdfDocument pdfDoc = new PdfDocument(writer);
// Document document = new Document(pdfDoc)) {
// document.add(new Paragraph("Invoice for User: " +
// userService.getUserById(userId).getUsername()));
// for (OrderDTO order : orders) {
// document.add(new Paragraph("Order ID: " + order.getOrderId()));
// // Add more order details
// }
// } catch (IOException e) {
// throw new RuntimeException("Error generating invoice PDF", e);
// }
// return baos.toByteArray();
// }

// @Override
// public byte[] generateReportingMerchant(Long merchantId, String period) {
// // Fetch data and generate report content
// ByteArrayOutputStream baos = new ByteArrayOutputStream();
// try {
// // PDF generation logic
// } catch (IOException e) {
// throw new RuntimeException("Error generating report PDF", e);
// }
// return baos.toByteArray();
// }
// }
