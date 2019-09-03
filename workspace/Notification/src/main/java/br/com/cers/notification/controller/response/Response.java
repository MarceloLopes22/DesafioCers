package br.com.cers.notification.controller.response;

import java.util.ArrayList;
import java.util.List;

public class Response<T> {
	
	private T dado;

	private List<String> erros;

	public T getDado() {
		return dado;
	}

	public void setDado(T dado) {
		this.dado = dado;
	}

	public List<String> getErros() {
		if (erros == null) {
			this.erros = new ArrayList<String>();
		}
		return erros;
	}

	public void setErros(List<String> erros) {
		this.erros = erros;
	}
}
