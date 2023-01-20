package entities;


import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Loan {
    private long id;
    private LocalDate initialDate;
    private LocalDate terminalDate;
    private double amount;
    private double interest;
    private Client client;
}
