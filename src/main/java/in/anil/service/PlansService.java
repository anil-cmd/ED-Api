package in.anil.service;

public interface PlansService {
	
	boolean snapConditionCheck(Integer applicationNumber);
	
	boolean ccapConditionCheck(Integer applicationNumber);
	
	boolean medicaidConditionCheck(Integer applicationNumber);
	
	boolean medicareConditionCheck(Integer applicationNumber);
	
	boolean riwConditionCheck(Integer applicationNumber);

}
