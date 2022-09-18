package maluevartem.moneytransferservice.model;

import java.util.Objects;

public class Card {

    private final String cardNumber;
    private final String cardValid;
    private final String cardCVV;
    private final int account;
    private final String currency;

    public Card(String cardNumber, String cardValid, String cardCVV, int account, String currency) {
        this.cardNumber = cardNumber;
        this.cardValid = cardValid;
        this.cardCVV = cardCVV;
        this.account = account;
        this.currency = currency;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardValid() {
        return cardValid;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public int getAccount() {
        return account;
    }

    public String getCurrency() {
        return currency;
    }

    @Override
    public String toString() {
        return "Card{" +
                ", cardNumber='" + cardNumber + '\'' +
                ", cardValid='" + cardValid + '\'' +
                ", cardCVV='" + cardCVV + '\'' +
                ", account=" + account +
                ", currency='" + currency + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Card card = (Card) o;
        return account == card.account &&
                Objects.equals(cardNumber, card.cardNumber) &&
                Objects.equals(cardValid, card.cardValid) &&
                Objects.equals(cardCVV, card.cardCVV) &&
                Objects.equals(currency, card.currency);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cardNumber, cardValid, cardCVV, account, currency);
    }
}
