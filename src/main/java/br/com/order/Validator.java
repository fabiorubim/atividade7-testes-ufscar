package br.com.order;

import java.util.List;

public interface Validator {
	public List<String> validateBasicData(Order customer);
}
