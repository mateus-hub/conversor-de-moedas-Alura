package model;

import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

public class GeradorDeArquivos {
	public void guardarJson(Moeda moeda, double valorConvertido) throws IOException {
		Gson gson = new GsonBuilder().setPrettyPrinting().create();
		String nomeArquivo = moeda.base_code() + "para" + moeda.target_code() + ".json";

		JsonObject jsonMoeda = gson.toJsonTree(moeda).getAsJsonObject();
		jsonMoeda.addProperty("valor_convertido", valorConvertido);

		try (FileWriter escrita = new FileWriter(nomeArquivo)) {
			escrita.write(gson.toJson(jsonMoeda));
		} catch (IOException e) {
			System.err.println("Erro ao guardar o arquivo: " + e.getMessage());
			throw e;
		}
	}
}