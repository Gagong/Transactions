package org.io.transactions.controller;

import java.math.BigDecimal;
import java.util.Locale;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import org.io.transactions.dto.TransactionDTO;
import org.io.transactions.entity.Client;
import org.io.transactions.entity.Transaction;
import org.io.transactions.enums.TransactionStatus;
import org.io.transactions.repository.ClientRepository;
import org.io.transactions.repository.TransactionRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
@Tag(name="Контроллер транзакций", description="Контроллер для проведения транзакций")
public class TransactionController {

	private final TransactionRepository transactionRepository;

	private final ClientRepository clientRepository;

	private final Faker faker = new Faker(new Locale("ru"));

	@Operation(
			summary = "Проведение транзакции",
			description = "Проведение абстрактной транзакции"
	)
	@PostMapping("doTransaction")
	public Transaction doTransaction(@Parameter(description = "Абстрактная транзакция") @RequestBody final TransactionDTO transaction) {
		final Client client = clientRepository.findById(transaction.getClientId()).orElseThrow(() -> new RuntimeException("Клиент не найден"));
		final TransactionStatus status = client.getBlocked() ? TransactionStatus.BLOCKED : TransactionStatus.APPROVED;
		return transactionRepository.save(
				Transaction.builder()
						.name(faker.finance().iban())
						.number(faker.finance().creditCard())
						.cost(new BigDecimal(faker.commerce().price()))
						.status(status)
						.client(client)
						.build()
		);
	}

}
