package maluevartem.moneytransferservice.controller;

import maluevartem.moneytransferservice.model.MoneyTransfer;
import maluevartem.moneytransferservice.logger.Logger;
import maluevartem.moneytransferservice.service.MoneyTransferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:3000"})
public class MoneyTransferController {

    private final Logger logger = Logger.getLog();
    private final MoneyTransferService service;

    @Autowired
    public MoneyTransferController(MoneyTransferService service) {
        this.service = service;
    }

    @PostMapping("/transfer")
    public String verificationCard(@RequestBody MoneyTransfer body) {
        System.out.println(body.toString());
        logger.log("POST запрос с данными карт принят");
        return service.verificationCard(body);
    }

    @PostMapping("/confirmOperation")
    public String confirmOperation(@RequestBody String body) {
        System.out.println(body);
        String code = body.substring(body.indexOf(":") + 2, body.lastIndexOf("\""));
        logger.log("POST запрос с кодом подтверждения принят");
        return service.confirmOperation(code);
    }
}
