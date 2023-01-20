package services;

import entities.Client;
import entities.Loan;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

public class MockData {
    public static List<Loan> getLoans() {
        Client berlis = new Client(1, "berlis rodriguez", "402-9393932-1");
        Client juan = new Client(2, "juan garcia", "402-55555555-1");
        Client pedro = new Client(3, "pedro hernandez", "402-55555555-1");

        return Arrays.asList(
                Loan.builder()
                        .id(1)
                        .client(berlis)
                        .amount(10_000)
                        .initialDate(LocalDate.of(2023,1,1))
                        .initialDate(LocalDate.of(2023,1,15))
                        .interest(10)
                        .build(),
                Loan.builder()
                        .id(1)
                        .client(berlis)
                        .amount(21_000)
                        .initialDate(LocalDate.of(2023,1,1))
                        .initialDate(LocalDate.of(2023,1,25))
                        .interest(20)
                        .build(),
                Loan.builder()
                        .id(1)
                        .client(juan)
                        .amount(15_000)
                        .initialDate(LocalDate.of(2023,1,1))
                        .initialDate(LocalDate.of(2023,1,15))
                        .interest(10)
                        .build(),
                Loan.builder()
                        .id(1)
                        .client(pedro)
                        .amount(12_000)
                        .initialDate(LocalDate.of(2023,1,1))
                        .initialDate(LocalDate.of(2023,2,28))
                        .interest(10)
                        .build()
        );

    }
}
