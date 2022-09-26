package Util;

public class TestExtraiPalavra {

    public static void main(String[] args) {
        try {
            ExtraiPalavra palavras = new ExtraiPalavra("Textos/delim.txt", "Textos/Exemplo1.txt");
            palavras.converteTextoParaBinario();
            //System.out.println(palavras.convertePalavraParaBinario("solicitou"));

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
