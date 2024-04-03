package bookstore.warehouse.external;

import bookstore.warehouse.data.WarehouseBook;
import bookstore.warehouse.data.WarehouseBookISBN;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class ExternalRestServiceWarehouse {
    public static final String CRASHING_APP_ISDN = "0000";
    public static final String STANDARD_TITLE = "Dune";
    public static final String STANDARD_AUTHOR = "Herbert";

    public WarehouseBook get(String number) {

        if (number.equals(CRASHING_APP_ISDN)) {
            throw new HttpClientErrorException(HttpStatus.FORBIDDEN);
        }

        return new WarehouseBook(new WarehouseBookISBN(number), STANDARD_TITLE, STANDARD_AUTHOR);
    }
}
