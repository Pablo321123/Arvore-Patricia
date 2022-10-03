package Util;

import java.util.ArrayList;

public class ItemPatricia {

    private ArrayList<Integer> listaOcorrencia;
    private String chave = "";

    // Lista com todas as ocorrencias
    public ItemPatricia(int posicao, String chave) {
        this.listaOcorrencia = new ArrayList<Integer>();
        this.listaOcorrencia.add(posicao);
        this.chave = chave;
    }

    public void addListaOcorrencia(int posicao) {
        if (posicao >= 0) {
            this.listaOcorrencia.add(posicao);
        }
    }

    public ArrayList<Integer> getListaOcorrencia() {
        return listaOcorrencia;
    }

    public String getChave() {
        return chave;
    }
}
