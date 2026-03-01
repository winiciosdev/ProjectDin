package com.winicios.dev.din.expenses;

import com.winicios.dev.din.bank.dto.BankSummaryDTO;
import com.winicios.dev.din.card.Card;
import com.winicios.dev.din.card.CardRepository;
import com.winicios.dev.din.expenses.dto.ExpenseRequestDTO;
import com.winicios.dev.din.expenses.dto.ExpenseResponseDTO;
import com.winicios.dev.din.expenses.dto.MonthlySummaryDTO;
import com.winicios.dev.din.spendingCategory.SpendingCategory;
import com.winicios.dev.din.spendingCategory.SpendingCategoryRepository;
import com.winicios.dev.din.spendingCategory.dto.CategorySummaryDTO;
import com.winicios.dev.din.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final CardRepository cardRepository;
    private final SpendingCategoryRepository categoryRepository;

    public ExpenseService(ExpenseRepository expenseRepository, CardRepository cardRepository, SpendingCategoryRepository categoryRepository) {
        this.expenseRepository = expenseRepository;
        this.cardRepository = cardRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<ExpenseResponseDTO> create(ExpenseRequestDTO dto, User loggedUser) {

        Card card = cardRepository.findByIdAndUser(dto.cardId(), loggedUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Cartão não encontrado ou acesso negado."));

        SpendingCategory category = categoryRepository.findById(dto.categoryId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Categoria não encontrada."));

        List<Expense> createdExpenses = new ArrayList<>();

        int parcelas = dto.installments() != null && dto.installments() > 0 ? dto.installments() : 1;


        BigDecimal valorParcela = dto.amount().divide(new BigDecimal(parcelas), 2, RoundingMode.HALF_UP);

        for (int i = 1; i <= parcelas; i++) {
            Expense expense = new Expense();
            expense.setCard(card);
            expense.setCategory(category);
            expense.setDescription(parcelas > 1 ? dto.description() + " (" + i + "/" + parcelas + ")" : dto.description());
            expense.setAmount(valorParcela);
            expense.setInstallments(parcelas);
            expense.setCurrentInstallment(i);
            expense.setStatus(ExpenseStatus.PENDENTE);

            expense.setPurchaseDate(dto.purchaseDate().plusMonths(i - 1));

            createdExpenses.add(expense);
        }

        expenseRepository.saveAll(createdExpenses);

        return createdExpenses.stream().map(ExpenseResponseDTO::new).collect(Collectors.toList());
    }

    public List<ExpenseResponseDTO> findByCard(String cardId, User loggedUser) {

        Card card = cardRepository.findByIdAndUser(cardId, loggedUser)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.FORBIDDEN, "Acesso negado."));

        return expenseRepository.findAllByCard(card)
                .stream()
                .map(ExpenseResponseDTO::new)
                .collect(Collectors.toList());
    }

    public MonthlySummaryDTO calculateExpensesByMonth(int year, int month, User loggedUser){

        MonthlySummaryDTO response = expenseRepository.getTotalMonthlySummary(loggedUser, month, year);

        return response;

    }

    public List<BankSummaryDTO> calculateExpensesByMonthAndBank(int year, int month, User loggedUser) {

        List<BankSummaryDTO> response = expenseRepository.getSummaryByBank(loggedUser, month, year);

        return response;
    }

    public List<CategorySummaryDTO> calculateExpensesByMonthAndCategory(int year, int month, User loggedUser) {

        List<CategorySummaryDTO> response = expenseRepository.getSummaryByCategory(loggedUser, month, year);

        return response;
    }
}