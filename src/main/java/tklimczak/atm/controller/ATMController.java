package tklimczak.atm.controller;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import tklimczak.atm.exception.NotEnoughBalanceException;
import tklimczak.atm.service.ATMService;

@RestController
@RequestMapping("/atm")
public class ATMController {

	@Autowired
	private transient ATMService atmService;

	@CrossOrigin
	@GetMapping("/withdraw/{amount}")
	@PreAuthorize("hasRole('ACCOUNT')")
	ResponseEntity<Void> withdrawMoney(@PathVariable BigDecimal amount) throws NotFoundException {
		atmService.withdrawMoney(amount);
		return ResponseEntity.ok().build();
	}

	@CrossOrigin
	@GetMapping("/add/{amount}")
	@PreAuthorize("hasRole('ACCOUNT')")
	ResponseEntity<Void> addMoney(@PathVariable BigDecimal amount) throws NotFoundException {
		atmService.addMoney(amount);
		return ResponseEntity.ok().build();
	}

	@CrossOrigin
	@GetMapping("/balance")
	@PreAuthorize("hasRole('ACCOUNT')")
	ResponseEntity<BigDecimal> getBalance() throws NotFoundException {
		return new ResponseEntity<BigDecimal>(atmService.getBalance(), HttpStatus.OK);
	}
}
