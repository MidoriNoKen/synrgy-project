package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.services.CartService;
import com.taufik.services.PaymentService;
import com.taufik.utils.Util;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UtilTest {

  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();
  private final PrintStream standardOut = System.out;
  private final InputStream standardIn = System.in;

  @BeforeEach
  void setUp() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @Test
  void testHeader() {
    Util.header("Test Header Message");
    assertTrue(outputStreamCaptor.toString().contains("Test Header Message"));
  }

  @Test
  void testUserOption() {
    ByteArrayInputStream in = new ByteArrayInputStream("5\n".getBytes());
    System.setIn(in);

    int option = Util.userOption();
    assertEquals(5, option);
  }

  @Test
  void testUserField() {
    ByteArrayInputStream in = new ByteArrayInputStream(
      "Test Field Input\n".getBytes()
    );
    System.setIn(in);

    String field = Util.userField("Enter a field: ");
    assertEquals("Test Field Input", field);
  }

  @Test
  void testUserValue() {
    ByteArrayInputStream in = new ByteArrayInputStream("12345\n".getBytes());
    System.setIn(in);

    Long value = Util.userValue();
    assertEquals(12345L, value);
  }

  @Test
  void testAdditionSeparator() {
    String separator = Util.additionSeparator();
    assertEquals("------------------------------+", separator);
  }

  @Test
  void testGeneralMenu() {
    Util.generalMenu("Main Menu", "Additional Option");
    assertTrue(outputStreamCaptor.toString().contains("Main Menu"));
    assertTrue(outputStreamCaptor.toString().contains("Additional Option"));
  }

  @Test
  void testClearData() {
    assertDoesNotThrow(() -> Util.clearData());
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
    System.setIn(standardIn);
  }
}
