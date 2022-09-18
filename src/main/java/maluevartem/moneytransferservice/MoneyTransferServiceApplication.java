package maluevartem.moneytransferservice;

import maluevartem.moneytransferservice.logger.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Приложение moneyTransferService - REST-сервис. Сервис предоставляет интерфейс для перевода денег с одной карты на
 * другую по заранее описанной спецификации. Приложение подключается веб-приложение (FRONT) и использует его функционал
 * для перевода денег.
 *
 * @author Maluev Artem
 */
@SpringBootApplication
public class MoneyTransferServiceApplication {

    private static final Logger logger = Logger.getLog();

    public static void main(String[] args) {
        logger.log("Star server \"MoneyTransferServiceApplication\"");
        SpringApplication.run(MoneyTransferServiceApplication.class, args);

    }
}
