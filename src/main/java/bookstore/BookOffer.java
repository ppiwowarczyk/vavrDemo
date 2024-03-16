package bookstore;

import annotations.Vavr_Option;
import bookstore.warehouse.data.WarehouseBookISBN;

import java.math.BigDecimal;

@Vavr_Option
public record BookOffer(WarehouseBookISBN isbn, String title, String author, BigDecimal price){}
