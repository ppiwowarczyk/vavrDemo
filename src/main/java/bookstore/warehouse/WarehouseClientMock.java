package bookstore.warehouse;

import annotations.Vavr_Either;
import bookstore.warehouse.data.WarehouseServiceError;
import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.data.WarehouseBookISBN;
import io.vavr.control.Either;

@Vavr_Either
public class WarehouseClientMock implements WarehouseApi {

    public static final String CRASHING_APP_ISDN ="0000";
    public static final String STANDARD_TITLE ="Dune";
    public static final String STANDARD_AUTHOR ="Herbert";

    public Either<WarehouseBook, WarehouseServiceError> callForBookData(String number) {

        if(number.equals(CRASHING_APP_ISDN)) {
            return Either.right(new WarehouseServiceError("Unable to connect to system"));
        }

        return Either.left(new WarehouseBook(new WarehouseBookISBN(number), STANDARD_TITLE, STANDARD_AUTHOR));
    }
}
