package com.taufik;

import static org.junit.jupiter.api.Assertions.*;

import com.taufik.models.entities.Cart;
import com.taufik.services.CartService;
import com.taufik.services.ProductService;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CartTest {

  /*
        1. Cek hasil dari metode getAll, apakah mengembalikan nilai yang sesuai atau tidak
        2. Cek hasil dari metode getTotalPrice, apakah mengembalikan total harga yang sesuai atau tidak
        3. Cek hasil dari metode getTotalQuantity, apakah mengembalikan total barang yang sesuai atau tidak
        4. Cek hasil dari metode getById, apakah mengembalikan nilai yang sesuai atau tidak
        5. Cek hasil dari metode add, apakah data menyimpan nilai yang sesuai atau tidak
        6. Cek keranjang, apakah fungsi untuk mengosongkan keranjang sesuai atau tidak

        Notes:
          Hitung total harga
          Product 1: Price 100 * Quantity 2 = 200
          Product 2: Price 200 * Quantity 3 = 600
          Total Price = 200 + 600 = 800

          Hitung total barang
          Total Quantity = 2 + 3 = 5
    */

  @BeforeEach
  void setUp() {
    Cart.getAll().clear();
  }

  @Test
  void testGetAll() {
    List<Cart> allCarts = CartService.getAll();
    assertNull(allCarts);

    CartService.add(1L, 2);
    CartService.add(2L, 3);

    allCarts = CartService.getAll();
    assertNotNull(allCarts);
    assertEquals(2, allCarts.size());
  }

  @Test
  void testGetTotalPrice() {
    ProductService.add(1L, "Product 1", 100L);
    ProductService.add(2L, "Product 2", 200L);
    CartService.add(1L, 2);
    CartService.add(2L, 3);

    assertEquals(800L, CartService.getTotalPrice());
  }

  @Test
  void testGetTotalQuantity() {
    CartService.add(1L, 2);
    CartService.add(2L, 3);

    assertEquals(5L, CartService.getTotalQuantity());
  }

  @Test
  void testAdd() {
    Long productId = 1L;
    int quantity = 2;
    CartService.add(productId, quantity);

    List<Cart> allCarts = CartService.getAll();
    assertNotNull(allCarts);
    assertEquals(1, allCarts.size());
    Cart addedCart = allCarts.get(0);
    assertEquals(productId, addedCart.getProductId());
    assertEquals(quantity, addedCart.getQuantity());
  }

  @Test
  void testClear() {
    CartService.add(1L, 2);
    CartService.add(2L, 3);

    CartService.clear();

    List<Cart> allCarts = CartService.getAll();
    assertNull(allCarts);
  }

  @Test
  void testAddInvalidData() {
    assertThrows(
      IllegalArgumentException.class,
      () -> CartService.add(null, 2)
    );
    assertThrows(IllegalArgumentException.class, () -> CartService.add(1L, 0));
  }
}
