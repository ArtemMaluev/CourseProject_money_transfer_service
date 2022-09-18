package maluevartem.moneytransferservice.dao;

import maluevartem.moneytransferservice.model.Card;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

public class DatabaseCards {
    private final Map<String, Card> listCards;

    public DatabaseCards() {
        listCards = new ConcurrentHashMap<>();
        Properties properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        addCard(properties);
    }

    public Map<String, Card> getListCards() {
        return listCards;
    }

    private void addCard(Properties properties) {
        String cardCounter = properties.getProperty("NUMBER_REG_CARDHOLDER");
        for (int i = 1; i <= Integer.parseInt(cardCounter); i++) {
            listCards.put(properties.getProperty("CARD_NUMBER_" + i), new Card(
                    properties.getProperty("CARD_NUMBER_" + i),
                    properties.getProperty("CARD_VALID_TILL_" + i),
                    properties.getProperty("CARD_CVV_" + i),
                    Integer.parseInt(properties.getProperty("CARD_ACCOUNT_" + i)),
                    properties.getProperty("CURRENCY_" + i)
            ));
        }

    }
}
