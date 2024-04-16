package org.io.transactions.entity;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.io.transactions.enums.TransactionStatus;

import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность абстрактной транзакции")
public class Transaction implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@Column
	@Schema(description = "Идентификатор транзакции")
	private Long id;

	@Column
	@Schema(description = "Имя транзакции")
	private String name;

	@Column
	@Schema(description = "Уникальный номер транзакции")
	private String number;

	@Column
	@Schema(description = "Сумма")
	private BigDecimal cost;

	@Schema(description = "Статуст транзакции")
	@Column
	@Enumerated(EnumType.STRING)
	@Default
	private TransactionStatus status = TransactionStatus.APPROVED;

	@Schema(description = "Клиент")
	@ManyToOne
	@JoinColumn(nullable = false)
	private Client client;

}