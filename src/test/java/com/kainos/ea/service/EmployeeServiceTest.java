package com.kainos.ea.service;

import com.kainos.ea.dao.EmployeeDao;
import com.kainos.ea.exception.DatabaseConnectionException;
import com.kainos.ea.exception.UserDoesNotExistException;
import com.kainos.ea.model.Employee;
import com.kainos.ea.model.EmployeeRequest;
import com.kainos.ea.util.DatabaseConnector;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    EmployeeDao employeeDao = Mockito.mock(EmployeeDao.class);
    DatabaseConnector databaseConnector = Mockito.mock(DatabaseConnector.class);

    EmployeeService employeeService = new EmployeeService(employeeDao, databaseConnector);

    EmployeeRequest employeeRequest = new EmployeeRequest(
            30000,
            "Tim",
            "Bloggs",
            "tbloggs@email.com",
            "1 Main Street",
            "Main Road",
            "Belfast",
            "Antrim",
            "BT99BT",
            "Northern Ireland",
            "12345678901",
            "12345678",
            "AA1A11AA"
    );

    Employee employee = new Employee(
            30000,
            "Tim",
            "Bloggs",
            "tbloggs@email.com",
            "1 Main Street",
            "Main Road",
            "Belfast",
            "Antrim",
            "BT99BT",
            "Northern Ireland",
            "12345678901",
            "12345678",
            "AA1A11AA"
    );

    Connection conn;

    @Test
    void insertEmployee_shouldReturnId_whenDaoReturnsId() throws DatabaseConnectionException, SQLException {
        int expectedResult = 1;
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenReturn(expectedResult);

        int result = employeeService.insertEmployee(employeeRequest);

        assertEquals(result, expectedResult);
    }

    @Test
    void insertEmployee_shouldThrowSqlException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.insertEmployee(employeeRequest, conn)).thenThrow(SQLException.class);

        assertThrows(SQLException.class,
                () -> employeeService.insertEmployee(employeeRequest));
    }

    /*
    Mocking Exercise 1

    Write a unit test for the getEmployee method

    When the dao throws a SQLException

    Expect SQLException to be thrown

    This should pass without code changes
     */
    @Test
    void getEmployee_shouldThrowSQLException_whenDaoThrowsSqlException() throws SQLException, DatabaseConnectionException{
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenThrow(SQLException.class);
        assertThrows(SQLException.class, () -> employeeService.getEmployee(1));
    }

    /*
    Mocking Exercise 2

    Write a unit test for the getEmployee method

    When the dao returns an id

    Expect the id to be returned

    This should pass without code changes
     */
    @Test
    void getEmployee_shouldReturnID_whenDaoreturnsID() throws SQLException, DatabaseConnectionException, UserDoesNotExistException {
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(employee);
        assertEquals(employee, employeeService.getEmployee(1));
    }
    /*
    Mocking Exercise 3

    Write a unit test for the getEmployee method

    When the id parameter is null

    Expect the dao not to be called

    This should fail, make code changes to make this test pass
     */
    /*
    @Test
    void getEmployee_shouldNotCallDAO_whenIDisNull() throws SQLException, DatabaseConnectionException{
        Mockito.when(databaseConnector.getConnection()).thenThrow(new SQLException());
        assertDoesNotThrow(() -> employeeService.getEmployee(null));
    }*/
    /*
    Mocking Exercise 4

    Write a unit test for the getEmployee method

    When the dao returns null

    Expect UserDoesNotExistException to be thrown

    This should fail, make code changes to make this test pass
     */
    @Test
    void getEmployee_shouldReturnUserDoesNotExistException_whenNullUserIsReturned() throws SQLException, DatabaseConnectionException{
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployee(1, conn)).thenReturn(null);
        assertThrows(UserDoesNotExistException.class, () -> employeeService.getEmployee(1));
    }
    /*
    Mocking Exercise 5

    Write a unit test for the getEmployees method

    When the dao returns a list of employees

    Expect the list of employees to be returned

    This should pass without code changes
     */
    @Test
    void getEmployees_shouldReturnListOfEmployees_whenDAOreturnsListOfEmployees() throws SQLException, DatabaseConnectionException{
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        List<Employee> employeeList = List.of(employee);
        Mockito.when(employeeDao.getEmployees(conn)).thenReturn(employeeList);
        assertEquals(employeeList, employeeService.getEmployees());
    }
    /*
    Mocking Exercise 6

    Write a unit test for the getEmployees method

    When the dao throws a SQLException

    Expect SQLException to be thrown

    This should pass without code changes
     */
    @Test
    void getEmployees_shouldThrowSQLException_whenDAOreturnsThrowsSQLException() throws SQLException, DatabaseConnectionException{
        Mockito.when(databaseConnector.getConnection()).thenReturn(conn);
        Mockito.when(employeeDao.getEmployees(conn)).thenThrow(new SQLException());
        assertThrows(SQLException.class, () -> employeeService.getEmployees());
    }
}