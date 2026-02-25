CREATE TABLE expense (

id TEXT PRIMARY KEY,
card_id TEXT NOT NULL,
category_id TEXT NOT NULL,
description TEXT NOT NULL,
amount NUMERIC(15,2) NOT NULL,
installments INTEGER DEFAULT 1,
current_installment INTEGER DEFAULT 1,
status VARCHAR(20) NOT NULL,
purchase_date DATE NOT NULL,
CONSTRAINT fk_expense_card FOREIGN KEY (card_id) REFERENCES card (id),
CONSTRAINT fk_expense_category FOREIGN KEY (category_id) REFERENCES spending_category (id)

);