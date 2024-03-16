package option;

import annotations.Vavr_Option;
import io.vavr.control.Option;
import option.bookExternalClient.BookDetailsExternalServiceRecord;
import option.bookExternalClient.BookStoreClient;
import option.bookExternalClient.ISBN;

import java.math.BigDecimal;

@Vavr_Option
public class BookService implements BookFind {

    private final BookStoreClient bookStoreClient;

    BookService(final BookStoreClient bookStoreClient) {
        this.bookStoreClient = bookStoreClient;
    }

    @Override
    public Option<BookDetailsSell> findBookDetails(ISBN isbn) {
        return Option.of(isbn)
                .map(ISBN::number)
                .map(number -> bookStoreClient.callForBookData(number))
                .fold(Option::none, this::handleResult);
    }

    private Option<BookDetailsSell> handleResult(BookDetailsExternalServiceRecord callResult) {

        return Option.of(new BookDetailsSell(
                callResult.isbn(),
                callResult.title(),
                callResult.author(),
                new BigDecimal("100.00")
        ));
    }


}
