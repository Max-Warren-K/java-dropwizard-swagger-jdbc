package com.kainos.ea.validator;

import com.kainos.ea.exception.*;
import com.kainos.ea.model.EmployeeRequest;

public class EmployeeValidator {
    public boolean isValidEmployee(EmployeeRequest employeeRequest) throws SalaryTooLowException, BankNumberLengthException, FNameLengthException, LNameLengthException, NinLengthException {
        if (employeeRequest.getSalary() < 20000) {
            throw new SalaryTooLowException();
        }

        if (employeeRequest.getBankNo().length() != 8) {
            throw new BankNumberLengthException();
        }
        if (employeeRequest.getFname().length() >50 && employeeRequest.getFname().length()<1){
            throw new FNameLengthException();
        }

        if (employeeRequest.getLname().length()>50 && employeeRequest.getLname().length()<1){
            throw new LNameLengthException();
        }

        if(employeeRequest.getNin().length()!=8){
            throw new NinLengthException();
        }
        return true;
    }
}
