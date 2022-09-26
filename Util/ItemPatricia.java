package Util;

import java.util.ArrayList;

public class ItemPatricia {

    private ArrayList<Integer> listaOcorrencia;

    // Lista com todas as ocorrencias
    public ItemPatricia(int posicao) {
        this.listaOcorrencia = new ArrayList<Integer>();
        this.listaOcorrencia.add(posicao);
    }

    public void addListaOcorrencia(int posicao) {
        if (posicao >= 0) {
            this.listaOcorrencia.add(posicao);
        }
    }

    public ArrayList<Integer> getListaOcorrencia() {

        System.out.println("A palavra pesquisada tem ocorrencia na(s) posicao(oes): ");
        for (Integer i : listaOcorrencia) {
            System.out.println(i);
        }

        return listaOcorrencia;
    }
}
