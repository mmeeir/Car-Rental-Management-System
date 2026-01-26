package repositories;

import models.Customer;

public interface CustomerRepository {
    // Тот самый метод, которого не хватает программе
    int createCustomerAndGetId(String name);
}
