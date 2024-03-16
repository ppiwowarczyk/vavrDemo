package bookstore;

import annotations.Vavr_Option;
import io.vavr.control.Option;
import bookstore.warehouse.data.WarehouseBookISBN;

@Vavr_Option
public interface FindBook {
    Option<BookOffer> findBookDetails(WarehouseBookISBN isbn);
}
