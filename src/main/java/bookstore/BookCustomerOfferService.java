package bookstore;

import annotations.Vavr_Option;
import io.vavr.control.Either;
import io.vavr.control.Option;
import bookstore.warehouse.data.WarehouseServiceError;
import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.WarehouseClientMock;
import bookstore.warehouse.data.WarehouseBookISBN;

import java.math.BigDecimal;

@Vavr_Option
public class BookCustomerOfferService implements FindBook {

    public static final String DUMMY_OFFER_NUMBER = "9999";
    public static final String DUMMY_OFFER_TITLE = "Super promo offer in case of shop is down";
    public static final String DUMMY_OFFER_AUTHOR = "Some very trendy author";

    private final WarehouseClientMock bookStoreClient;

    BookCustomerOfferService(final WarehouseClientMock bookStoreClient) {
        this.bookStoreClient = bookStoreClient;
    }

    @Override
    public Option<BookOffer> findBookDetails(WarehouseBookISBN isbn) {
        return Option.of(isbn)
                .map(WarehouseBookISBN::number)
                .map(number -> bookStoreClient.callForBookData(number))
                .fold(Option::none, this::handleResult);
    }

    private Option<BookOffer> handleResult(Either<WarehouseBook, WarehouseServiceError> callResult) {

        if (callResult.isLeft()) {
            WarehouseBook left = callResult.getLeft();
            return Option.of(new BookOffer(
                    left.isbn(),
                    left.title(),
                    left.author(),
                    new BigDecimal("100.00")
            ));    
        } else {
           return provideSomePromoOfferInCaseOfStockIsDown();
        }
    }

    private Option<BookOffer> provideSomePromoOfferInCaseOfStockIsDown() {

        return Option.of( new BookOffer(
            new WarehouseBookISBN(DUMMY_OFFER_NUMBER),
                DUMMY_OFFER_TITLE,
                DUMMY_OFFER_AUTHOR,
            new BigDecimal("10.00")
        ));
    }
}
