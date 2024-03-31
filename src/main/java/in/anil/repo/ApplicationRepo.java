package in.anil.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import in.anil.entity.CitizenApplicationDetails;
import in.anil.entity.CitizenEducationDetails;

public interface ApplicationRepo extends JpaRepository<CitizenApplicationDetails, Integer>{
	public CitizenApplicationDetails findByApplicationNumber(Integer applicationNumber);
}
