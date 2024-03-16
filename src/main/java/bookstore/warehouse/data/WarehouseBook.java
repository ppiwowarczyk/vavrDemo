package bookstore.warehouse.data;

import annotations.Vavr_Option;

@Vavr_Option
public record WarehouseBook(WarehouseBookISBN isbn, String title, String author){}
