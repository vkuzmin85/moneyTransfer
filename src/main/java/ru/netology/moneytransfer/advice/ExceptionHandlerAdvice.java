package ru.netology.moneytransfer.advice;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransfer.exceptions.BalanceErrorException;
import ru.netology.moneytransfer.exceptions.CardInvalidDataException;
import ru.netology.moneytransfer.exceptions.InvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;

@RestControllerAdvice
@Slf4j
public class ExceptionHandlerAdvice {
    @ExceptionHandler(CardInvalidDataException.class)
    public ResponseEntity<String> cardInvalidDateException(CardInvalidDataException ex) {
        log.info("Некорректные данные " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidDataException(InvalidDataException ex) {
        log.info("Некорректные данные " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(BalanceErrorException.class)
    public ResponseEntity<String> balanceErrorException(BalanceErrorException ex) {
        log.info("Недостаточно средств " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(TransactionErrorException.class)
    public ResponseEntity<String> transactionErrorException(TransactionErrorException ex) {
        log.info("Ошибка транзакции " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}