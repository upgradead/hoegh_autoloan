package autoliners.hoegh.autoloan.controller;

import autoliners.hoegh.autoloan.exception.LoanValidationException;
import autoliners.hoegh.autoloan.model.Loan;
import autoliners.hoegh.autoloan.model.TotalInterest;
import autoliners.hoegh.autoloan.service.LoanService;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
@Slf4j
class LoanController {

    private LoanService loanService;

    @Autowired
    public LoanController(LoanService loanService) {
        this.loanService = loanService;
    }

    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success", response = TotalInterest.class),
            @ApiResponse(code = 400, message = "Invalid amount")})
    @PostMapping(value = "loan",
            produces = {MediaType.APPLICATION_JSON_VALUE},
            consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<TotalInterest> getMonthlyAmortization(@ApiParam(value = "", required = true) @Valid @RequestBody Loan loanRequest) {

        log.info("Get monthly amortization {}" , loanRequest);

        if (loanRequest.getMortgage() == 0) { // Sample validation
            throw new LoanValidationException("Mortgage empty");
        }
        return ResponseEntity.status(HttpStatus.OK).body(loanService.getMonthlyAmortization(loanRequest));
    }
}