package com.winicios.dev.din.card;

import com.winicios.dev.din.bank.Bank;
import com.winicios.dev.din.users.User;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Table(name = "card")
@Entity(name = "card")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_id")
    private Bank bank;

    @Column(name = "name")
    private String name;

    @Column(name = "credit_limit")
    private BigDecimal creditLimit;

    @Column(name = "closing_day")
    private Integer closingDay;

    @Column(name = "due_day")
    private Integer dueDay;
}