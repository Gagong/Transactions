package org.io.transactions.entity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import org.io.transactions.enums.BlockType;
import io.swagger.v3.oas.annotations.media.Schema;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
@Getter
@Setter
@Builder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "Сущность клиента")
public class Client implements Serializable {

	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column
	@Schema(description = "Идентификатор клиента")
	private Long id;

	@Schema(description = "ФИО клиента")
	@Column(nullable = false)
	private String name;

	@Schema(description = "Возраст клиента")
	@Column(nullable = false)
	private Integer age;

	@Schema(description = "Дата рождения клиента")
	@Column(nullable = false)
	private Date birthday;

	@Schema(description = "Номер документа клиента, удостоверяющий личность")
	@Column
	private String documentNumber;

	@Schema(description = "Блокировка клиента")
	@Column(nullable = false)
	@Default
	private Boolean blocked = false;

	@Schema(description = "Тип блокировки клиента")
	@Column
	@Enumerated(EnumType.STRING)
	private BlockType blockType;

}
