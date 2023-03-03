package ru.netology.moneytransfer.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.netology.moneytransfer.exceptions.BalanceErrorException;
import ru.netology.moneytransfer.exceptions.CardInvalidDataException;
import ru.netology.moneytransfer.exceptions.InvalidDataException;
import ru.netology.moneytransfer.exceptions.TransactionErrorException;

@RestControllerAdvice
public class ExceptionHandlerAdvice {
    private final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

    @ExceptionHandler(CardInvalidDataException.class)
    public ResponseEntity<String> cardInvalidDateException(InvalidDataException ex) {
        logger.info("Некорректные данные " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(InvalidDataException.class)
    public ResponseEntity<String> invalidDataException(CardInvalidDataException ex) {
        logger.info("Некорректные данные " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(BalanceErrorException.class)
    public ResponseEntity<String> BalanceErrorException(BalanceErrorException ex) {
        logger.info("Недостаточно средств " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }

    @ExceptionHandler(TransactionErrorException.class)
    public ResponseEntity<String> transactionErrorException(TransactionErrorException ex) {
        logger.info("Ошибка транзакции " + ex.getMessage());
        return ResponseEntity
                .badRequest()
                .body(ex.getMessage());
    }
}