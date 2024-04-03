package bookstore.warehouse;

import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.data.WarehouseServiceError;
import io.vavr.control.Either;

public interface WarehouseApi {

    Either<WarehouseServiceError, WarehouseBook> callForBookData(String number);
}
