package org.usra.loanService.controller;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.usra.loanService.dto.LoanApplicationResponse;
import org.usra.loanService.dto.LoanFormRequest;
import org.usra.loanService.dto.LoanProcessedStatus;
import org.usra.loanService.service.CustomerLoanService;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/loan")
public class CustomerController {

    private final CustomerLoanService customerLoanService;

    @GetMapping(value = "/welcome")
    public String get(){
        return "welcome";
    }

    @PostMapping(value = "/apply", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanApplicationResponse> apply(@RequestBody LoanFormRequest loanFormRequest){
        log.info("CustomerController::apply - request for loan processing. ");
        String status = customerLoanService.processLoanApplication(loanFormRequest);
        return ResponseEntity.ok(LoanApplicationResponse.builder().status(status).build());
    }

    @GetMapping(value = "/getStatus/{quoteId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<LoanProcessedStatus> getLoanStatus(@PathVariable String quoteId){
        log.info("CustomerController::getLoanStatus - request received for quoteId: {} ", quoteId);
        String status = customerLoanService.getLoanStatus(quoteId);
        log.info("CustomerController::getLoanStatus::LoanProcessedStatus - response is: {} ", status);
        return ResponseEntity.ok(LoanProcessedStatus.builder().status(status).build());
    }
}
