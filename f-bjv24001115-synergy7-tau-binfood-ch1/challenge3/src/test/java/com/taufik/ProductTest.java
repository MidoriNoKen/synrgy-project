package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.models.entities.Product;
import com.taufik.services.ProductService;
import java.time.LocalDateTime;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

  /*
        1. Cek hasil dari metode getAll, apakah mengembalikan nilai yang sesuai atau tidak
        2. Cek hasil dari metode getById, apakah mengembalikan nilai yang sesuai atau tidak
        3. Cek hasil dari metode add, apakah data menyimpan nilai yang sesuai atau tidak (termasuk tanggal)
    */

  @BeforeEach
  void setUp() {
    Product.getAll().clear();
  }

  @Test
  void testGetAll() {
    ProductService.add(1L, "Product 1", 100L);
    ProductService.add(2L, "Product 2", 200L);

    Map<Long, Product> allProducts = ProductService.getAll();
    assertNotNull(allProducts);
    assertEquals(2, allProducts.size());
  }

  @Test
  void testGetById() {
    ProductService.add(1L, "Product 1", 100L);
    ProductService.add(2L, "Product 2", 200L);

    Product product1 = ProductService.getById(1L);
    Product product2 = ProductService.getById(2L);
    assertNotNull(product1);
    assertNotNull(product2);
    assertEquals("Product 1", product1.getName());
    assertEquals("Product 2", product2.getName());
    assertEquals(100L, product1.getPrice());
    assertEquals(200L, product2.getPrice());
  }

  @Test
  void testAdd() {
    Long productId = 1L;
    String productName = "New Product";
    Long productPrice = 300L;
    ProductService.add(productId, productName, productPrice);

    Product addedProduct = ProductService.getById(productId);
    assertNotNull(addedProduct);
    assertEquals(productId, addedProduct.getId());
    assertEquals(productName, addedProduct.getName());
    assertEquals(productPrice, addedProduct.getPrice());

    LocalDateTime now = LocalDateTime.now();
    assertTrue(
      addedProduct.getCreatedDate().isBefore(now) ||
      addedProduct.getCreatedDate().isEqual(now)
    );
    assertTrue(
      addedProduct.getUpdatedDate().isBefore(now) ||
      addedProduct.getUpdatedDate().isEqual(now)
    );
  }

  @Test
  void testAdd_NullId() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        ProductService.add(null, "Product", 100L);
      }
    );
  }

  @Test
  void testAdd_NullName() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        ProductService.add(1L, null, 100L);
      }
    );
  }

  @Test
  void testAdd_NullPrice() {
    assertThrows(
      IllegalArgumentException.class,
      () -> {
        ProductService.add(1L, "Product", null);
      }
    );
  }

  @Test
  void testAdd_DuplicateId() {
    ProductService.add(1L, "Product 1", 100L);

    assertThrows(
      IllegalArgumentException.class,
      () -> {
        ProductService.add(1L, "Product 2", 200L);
      }
    );
  }
}
