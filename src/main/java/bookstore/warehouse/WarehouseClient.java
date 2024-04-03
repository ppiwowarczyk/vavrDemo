package bookstore.warehouse;

import annotations.Vavr_Either;
import annotations.Vavr_Try;
import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.data.WarehouseBookISBN;
import bookstore.warehouse.data.WarehouseServiceError;
import bookstore.warehouse.external.ExternalRestServiceWarehouse;
import io.vavr.control.Either;
import io.vavr.control.Try;

import java.util.logging.Level;
import java.util.logging.Logger;

@Vavr_Either
@Vavr_Try
public class WarehouseClient implements WarehouseApi {

    private static final Logger LOGGER = Logger.getLogger( WarehouseClient.class.getName() );

    private ExternalRestServiceWarehouse externalRestServiceWarehouse;

    public WarehouseClient(ExternalRestServiceWarehouse externalRestServiceWarehouse) {
        this.externalRestServiceWarehouse = externalRestServiceWarehouse;
    }

    public Either<WarehouseServiceError, WarehouseBook> callForBookData(String number) {

        Either<Throwable, WarehouseBook> result = Try.of(() -> externalRestServiceWarehouse.get(number))
                .onFailure(throwable -> LOGGER.log(Level.INFO, "Failed to get data", throwable))
                .toEither();

        if (result.isLeft()) {
            return Either.left(provideClientError());
        }
        else {
            return Either.right(result.get());
        }
    }

    private WarehouseServiceError provideClientError() {
        return new WarehouseServiceError("Unable to connect to system");
    }
}
