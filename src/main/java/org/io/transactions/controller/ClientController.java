package org.io.transactions.controller;

import java.util.Locale;

import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.javafaker.Faker;
import org.io.transactions.entity.Client;
import org.io.transactions.enums.BlockType;
import org.io.transactions.repository.ClientRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("client")
@RequiredArgsConstructor
@Tag(name="Контроллер клиентов", description="Контроллер для блокировки, разблокировки, проверки блокировки, проверки типа блокировки и создания клиента")
public class ClientController {

	private final ClientRepository clientRepository;

	private final Faker faker = new Faker(new Locale("ru"));

	@Operation(
			summary = "Блокировка клиента",
			description = "Позволяет заблокировать клиента"
	)
	@Transactional
	@PatchMapping("block/{clientId}/{blockType}")
	public void block(@Parameter(description = "Идентификатор клиента") @PathVariable final Long clientId,
										@Parameter(description = "Тип блокировки") @PathVariable final BlockType blockType) {
		clientRepository.findById(clientId).ifPresentOrElse(client -> client.setBlocked(true).setBlockType(blockType), () -> {
			throw new RuntimeException("Клиент не найден");
		});
	}

	@Operation(
			summary = "Разблокировка клиента",
			description = "Позволяет разблокировать клиента"
	)
	@Transactional
	@PatchMapping("unblock/{clientId}")
	public void unblock(@Parameter(description = "Идентификатор клиента") @PathVariable final Long clientId) {
		clientRepository.findById(clientId).ifPresentOrElse(client -> client.setBlocked(false).setBlockType(null), () -> {
			throw new RuntimeException("Клиент не найден");
		});
	}

	@Operation(
			summary = "Проверка клиента",
			description = "Позволяет проверить клиента - заблокирован или нет"
	)
	@GetMapping("check/{clientId}")
	public String check(@Parameter(description = "Идентификатор клиента") @PathVariable final Long clientId) {
		final Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Клиент не найден"));
		if (client.getBlocked()) {
			return "Клиент заблокирован";
		} else {
			return "Клиент не заблокирован";
		}
	}

	@Operation(
			summary = "Получения типа блокировки клиента",
			description = "Позволяет получить информацию о типе блокировки клиента"
	)
	@GetMapping("getBlockType/{clientId}")
	public String getBlockType(@Parameter(description = "Идентификатор клиента") @PathVariable final Long clientId) {
		final Client client = clientRepository.findById(clientId).orElseThrow(() -> new RuntimeException("Клиент не найден"));
		if (client.getBlocked()) {
			return client.getBlockType().getValue();
		} else {
			return "Клиент не заблокирован";
		}
	}

	@Operation(
			summary = "Создание случайного клиента (для тестирования)",
			description = "Позволяет создать случайного клиента для тестирования"
	)
	@PostMapping("generateRandomClient")
	public void generateRandomClient() {
		clientRepository.save(
				Client.builder()
						.name(faker.name().fullName())
						.age(faker.random().nextInt(1, 100))
						.birthday(faker.date().birthday())
						.documentNumber(faker.finance().creditCard())
						.build()
		);
	}

}
