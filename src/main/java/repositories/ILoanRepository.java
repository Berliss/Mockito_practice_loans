package repositories;

import entities.Loan;

import java.util.List;

public interface ILoanRepository {
    List<Loan> findLoans();
    Loan saveLoan(Loan loan);
}
