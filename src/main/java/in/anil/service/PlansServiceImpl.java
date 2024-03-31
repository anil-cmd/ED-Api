package in.anil.service;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.anil.entity.CitizenApplicationDetails;
import in.anil.entity.CitizenEducationDetails;
import in.anil.entity.CitizenIncomeDetails;
import in.anil.entity.CitizenKidsDetails;
import in.anil.repo.ApplicationRepo;
import in.anil.repo.EducationRepo;
import in.anil.repo.IncomeRepo;
import in.anil.repo.KidsRepo;

@Service
public class PlansServiceImpl implements PlansService{
	
	@Autowired
	private IncomeRepo incomeRepo;
	
	@Autowired
	private KidsRepo kidsRepo;
	
	@Autowired
	private EducationRepo educationRepo;
	
	@Autowired
	private ApplicationRepo applicationRepo;
	

	@Override
	public boolean snapConditionCheck(Integer applicationNumber) {
		if(applicationNumber != null) {
			CitizenIncomeDetails incomeDetails = incomeRepo.findByApplicationNumber(applicationNumber);
			return (incomeDetails.getSalaryIncome() <= 300) ? true : false;
		}
		return false;
	}

	@Override
	public boolean ccapConditionCheck(Integer applicationNumber) {

		if(applicationNumber != null) {
			CitizenIncomeDetails incomeDetails = incomeRepo.findByApplicationNumber(applicationNumber);
			List<CitizenKidsDetails> kidsDetails = kidsRepo.findByApplicationNumber(applicationNumber);
			long kidsCount = kidsDetails.stream().count();
			List<CitizenKidsDetails> eligibleKids = kidsDetails.stream().filter(kid -> checkKidsAgeEligibility(kid.getKidDob())).collect(Collectors.toList());
			
			return (incomeDetails.getSalaryIncome() <= 300 && kidsCount > 0 && eligibleKids.size() == kidsCount) ? true : false;
		}
		
		return false;
	}
	
	public boolean checkKidsAgeEligibility(String kidsDob) {
		String[] word = kidsDob.split("/");
		int day = Integer.parseInt(word[0]);
		int month = Integer.parseInt(word[1]);
		int year = Integer.parseInt(word[2]);
        LocalDate birthdate = LocalDate.of(year, month, day);
        int age = calculateAge(birthdate);
        return (age <= 16) ? true : false;
	}
	
	public boolean checkCitizenAgeEligibility(String citizenDob) {
		String[] word = citizenDob.split("/");
		int day = Integer.parseInt(word[0]);
		int month = Integer.parseInt(word[1]);
		int year = Integer.parseInt(word[2]);
        LocalDate birthdate = LocalDate.of(year, month, day);
        int age = calculateAge(birthdate);
        return (age >= 65) ? true : false;
	}
	
    public static int calculateAge(LocalDate birthdate) {
        LocalDate currentDate = LocalDate.now();
        return Period.between(birthdate, currentDate).getYears();
    }

	@Override
	public boolean medicaidConditionCheck(Integer applicationNumber) {
		if(applicationNumber != null) {
			CitizenIncomeDetails incomeDetails = incomeRepo.findByApplicationNumber(applicationNumber);
			return (incomeDetails.getSalaryIncome() <= 300 && incomeDetails.getPropertyIncome() == 0) ? true : false;
		}
		return false;
	}

	@Override
	public boolean medicareConditionCheck(Integer applicationNumber) {
		if(applicationNumber != null) {
			CitizenApplicationDetails citizenApplicationDetails = applicationRepo.findByApplicationNumber(applicationNumber);
			return (checkCitizenAgeEligibility(citizenApplicationDetails.getDob())) ? true : false;
		}
		return false;
	}

	@Override
	public boolean riwConditionCheck(Integer applicationNumber) {
		if(applicationNumber != null) {
			CitizenIncomeDetails incomeDetails = incomeRepo.findByApplicationNumber(applicationNumber);
			CitizenEducationDetails educationDetails = educationRepo.findByApplicationNumber(applicationNumber);
			return (incomeDetails.getSalaryIncome() == 0 && educationDetails.getGraduationYear().equals(null)) ? true : false;
		}
		return false;
	}


}
