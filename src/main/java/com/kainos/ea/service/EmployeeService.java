package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.UserDoesNotExistException;
import com.kainos.ea.model.Employee;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.util.DatabaseConnector;

import java.sql.SQLException;
import java.util.List;

public class EmployeeService {
    public EmployeeDao employeeDao;
    public DatabaseConnector databaseConnector;

    public EmployeeService(EmployeeDao employeeDao, DatabaseConnector databaseConnector) {
        this.employeeDao = employeeDao;
        this.databaseConnector = databaseConnector;
    }

    public int insertEmployee(EmployeeRequest employee) throws DatabaseConnectionException, SQLException {
        return employeeDao.insertEmployee(employee, databaseConnector.getConnection());
    }

    public Employee getEmployee(int employeeId) throws DatabaseConnectionException, SQLException, UserDoesNotExistException {
        Employee employee =  employeeDao.getEmployee(employeeId, databaseConnector.getConnection());
        if (employee == null){
            throw new UserDoesNotExistException();
        }
        return employee;
    }

    public List<Employee> getEmployees() throws DatabaseConnectionException, SQLException {
        return employeeDao.getEmployees(databaseConnector.getConnection());
    }
}
