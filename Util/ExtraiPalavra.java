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

    // coloca a palavra, ja em bcd, no formato de 16 caracteres
    public String setPalavra16caracteres(String palavra) {
        if (palavra.length() < 128) { // palavras < 16 : preenche com 0 ao final
            String aux = palavra.concat("0");
            return setPalavra16caracteres(aux);
        } else if (palavra.length() > 128) { // palavras> 16: remove os bits do final que excedem o limite de 128 bits
            String aux = palavra.substring(0, 127);
            return aux;
        } else {
            return palavra;
        }

    }

    // coloca todas as strings de bits de cada caracter no formato de 8 bits
    public String setCaracter8bits(String sequencia) {
        if (sequencia.length() < 8) {
            String aux = "0";
            aux = aux.concat(sequencia);
            return setCaracter8bits(aux);
        } else if (sequencia.length() > 8) {
            String aux = sequencia.substring(sequencia.length() - 8);
            // System.out.println(sequencia);
            return aux;
        } else {
            // System.out.println(sequencia);
            return sequencia;
        }

    }

    // Converte a palavra para codigo BCD para a insercao na arvore patricia
    public String convertePalavraParaBinario(String palavra) {
        int n = palavra.length();
        String palavraEmBinario = "";
        palavra = palavra.toLowerCase();
        byte[] charsPalavra = new byte[n];
        palavra.getBytes(0, n, charsPalavra, 0);

        for (byte b : charsPalavra) {
            // System.out.println(b);
            // transforma todos os caracteres em sequencias de 8 bits antes de formar a
            // palavra
            String caracterEm8bits = setCaracter8bits(Integer.toBinaryString(b));
            palavraEmBinario += caracterEm8bits;
        }
        // metodo para colocar todas as palavras no formato de 16 caracteres
        palavraEmBinario = setPalavra16caracteres(palavraEmBinario);
        return palavraEmBinario;
    }

    public void converteTextoParaBinario() {
        try {
            String palavra = null, palavraBinaria = null;
            int i = 1;
            while ((palavra = proximaPalavra()) != null) {
                // System.out.println("Palavra" + (i) + ": " + palavra);
                if (palavra.equals("")) {
                    continue;
                }

                palavraBinaria = convertePalavraParaBinario(palavra);

                if (mapaTexto.get(palavraBinaria) != null) {
                    mapaTexto.get(palavraBinaria).addListaOcorrencia(i);
                } else {
                    mapaTexto.put(palavraBinaria, new ItemPatricia(i, palavra));
                }
                i++;
            }

            // System.out.println(mapaTexto.get("0100000101000010").getListaOcorrencia());

            fecharArquivos();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    // Metodo para testar a conscitencia da extracao de palavreas do texto
    public void buscaPalavra(String palavraString) {
        String palavraBCD = this.convertePalavraParaBinario(palavraString);

        if (mapaTexto.get(palavraBCD) == null) {
            System.out.println("palavra não encontrada no texto");
        } else {
            System.out.println("palavra encontrada no texto");
            System.out.println(mapaTexto.get(palavraBCD).getListaOcorrencia());
        }
    }

    public void fecharArquivos() throws Exception {
        this.arqDelim.close();
        this.arqTxt.close();
    }

    public HashMap<String, ItemPatricia> getMapaTexto() {
        return this.mapaTexto;
    }

}
