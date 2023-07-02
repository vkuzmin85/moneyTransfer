# Money transfer service application

## Description
The project provides a service for transferring money from one card to another. A web application (FRONT) connects to the service and uses its functionality to transfer money. A description of the web application (FRONT) is available at https://github.com/serp-ya/card-transfer. You can launch the web application by going to https://serp-ya.github.io/card-transfer/.

## Example of use
Two bank cards are created in the application database
| Card number         | Expiration date | CVV code | Card balance | Currency |
|---------------------|-----------------|----------|--------------|----------|
| 1111 1111 1111 1111 | 11/24           | 111      | 10000        | RUB      |
| 2222 2222 2222 2222 |	12/24           |	222      | 20000        | RUB      |

In the web application launched via the link above, fill in the relevant card details and transfer amount in the fields.

## Application description
The application settings are stored in files in the resources fold

### `CardsRepository`
- stores card data of the `Card` class
- returns cards by card number or all card data

### `TransferController.transfer()`
- processes information on the `/transfer` branch
- checks the card data against the `CardsRepository
- checks if the transaction can be processed
- throw an error if the checks fail
- returns the class `OperationId` to the controller with the transaction number

### `TransferController.confirmOperation()`
- processes information on the `/confirmOperation` branch
- Checks the transaction code from the client against the data in the `TransactionRepository
- Checks the confirmation code
- if all data is correct, executes 


