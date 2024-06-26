package bookstore;

import annotations.Vavr_Either;
import annotations.Vavr_Lazy;
import annotations.Vavr_Option;
import bookstore.warehouse.WarehouseApi;
import io.vavr.Lazy;
import io.vavr.control.Either;
import io.vavr.control.Option;
import bookstore.warehouse.data.WarehouseServiceError;
import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.data.WarehouseBookISBN;

import java.math.BigDecimal;

@Vavr_Option
@Vavr_Either
@Vavr_Lazy
public class BookCustomerOfferService implements FindBook {

    public static final String DUMMY_OFFER_NUMBER = "9999";
    public static final String DUMMY_OFFER_TITLE = "Super promo offer in case of shop is down";
    public static final String DUMMY_OFFER_AUTHOR = "Some very trendy author";

    private final WarehouseApi warehouseApi;

    BookCustomerOfferService(final WarehouseApi warehouseApi) {
        this.warehouseApi = warehouseApi;
    }

    @Override
    public Option<BookOffer> findBookDetails(WarehouseBookISBN isbn) {
        return Option.of(isbn)
                .map(WarehouseBookISBN::number)
                .map(number -> warehouseApi.callForBookData(number))
                .fold(Option::none, this::handleResult);
    }

    private Option<BookOffer> handleResult(Either<WarehouseServiceError, WarehouseBook> callResult) {

        if (callResult.isRight()) {
            WarehouseBook right = callResult.get();
            return Option.of(new BookOffer(
                    right.isbn(),
                    right.title(),
                    right.author(),
                    new BigDecimal("100.00")
            ));    
        } else {
            Lazy<Option<BookOffer>> lazy = provideSomePromoOfferInCaseOfStockIsDown();
            return lazy.get();
        }
    }

    private Lazy<Option<BookOffer>> provideSomePromoOfferInCaseOfStockIsDown() {

        return Lazy.of(() -> Option.of( new BookOffer(
            new WarehouseBookISBN(DUMMY_OFFER_NUMBER),
                DUMMY_OFFER_TITLE,
                DUMMY_OFFER_AUTHOR,
            new BigDecimal("10.00"))
        ));
    }
}
