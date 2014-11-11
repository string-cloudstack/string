package com.pft.string.service.framework.core.types;

public enum ErrorLevel
{
	LOW(1), MEDIUM(2), HIGH(3);
	private int value;

	private ErrorLevel(int value) {
		this.value = value;
	}

	public int getIdForErrorLevel() {
		return value;
	}

	public static boolean contains(int errorLevelId) {

		for (ErrorLevel enumErrorLevel : ErrorLevel.values()) {

			if (enumErrorLevel.getIdForErrorLevel() == errorLevelId) {
				return true;
			}
		}

		return false;
	}
}
