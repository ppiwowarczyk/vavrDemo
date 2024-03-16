package option.bookExternalClient;

import annotations.Vavr_Option;

@Vavr_Option
public record BookDetailsExternalServiceRecord(ISBN isbn, String title, String author){}
