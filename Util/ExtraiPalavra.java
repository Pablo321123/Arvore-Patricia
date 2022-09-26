package Util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class ExtraiPalavra {
    private BufferedReader arqDelim, arqTxt;
    private StringTokenizer palavras;
    private String delimitadores;
    private HashMap<String, ItemPatricia> mapaTexto;

    public ExtraiPalavra(String nomeArqDelim, String nomeArqTxt)
            throws Exception {
        this.arqDelim = new BufferedReader(new FileReader(nomeArqDelim));
        this.arqTxt = new BufferedReader(new FileReader(nomeArqTxt));
        // @{\it Os delimitadores devem estar juntos em uma \'unica linha do arquivo}@
        this.delimitadores = arqDelim.readLine() + "\r\n";
        this.palavras = null;
        mapaTexto = new HashMap<String, ItemPatricia>();

    }

    public String proximaPalavra() throws Exception {
        if (palavras == null || !palavras.hasMoreTokens()) {
            String linha = arqTxt.readLine();
            if (linha == null) {
                return null;
            }
            this.palavras = new StringTokenizer(linha, this.delimitadores);
            if (!palavras.hasMoreTokens())
                return ""; // @{\it ignora delimitadores}@
        }
        return this.palavras.nextToken();
    }

    public void fecharArquivos() throws Exception {
        this.arqDelim.close();
        this.arqTxt.close();
    }

    // Converte a palavra para codigo BCD para a insercao na arvore patricia
    public String convertePalavraParaBinario(String palavra) {
        int n = palavra.length();
        String palavraEmBinario = "";
        byte[] charsPalavra = new byte[n];
        palavra.getBytes(0, n, charsPalavra, 0);

        for (byte b : charsPalavra) {
            // System.out.println(b);
            palavraEmBinario += Integer.toBinaryString(b);
        }

        return palavraEmBinario;
    }

    public void converteTextoParaBinario() {
        try {
            String palavra = null, palavraBinaria = null;
            int i = 1;
            while ((palavra = proximaPalavra()) != null) {
                System.out.println("Palavra" + (i) + ": " + palavra);
                palavraBinaria = convertePalavraParaBinario(palavra);

                if (mapaTexto.get(palavraBinaria) != null) {
                    mapaTexto.get(palavraBinaria).addListaOcorrencia(i);
                } else {
                    mapaTexto.put(palavraBinaria, new ItemPatricia(i));
                }
                i++;
            }

            System.out.println(
                    mapaTexto.get("111001111011111101100110100111000111101001111010011011111110101")
                            .getListaOcorrencia());

            fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
