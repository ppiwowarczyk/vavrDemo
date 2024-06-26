# Intro

In this project, we are going to explore exactly what `vavr` is, why we need it and how to use it in our projects.
We wil be using fake application where we used `vavr` to show more less real live examples of usadge in real code.

# What is vavr

Vavr is a functional library for Java 8+ that provides immutable data types and functional control structures.

# Demo

There is also annotations refers to particular functionalities for examples :
````
@Vavr_Option
@Vavr_Either
@Vavr_Try
@Vavr_Lazy
````

Classes where those part of `vavr` are used are annotated so can be easily track in project.

## Option
We are using `Option` as return element for books but also using `fold` to process results.

## Either
We are using `Either` during communication with external warehouse system as we can get `Option` of book from warehouse or some error with warehouse access.

## Try
While communicating with external warehouse system we are using `Try` to handle in functional way exception handling for external service communication. 

## Lazy
We use `Lazy` in class where book customer service is providing some dummy fake offer in case of external warehouse access is down. We are using `Lazy` to compute offer only if needed as this is easy example but in rral case we might run some recommendations time-consuming computation. 

# Materials

* https://geek.justjoin.it/biblioteka-vavr/
* https://docs.vavr.io/
* https://www.baeldung.com/vavr
* https://koziolekweb.pl/2016/03/20/masakra-javy-wyjatkowa-monada/
* https://www.youtube.com/watch?v=2JTlFAjhL3U
* https://dzone.com/articles/using-java-optional-vs-vavr-bookstore
