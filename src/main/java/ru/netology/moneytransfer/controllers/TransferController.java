package ru.netology.moneytransfer.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.moneytransfer.model.ConfirmOperation;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferRequest;
import ru.netology.moneytransfer.services.TransferService;

@RestController
@CrossOrigin(origins = "https://serp-ya.github.io")
public class TransferController {
    private TransferService transferService;
    private static Logger logger = LoggerFactory.getLogger(TransferService.class);

    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @CrossOrigin
    @PostMapping("/transfer")
    private ResponseEntity<OperationId> transfer(@RequestBody TransferRequest transferRequest) {
        logger.info("Transfer is started...");
        return ResponseEntity.ok(transferService.preTransfer(transferRequest));
    }

    @CrossOrigin
    @PostMapping("/confirmOperation")
    private ResponseEntity<ConfirmOperation> confirmOperation(@RequestBody ConfirmOperation confirmOperation) {
        logger.info("Confirmation is started...");
        return ResponseEntity.ok().body(transferService.confirmOfOperation(confirmOperation));
    }
}