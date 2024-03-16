package option;

import annotations.Vavr_Option;
import option.bookExternalClient.ISBN;

import java.math.BigDecimal;

@Vavr_Option
public record BookDetailsSell(ISBN isbn, String title, String author, BigDecimal price){}
