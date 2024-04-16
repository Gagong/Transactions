package org.io.transactions.dto;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import io.swagger.v3.oas.annotations.media.Schema;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "Абстрактные данные транзакции")
public class TransactionDTO implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Schema(description = "Имя транзакции")
	private String name;

	@Schema(description = "Идентификатор клиента")
	private Long clientId;

	@Schema(description = "Сумма")
	private BigDecimal cost;

}
