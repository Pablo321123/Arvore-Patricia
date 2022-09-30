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

import Util.ExtraiPalavra;
import Util.ItemPatricia;

public class TestPatricia {
    public static void main(String[] args) {
        ArvorePatricia ap = new ArvorePatricia(128);

        /*char[] listaAlfabeto = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', };

        for (char c : listaAlfabeto) {
            ap.insere(c);
        }

        //ap.pesquisa('f');*/
        
        Scanner myObj = new Scanner(System.in);
        
        boolean continuaPesquisa = true;
        HashMap<String, ItemPatricia> mapaTexto = new HashMap<String, ItemPatricia>();    
        
        //while(continuaPesquisa) {
	        System.out.println("pesquisar palavra: ");
	
	        String palavra = myObj.nextLine();
	        
	        /*if(palavra.equals("0")){
	        	System.out.println("fim da pesquisa");
	        	break;
	        }*/
	        
	        try {
	        ExtraiPalavra palavras = new ExtraiPalavra("Textos/delim.txt", "Textos/Exemplo4.txt");
	        palavras.converteTextoParaBinario();
	        palavras.buscaPalavra(palavra);
	        
	        mapaTexto = palavras.getMapaTexto();
	        
	        } catch (Exception e) {
	        	//System.out.println(e.getMessage());
	        }
	        mapaTexto.forEach((k, v) -> System.out.println("conteudo: " + k));
	        // agora tem que inserir os bits na arvore
	        //mapaTexto.forEach((k, v) ->ap.insere(k));
	        
        //}
        
    }
}
