package bookstore;

import annotations.Vavr_Option;
import io.vavr.control.Option;
import bookstore.warehouse.WarehouseClientMock;
import bookstore.warehouse.data.WarehouseBookISBN;
import org.junit.Test;

import java.util.Optional;

import static bookstore.BookCustomerOfferService.DUMMY_OFFER_TITLE;
import static bookstore.warehouse.WarehouseClientMock.CRASHING_APP_ISDN;
import static org.assertj.core.api.Assertions.assertThat;

public class BookCustomerTest
{
    @Test
    @Vavr_Option
    public void shouldReturnBookOffer()
    {
        // given
        FindBook bookFind = new BookCustomerOfferService(new WarehouseClientMock());

        // when
        Option<BookOffer> bookDetailWithVavrOption = bookFind.findBookDetails(new WarehouseBookISBN("1234"));
        Optional<BookOffer> bookDetailsWithJavaOptional = Optional.of(bookDetailWithVavrOption.get());

        // then
        assertThat(bookDetailWithVavrOption.get().title()).isEqualTo("Dune");
        assertThat(bookDetailsWithJavaOptional.get().title()).isEqualTo("Dune");
    }

    @Test
    public void shouldReturnPromoOfferInCaseOfWarehouseCrash() {

        // given
        FindBook bookFind = new BookCustomerOfferService(new WarehouseClientMock());

        // when
        Option<BookOffer> bookDetailWithVavrOption = bookFind.findBookDetails(new WarehouseBookISBN(CRASHING_APP_ISDN));

        // then
        assertThat(bookDetailWithVavrOption.get().title()).isEqualTo(DUMMY_OFFER_TITLE);
    }
}