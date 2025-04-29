package com.springboot.financialplanning.service;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.springboot.financialplanning.exception.InvalidIdException;
import com.springboot.financialplanning.model.Withdraw;
import com.springboot.financialplanning.repository.WithdrawRepository;

@Service
public class WithdrawService {
	
	@Autowired
	private WithdrawRepository withdrawRepository;


	public Withdraw save(Withdraw withdraw) {
		return withdrawRepository.save(withdraw);
		
	}

	public List<Withdraw> getAllWithdraw(Pageable pageable) {
		return withdrawRepository.findAll();
	}

	public List<Withdraw> getAllWithdrawinThematic(Pageable pageable) {
		return withdrawRepository.findAll();
	}


	
	public List<Withdraw> getWithdrawalsByInvestorId(int investorId) throws InvalidIdException {
        // Fetch withdrawals associated with the investor ID from the repository
        List<Withdraw> withdrawals = withdrawRepository.findByInvestorId(investorId);
        
        // Check if withdrawals are found
        if (withdrawals.isEmpty()) {
            throw new InvalidIdException("No withdrawals found for investor id: " + investorId);
        } else {
            return withdrawals;
        }
    }


	public List<Withdraw> getWithdrawalsByInvestorIdAndDateOfInvestment(int investorId, LocalDate dateOfInvestment) throws InvalidIdException {
	    // Fetch withdrawals associated with the investor ID and date of investment from the repository
	    List<Withdraw> withdrawals = withdrawRepository.findByInvestorIdAndInvestorMutualFundInvestmentDate(investorId, dateOfInvestment);
	    
	    // Check if withdrawals are found
	    if (withdrawals.isEmpty()) {
	        throw new InvalidIdException("No withdrawals found for investor id " + investorId + " on " + dateOfInvestment);
	    } else {
	        return withdrawals;
	    }
	}



}
