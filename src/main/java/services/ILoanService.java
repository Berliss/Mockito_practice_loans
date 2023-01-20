package services;

import entities.Client;
import entities.Loan;

import java.time.LocalDate;
import java.util.List;

public interface ILoanService {

    List<Loan> getLoansByClientId(long id);
    List<Loan> getLoans();
    List<Loan> getLoansWithAmountGreaterThan(double amount);
    Loan createLoan(Client client, LocalDate initialDate, LocalDate terminalDate, double amount);

}
