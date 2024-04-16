package org.io.transactions.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum BlockType {

	SWINDLER("Мошенник"),
	SUSPECT("Подозрительный клиент");

	private final String value;

}
