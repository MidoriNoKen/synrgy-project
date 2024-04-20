package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.models.entities.Payment;
import com.taufik.services.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class PaymentTest {

  /*
        1. Menjamin kondisi awal bersih sebelum setiap pengujian
        2. Pengujian untuk metode addPaymentMethod. Cek metode pembayaran dengan membuat data kemudian cek sizenya
        3. Pengujian untuk metode addPayment. Cek pembayaran yang telah dibuat
        4. Pengujian untuk metode clear. Cek apakah data benar-benar kosong atau tidak
        5. Pengujian untuk metode addPaymentMethod jika nama null. Cek apakah exception sesuai ketika isi Metode Pembayaran kosong ketika input
        6. Pengujian untuk metode addPayment jika nilai null. Cek apakah exception sesuai ketika isi Pembayaran kosong ketika
    */

  @BeforeEach
  void setUp() {
    PaymentService.clear();
  }

  @Test
  void testInit() {
    PaymentService paymentService = new PaymentService();
    assertNotNull(paymentService);
  }

  @Test
  void testConstructor() {
    int paymentMethod = 1;
    Long nominal = 50000L;

    Payment payment = new Payment(paymentMethod, nominal);

    assertNotNull(payment);
    assertEquals(paymentMethod, payment.getPaymentMethod());
    assertEquals("Cash", payment.getPaymentMethodSelected());
    assertEquals(nominal, payment.getNominal());
  }

  @Test
  void testAddPaymentMethod() {
    int initialSize = PaymentService.getAllPaymentMethods().size();

    PaymentService.addPaymentMethod("Cash");
    PaymentService.addPaymentMethod("Bank");
    PaymentService.addPaymentMethod("Online Payment");

    assertEquals(initialSize + 3, PaymentService.getAllPaymentMethods().size());
  }

  @Test
  void testAddPayment() {
    PaymentService.addPaymentMethod("Cash");
    PaymentService.addPayment(1, 50000L);

    assertNotNull(PaymentService.getNominalPayment());
    assertEquals(50000L, PaymentService.getNominalPayment());
    assertEquals("Cash", PaymentService.getPaymentMethod());
  }

  @Test
  void testClear() {
    PaymentService.addPaymentMethod("Cash");
    PaymentService.addPayment(1, 50000L);
    PaymentService.clear();

    if (PaymentService.payment != null) {
      assertNull(PaymentService.getNominalPayment());
      assertNull(PaymentService.getPaymentMethod());
    }
  }

  @Test
  void testAddPaymentMethod_NullName() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        PaymentService.addPaymentMethod(null);
      }
    );
  }

  @Test
  void testAddPayment_NullValue() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        PaymentService.addPayment(0, 0L);
      }
    );
  }

  @Test
  void testAddPaymentMethod_NullName_False() {
    try {
      PaymentService.addPaymentMethod(null);
    } catch (IllegalArgumentException e) {
      assertFalse(false);
      return;
    }
    assertFalse(true);
  }

  @Test
  void testAddPayment_NullValue_False() {
    try {
      PaymentService.addPayment(0, 0L);
    } catch (IllegalArgumentException e) {
      assertFalse(false);
      return;
    }
    assertFalse(true);
  }
}
