package maluevartem.moneytransferservice.repository;

import maluevartem.moneytransferservice.model.MoneyTransfer;
import maluevartem.moneytransferservice.model.Card;
import maluevartem.moneytransferservice.dao.DatabaseCards;
import maluevartem.moneytransferservice.logger.Logger;
import org.springframework.stereotype.Repository;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Repository
public class CardRepository {

    private final Logger logger = Logger.getLog();
    private final Properties properties;
    private static AtomicLong operationId;
    private final DatabaseCards databaseCards;
    private final String confirmationCode;

    public CardRepository() {
        databaseCards = new DatabaseCards();
        logger.log("База данных карт пользователей загружена");
        operationId = new AtomicLong();

        properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        confirmationCode = properties.getProperty("CODE");
    }

    public String getConfirmationCode() {
        return confirmationCode;
    }

    public String verificationCard(MoneyTransfer moneyTransfer) {
        Card cardSender;
        if (databaseCards.getListCards().containsKey(moneyTransfer.getCardFromNumber())) {
            cardSender = databaseCards.getListCards().get(moneyTransfer.getCardFromNumber());
            if (!databaseCards.getListCards().containsKey(moneyTransfer.getCardToNumber())) {
                logger.log("Номер карты получателя № " + moneyTransfer.getCardToNumber() + " введен НЕ верно!");
                return null;
            } else if (!moneyTransfer.getCardFromValidTill().equals(cardSender.getCardValid())) {
                logger.log("Дата действия карты отправителя: " + moneyTransfer.getCardFromValidTill() + " введена НЕ верно!");
                return null;
            } else if (!moneyTransfer.getCardFromCVV().equals(cardSender.getCardCVV())) {
                logger.log("Код CVV карты отправителя: " + moneyTransfer.getCardFromCVV() + " введен НЕ верно!");
                return null;
            } else if (!moneyTransfer.getAmount().getCurrency().equals(cardSender.getCurrency())) {
                logger.log("Значение валюты перевода: " + moneyTransfer.getAmount().getCurrency() + " введено НЕ верно!");
                return null;
            } else if (moneyTransfer.getAmount().getValue() > cardSender.getAccount()) {
                logger.log("Сумма перевода превышает сумму счета отправителя!");
                return null;
            } else {
                operationId.getAndIncrement();
                logger.log("Карта отправителя № " + moneyTransfer.getCardFromNumber() + " проверена");
                logger.log("Карта получателя № " + moneyTransfer.getCardToNumber() + " проверена");
                return "operationId: " + operationId + " has been verified";
            }
        } else {
            logger.log("Номер карты отправителя № " + moneyTransfer.getCardFromNumber() + " введен НЕ верно!");
            return null;
        }
    }

    public String confirmOperation(String code) {
        if (code.equals(confirmationCode)) {
            logger.log("Код потверждения операции введен верно");
            return "operationId: " + operationId + " is completed";
        }
        logger.log("Код потверждения операции введен НЕ верно!!!");
        return null;
    }
}
