package com.winicios.dev.din.expenses;

import com.winicios.dev.din.bank.dto.BankSummaryDTO;
import com.winicios.dev.din.card.Card;
import com.winicios.dev.din.expenses.dto.MonthlySummaryDTO;
import com.winicios.dev.din.spendingCategory.dto.CategorySummaryDTO;
import com.winicios.dev.din.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExpenseRepository extends JpaRepository<Expense, String> {
    List<Expense> findAllByCard(Card card);

    @Query("SELECT new com.winicios.dev.din.spendingCategory.dto.CategorySummaryDTO(cat.name, SUM(e.amount))" +
            "FROM expense e " +
            "JOIN e.category cat " +
            "JOIN e.card c " +
            "WHERE c.user = :user " +
            "AND EXTRACT(MONTH FROM e.purchaseDate) = :month " +
            "AND EXTRACT(YEAR FROM e.purchaseDate) = :year " +
            "GROUP BY cat.name")
    List<CategorySummaryDTO> getSummaryByCategory(@Param("user") User user, @Param("month") int month, @Param("year") int year);

    @Query("SELECT new com.winicios.dev.din.bank.dto.BankSummaryDTO(b.name, b.hexadecimalColor, SUM(e.amount)) " +
            "FROM expense e " +
            "JOIN e.card c " +
            "JOIN c.bank b " +
            "WHERE c.user = :user " +
            "AND EXTRACT(MONTH FROM e.purchaseDate) = :month " +
            "AND EXTRACT(YEAR FROM e.purchaseDate) = :year " +
            "GROUP BY b.name, b.hexadecimalColor")
    List<BankSummaryDTO> getSummaryByBank(@Param("user") User user, @Param("month") int month, @Param("year") int year);

    @Query("SELECT new com.winicios.dev.din.expenses.dto.MonthlySummaryDTO(:month, :year, COALESCE(SUM(e.amount), 0)) " +
            "FROM expense e " +
            "JOIN e.card c " +
            "WHERE c.user = :user " +
            "AND EXTRACT(MONTH FROM e.purchaseDate) = :month " +
            "AND EXTRACT(YEAR FROM e.purchaseDate) = :year")
    MonthlySummaryDTO getTotalMonthlySummary(@Param("user") User user, @Param("month") int month, @Param("year") int year);

}