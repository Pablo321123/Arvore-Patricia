/* --------------------------------------------------------------+
 * Trabalho de AEDS II - Implementação da Arvore Patricia
 * --------------------------------------------------------------+ */
/**
 * @author
 *         Pablo Vasconcelos da Cruz
 * @author
 *         Stéphanie Magalhães;
 **/
// --------------------------------------------------------------+

import java.util.HashMap;
import java.util.Scanner;

import Util.Cores;
import Util.ExtraiPalavra;
import Util.ItemPatricia;

public class TestPatricia {

	private static final int NBITSCHAVE = 128;

	private static void realizarPesquisa(int nBitsChave, String palavra) {

		ArvorePatriciaString ap = new ArvorePatriciaString(nBitsChave);
		HashMap<String, ItemPatricia> mapaTexto = new HashMap<String, ItemPatricia>();

		try {
			ExtraiPalavra ep = new ExtraiPalavra("Textos/delim.txt", "Textos/Exemplo2.txt");
			ep.converteTextoParaBinario();
			// ep.buscaPalavra(palavra);

			// Hash Map contendo todas as palavras convertidas para serem inseridas na
			// arvore patricia
			mapaTexto = ep.getMapaTexto();

			// mapaTexto.forEach((k, v) -> System.out.println("conteudo: " + k));
			mapaTexto.forEach((k, v) -> ap.insere(k, v));

			ap.pesquisa(palavra, ep);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void main(String[] args) {

		Scanner myObj = new Scanner(System.in);

		boolean continuaPesquisa = true;

		while (continuaPesquisa) {
			System.out.println("\n------------------------------------------------------------------------\n");
			System.out.println(Cores.ANSI_CYAN + "Qual palavra voce deseja encontrar?" + Cores.ANSI_RESET);
			String palavra = myObj.nextLine();

			realizarPesquisa(NBITSCHAVE, palavra);

			System.out.println(Cores.ANSI_GREEN + "Deseja procurar outra palavra?\n" + Cores.ANSI_GREEN + "1 - Sim\n"
					+ Cores.ANSI_RED + "2 - Nao" + Cores.ANSI_RESET);

			continuaPesquisa = myObj.nextInt() == 1 ? true : false;

			myObj.nextLine();

		}

		myObj.close();

	}
}
