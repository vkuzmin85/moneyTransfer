package ru.netology.moneytransfer.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.netology.moneytransfer.model.ConfirmOperation;
import ru.netology.moneytransfer.model.OperationId;
import ru.netology.moneytransfer.model.TransferRequest;
import ru.netology.moneytransfer.service.TransferService;

@RestController
@Slf4j
public class TransferController {
    private TransferService transferService;
    public TransferController(TransferService transferService) {
        this.transferService = transferService;
    }

    @CrossOrigin
    @PostMapping("/transfer")
    private ResponseEntity<OperationId> transfer(@RequestBody TransferRequest transferRequest) {
        log.info("Transfer is started...");
        return ResponseEntity.ok(transferService.preTransfer(transferRequest));
    }

    @CrossOrigin
    @PostMapping("/confirmOperation")
    private ResponseEntity<ConfirmOperation> confirmOperation(@RequestBody ConfirmOperation confirmOperation) {
        log.info("Confirmation is started...");
        return ResponseEntity.ok().body(transferService.confirmOfOperation(confirmOperation));
    }
}