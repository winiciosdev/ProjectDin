package com.winicios.dev.din.expenses;

import com.winicios.dev.din.bank.dto.BankSummaryDTO;
import com.winicios.dev.din.expenses.dto.ExpenseRequestDTO;
import com.winicios.dev.din.expenses.dto.ExpenseResponseDTO;
import com.winicios.dev.din.expenses.dto.MonthlySummaryDTO;
import com.winicios.dev.din.spendingCategory.dto.CategorySummaryDTO;
import com.winicios.dev.din.users.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<List<ExpenseResponseDTO>> create(
            @RequestBody ExpenseRequestDTO dto,
            @AuthenticationPrincipal User loggedUser) {
        List<ExpenseResponseDTO> response = expenseService.create(dto, loggedUser);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/card/{cardId}")
    public ResponseEntity<List<ExpenseResponseDTO>> findByCard(
            @PathVariable String cardId,
            @AuthenticationPrincipal User loggedUser) {
        return ResponseEntity.ok(expenseService.findByCard(cardId, loggedUser));
    }

    @GetMapping("/summary/total/{month}/{year}")
    public ResponseEntity<MonthlySummaryDTO> calculateExpensesByMonth(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable int year,
            @PathVariable int month) {

        return ResponseEntity.ok(expenseService.calculateExpensesByMonth(year, month, loggedUser));
    }

    @GetMapping("/summary/banks/{month}/{year}")
    public ResponseEntity<List<BankSummaryDTO>> calculateExpensesByMonthAndBank(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable int year,
            @PathVariable int month) {

        List<BankSummaryDTO> response = expenseService.calculateExpensesByMonthAndBank(year, month, loggedUser);

        return ResponseEntity.ok(response);

    }

    @GetMapping("/summary/categories/{month}/{year}")
    public ResponseEntity<List<CategorySummaryDTO>> calculateExpensesByMonthAndCategory(
            @AuthenticationPrincipal User loggedUser,
            @PathVariable int year,
            @PathVariable int month) {

        List<CategorySummaryDTO> response = expenseService.calculateExpensesByMonthAndCategory(year, month, loggedUser);

        return ResponseEntity.ok(response);

    }
}