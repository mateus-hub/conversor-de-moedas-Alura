package model;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class ConsultaApi {
	public Moeda buscarMoeda(String moedaBase, String moedaConvertir, double valor) {
		URI direcao = URI.create("https://v6.exchangerate-api.com/v6/4c200819a77b1e31d39bd486/pair/" + moedaBase + "/"
				+ moedaConvertir + "/" + valor);

		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(direcao).build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			return new Gson().fromJson(response.body(), Moeda.class);
		} catch (Exception e) {
			throw new RuntimeException("Não encontrou a moeda!");
		}
	}
}