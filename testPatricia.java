/* --------------------------------------------------------------+
 * Trabalho de AEDS II - Implementação da Árvore Patricia
 * --------------------------------------------------------------+ */
/**
 * @author
 *         Pablo Vasconcelos da Cruz
 * @author
 *         Stéphanie Magalhães;
 **/
// --------------------------------------------------------------+

public class testPatricia {
    public static void main(String[] args) {
        ArvorePatricia ap = new ArvorePatricia(128);

        char[] listaAlfabeto = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', };

        for (char c : listaAlfabeto) {
            ap.insere(c);
        }

        ap.pesquisa('f');

    }
}
