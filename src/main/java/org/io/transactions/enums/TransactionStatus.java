package org.io.transactions.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TransactionStatus {

	BLOCKED("Заблокирована"),
	APPROVED("Подтверждена");

	private final String value;

}
