package bookstore;

import bookstore.warehouse.data.WarehouseBookISBN;

import java.math.BigDecimal;

public record BookOffer(WarehouseBookISBN isbn, String title, String author, BigDecimal price){}
