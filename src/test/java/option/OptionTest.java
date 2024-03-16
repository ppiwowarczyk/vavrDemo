package option;

import annotations.Vavr_Option;
import io.vavr.control.Option;
import option.bookExternalClient.BookStoreClient;
import option.bookExternalClient.ISBN;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Vavr_Option
public class OptionTest
{
    @Test
    public void test()
    {
        // given
        BookFind bookFind = new BookService(new BookStoreClient());

        // when
        Option<BookDetailsSell> bookDetailWithVavrOption = bookFind.findBookDetails(new ISBN("1234"));
        Optional<BookDetailsSell> bookDetailsWithJavaOptional = Optional.of(bookDetailWithVavrOption.get());

        // then
        assertThat(bookDetailWithVavrOption.get().title()).isEqualTo("Dune");
        assertThat(bookDetailsWithJavaOptional.get().title()).isEqualTo("Dune");
    }
}