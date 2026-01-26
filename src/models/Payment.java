package models;

import java.time.LocalDate;
public class Payment {
     private int rentId;
     private double amount;
     private LocalDate paymentDate;

     public Payment(int rentId, double amount, LocalDate paymentDate) {
          this.rentId = rentId;
          this.amount = amount;
          this.paymentDate = paymentDate;
     }
}
