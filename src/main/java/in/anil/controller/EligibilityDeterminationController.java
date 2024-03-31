package in.anil.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import in.anil.service.PlansService;

@RestController
public class EligibilityDeterminationController {
	
	@Autowired
	private PlansService plansService;
	
	@GetMapping("/snap")
	public ResponseEntity<String> snapEligibility(Integer applicationNumber) {
		boolean isEligible = plansService.snapConditionCheck(applicationNumber);
		return (isEligible) ? new ResponseEntity<String>("your eligible for plan", HttpStatus.OK) : new ResponseEntity<String>("not eligible", HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping("/ccap")
	public ResponseEntity<String> ccapEligibility(Integer applicationNumber) {
		boolean isEligible = plansService.ccapConditionCheck(applicationNumber);
		return (isEligible) ? new ResponseEntity<String>("your eligible for plan", HttpStatus.OK) : new ResponseEntity<String>("not eligible", HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping("/medicaid")
	public ResponseEntity<String> medicaidEligibility(Integer applicationNumber) {
		boolean isEligible = plansService.medicaidConditionCheck(applicationNumber);
		return (isEligible) ? new ResponseEntity<String>("your eligible for plan", HttpStatus.OK) : new ResponseEntity<String>("not eligible", HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping("/medicare")
	public ResponseEntity<String> medicareEligibility(Integer applicationNumber) {
		boolean isEligible = plansService.medicareConditionCheck(applicationNumber);
		return (isEligible) ? new ResponseEntity<String>("your eligible for plan", HttpStatus.OK) : new ResponseEntity<String>("not eligible", HttpStatus.BAD_REQUEST);	
	}
	
	@GetMapping("/riw")
	public ResponseEntity<String> riwEligibility(Integer applicationNumber) {
		boolean isEligible = plansService.riwConditionCheck(applicationNumber);
		return (isEligible) ? new ResponseEntity<String>("your eligible for plan", HttpStatus.OK) : new ResponseEntity<String>("not eligible", HttpStatus.BAD_REQUEST);	
	}

}
