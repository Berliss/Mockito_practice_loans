package services;

import entities.Client;
import entities.Loan;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import repositories.ILoanRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class LoanServiceTest {
    @Mock
    private ILoanRepository loanRepository;
    @InjectMocks
    private LoanService loanService;
    private List<Loan> loans;

    @BeforeEach
    void setUp() {
        loans = MockData.getLoans();
    }

    @Test
    @DisplayName("Test 'getLoansByClientId', expected -> a non-empty list of loans that belongs to the specified client id")
    void getLoansByClientId() {
        //given
        when(loanRepository.findLoans()).thenReturn(loans);
        long id = 1;

        //when
        List<Loan> loans = loanService.getLoansByClientId(id);

        //then
        assertNotNull(loans);
        assertEquals(2, loans.size());
        assertEquals(1,loans.get(0).getClient().getId());
        assertEquals(1,loans.get(1).getClient().getId());
    }

    @Test
    @DisplayName("Test 'getLoans', expected -> a non-empty list of loans")
    void getLoans() {
        //given
        when(loanRepository.findLoans()).thenReturn(loans);

        //when
        List<Loan> loans = loanService.getLoans();

        //then
        assertNotNull(loans);
        assertTrue(loans.size() > 0);
    }

    @Test
    @DisplayName("Test 'getLoansWithAmountGreaterThan', expected -> a non-empty list of loans with the amount greater or equal than the given amount")
    void getLoansWithAmountGreaterThan() {
        //given
        when(loanRepository.findLoans()).thenReturn(loans);
        double amount = 20_000;

        //when
        List<Loan> loans = loanService.getLoansWithAmountGreaterThan(amount);

        //then
        assertNotNull(loans);
        assertEquals(0, loans.stream().filter(loan -> loan.getAmount() < amount).toList().size());
        assertNotEquals(0, loans.stream().filter(loan -> loan.getAmount() >= amount).toList().size());
    }

    @Test
    @DisplayName("Test 'getLoansWithAmountGreaterThan' when there is not a loan with a amount greater or equal than the specified amount, expected -> a empty list of loans ")
    void getLoansWithAmountGreaterThan1() {
        //given
        when(loanRepository.findLoans()).thenReturn(loans);
        double amount = 30_000;

        //when
        List<Loan> loans = loanService.getLoansWithAmountGreaterThan(amount);

        //then
        assertNotNull(loans);
        assertEquals(0, loans.size());
    }

    @Test
    @DisplayName("Test 'createLoan', expected -> a loan saved with its autoincrement id assigned and a 10% interest applied")
    void createLoan() {
        //given
        Client client = loans.get(0).getClient();
        double amount = 10_000;

        LocalDate initialDate = LocalDate.of(2023, 1, 1);
        LocalDate terminalDate = LocalDate.of(2023, 1, 16);

        when(loanRepository.saveLoan(any(Loan.class))).then(invocationOnMock -> {
            Loan loan = invocationOnMock.getArgument(0);
            loan.setId(1);
            return loan;
        });

        //when
        Loan loan = loanService.createLoan(client, initialDate, terminalDate, amount);
        var daysBetween = initialDate.until(terminalDate, ChronoUnit.DAYS);

        //then
        assertNotNull(loan);
        assertNotNull(loan.getClient());
        assertEquals(1, loan.getId());
        assertEquals(10, loan.getInterest());
        assertEquals(10_000, loan.getAmount());
        assertEquals(1, client.getId());
        assertEquals("berlis rodriguez", client.getName());
        assertEquals("402-9393932-1", client.getDni());
        assertEquals(15, daysBetween);
    }

    @Test
    @DisplayName("Test 'createLoan' when the initial date is not before terminalDate, expected -> throws RunTimeException")
    void createLoan1() {
        //given
        Client client = loans.get(0).getClient();
        double amount = 10_000;

        LocalDate initialDate = LocalDate.of(2023, 1, 30);
        LocalDate terminalDate = LocalDate.of(2023, 1, 16);

        //when
        //then
        assertThrows(RuntimeException.class, () -> loanService.createLoan(client, initialDate, terminalDate, amount));

        verify(loanRepository, never()).saveLoan(any(Loan.class));
    }
}