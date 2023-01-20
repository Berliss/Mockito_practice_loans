package services;

import entities.Client;
import entities.Loan;
import lombok.AllArgsConstructor;
import repositories.ILoanRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@AllArgsConstructor
public class LoanService implements ILoanService {

    private ILoanRepository loanRepository;

    @Override
    public List<Loan> getLoansByClientId(long id) {
        return loanRepository.findLoans()
                .stream()
                .filter(loan -> loan.getClient().getId() == id)
                .toList();
    }

    @Override
    public List<Loan> getLoans() {
        return loanRepository.findLoans();
    }

    @Override
    public List<Loan> getLoansWithAmountGreaterThan(double amount) {
        return loanRepository.findLoans()
                .stream()
                .filter(loan -> loan.getAmount() >= amount)
                .toList();
    }

    @Override
    public Loan createLoan(Client client, LocalDate initialDate, LocalDate terminalDate, double amount) {
        int daysBetween = (int) initialDate.until(terminalDate, ChronoUnit.DAYS);
        long interest = 0;

        if (daysBetween >= 0 && daysBetween <= 15) {
            interest = 10;
        } else if (daysBetween >= 16 && daysBetween <= 30) {
            interest = 20;
        } else if (daysBetween >= 31) {
            interest = 30;
        } else if (daysBetween < 0) {
            throw new RuntimeException("days between must be a positive number");
        }

        Loan loan = Loan.builder()
                .initialDate(initialDate)
                .terminalDate(terminalDate)
                .amount(amount)
                .interest(interest)
                .client(client)
                .build();

        return loanRepository.saveLoan(loan);
    }
}
