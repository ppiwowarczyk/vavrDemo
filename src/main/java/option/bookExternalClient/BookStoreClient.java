package option.bookExternalClient;

import annotations.Vavr_Option;

@Vavr_Option
public class BookStoreClient {

    public BookDetailsExternalServiceRecord callForBookData(String number) {
        return new BookDetailsExternalServiceRecord(new ISBN(number), "Dune", "Herbert");
    }
}
