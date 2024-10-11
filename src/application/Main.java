package application;

import java.io.IOException;
import java.util.Scanner;

import model.ConsultaApi;
import model.GeradorDeArquivos;
import model.Moeda;

public class Main {
	public static void main(String[] args) throws RuntimeException, IOException {

		Scanner sc = new Scanner(System.in);
		ConsultaApi consulta = new ConsultaApi();
		String moedaBase = " ";
		String moedaConvertir = "";
		double valor = 0;

		boolean continuar;

		while (true) {
			System.out.println("""
					*****************************************************
					Bem vindo(a) ao conversor de moedas
					
					Escolha uma opcao:

					1) Dolar =>> Peso argentino
					2) Peso argentino =>> Dolar
					3) Dolar =>> Real brasileiro
					4) Real brasileiro =>> Dolar
					5) Dolar =>> Peso colombiano
					6) Peso colombiano =>> Dolar
					7) Sair
					*****************************************************""");

			do {
				continuar = false;
				System.out.println("Escolha uma opcao valida: ");
				int opcao = sc.nextInt();

				switch (opcao) {
				case 1 -> {
					moedaBase = "USD";
					moedaConvertir = "ARS";
				}
				case 2 -> {
					moedaBase = "ARS";
					moedaConvertir = "USD";
				}
				case 3 -> {
					moedaBase = "USD";
					moedaConvertir = "BRL";
				}
				case 4 -> {
					moedaBase = "BRL";
					moedaConvertir = "USD";
				}

				case 5 -> {
					moedaBase = "USD";
					moedaConvertir = "COP";
				}
				case 6 -> {
					moedaBase = "COP";
					moedaConvertir = "USD";
				}
				case 7 -> {
					System.out.println("Saindo do programa.");
					return;
				}
				default -> {
					System.out.println("Opcao invalida. Por favor, escolha uma opcao valida.");
					continuar = true;
					break;
				}
				}
			} while (continuar);

			System.out.println("Convertendo " + moedaBase + " para " + moedaConvertir);

			while (valor <= 0) {
				System.out.println("Digite o valor que deseja converter: ");
				valor = sc.nextInt();

				if (valor <= 0) {
					System.out.println("Por favor, digite um valor maior que 0.");
				}
			}

			try {
				Moeda moeda;
				moeda = consulta.buscarMoeda(moedaBase, moedaConvertir, valor);
				System.out.println("********************************************************************************");
				System.out.println("Converter com o valor de mercado de: " + moeda.conversion_rate() + " " + moedaConvertir);
				System.out.println("O valor de " + valor + " " + moedaBase + " equivale a "
						+ moeda.conversion_result() + " " + moedaConvertir);

				GeradorDeArquivos gerarArquivos = new GeradorDeArquivos();
				gerarArquivos.guardarJson(moeda, valor);

			} catch (NumberFormatException e) {
				System.out.println("Numero nao encontrado " + e.getMessage());
			}
		}
	}
}