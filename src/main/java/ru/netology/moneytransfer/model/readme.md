classDiagram
direction BT
class Amount {
  + getCurrency() String
  + getValue() int
  + hashCode() int
  + setCommissionPercent(int) void
  + getCommission() int
  + equals(Object) boolean
  + toString() String
}
class BalanceErrorException {
  + getMessage() String
}
class Card {
  + getAmount() AtomicInteger
  + getNumber() String
  + setAmount(AtomicInteger) void
  + setNumber(String) void
  + getValid() String
  + getCvv() String
  + setValid(String) void
  + setCvv(String) void
}
class CardInvalidDataException
class CardsRepository {
  + getCardByNumber(String) Optional~Card~
  + getCardByWholeInfo(String, String, String) Optional~Card~
}
class ConfirmOperation {
  + getCode() String
  + setCode(String) void
  + getOperationId() String
  + setOperationId(String) void
  + toString() String
}
class ExceptionHandlerAdvice {
  + BalanceErrorException(BalanceErrorException) ResponseEntity~String~
  + cardInvalidDateException(InvalidDataException) ResponseEntity~String~
  + invalidDataException(CardInvalidDataException) ResponseEntity~String~
  + transactionErrorException(TransactionErrorException) ResponseEntity~String~
}
class InvalidDataException {
  + getMessage() String
}
class MoneyTransferApplication {
  + main(String[]) void
}
class OperationId {
  + toString() String
  + getOperationId() String
}
class TransactionErrorException
class TransferController
class TransferInfo {
  + equals(Object) boolean
  + setCardFrom(Card) void
  + getCardFrom() Card
  + setCode(String) void
  + getCardTo() Card
  + setValue(int) void
  + getCommission() int
  + getValue() int
  + setCommission(int) void
  + setCardTo(Card) void
  + hashCode() int
  + getCode() String
}
class TransferRepository {
  + addTransfer(String, TransferInfo) void
  + getTransferByOperationId(String) Optional~TransferInfo~
}
class TransferRequest {
  + hashCode() int
  + getAmount() Amount
  + setCode(String) void
  + getCardToNumber() String
  + toString() String
  + getCode() String
  + getCardFromCVV() String
  + equals(Object) boolean
  + getCardFromNumber() String
  + getCardFromValidTill() String
}
class TransferResponse {
  + setOperationId(String) void
  + getOperationId() String
}
class TransferService {
  + preTransfer(TransferRequest) OperationId
  + getCardTo(String) Card
  + getCardFrom(String, String, String) Card
  + checkBalance(Card, Amount) boolean
  + confirmOfOperation(ConfirmOperation) ConfirmOperation
  + addTransferRepository(Card, Card, Amount) OperationId
  + escapeTransfer(TransferInfo) void
}

CardsRepository "1" *--> "cardRepository *" Card 
CardsRepository  ..>  Card : «create»
TransferController "1" *--> "transferService 1" TransferService 
TransferInfo "1" *--> "cardFrom 1" Card 
TransferRepository "1" *--> "TransferRepository *" TransferInfo 
TransferRequest "1" *--> "amount 1" Amount 
TransferService  ..>  CardInvalidDataException : «create»
TransferService "1" *--> "cardsRepository 1" CardsRepository 
TransferService  ..>  InvalidDataException : «create»
TransferService  ..>  OperationId : «create»
TransferService  ..>  TransactionErrorException : «create»
TransferService  ..>  TransferInfo : «create»
TransferService "1" *--> "transferRepository 1" TransferRepository 
