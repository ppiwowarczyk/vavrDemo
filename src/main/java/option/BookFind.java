package option;

import annotations.Vavr_Option;
import io.vavr.control.Option;
import option.bookExternalClient.BookDetailsExternalServiceRecord;
import option.bookExternalClient.ISBN;

@Vavr_Option
public interface BookFind {
    Option<BookDetailsSell> findBookDetails(ISBN isbn);
}
