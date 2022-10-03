import Util.ExtraiPalavra;
import Util.ItemPatricia;
import Util.Cores;

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

public class ArvorePatriciaString {

    private PatNo raiz;
    private int nbitsChave;

    /**
     * @param nbitsChave - 128 bits para caracteres ASCII
     */
    public ArvorePatriciaString(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    // Retorna o i-ésimo bit da chave k a partir da esquerda
    private int bit(int i, String k) {
        if (i == 0) {
            return 0;
        }

        // Retorna o bit na posicao i (do mais significativo para o menos
        // significatrivo) *Esquerda -> Direita
        int bitDeComparacao = (k.charAt(i - 1) - 48);
        return bitDeComparacao;
    }

    // Verifica se p é nó externo
    private boolean eExterno(PatNo p) {
        Class classe = p.getClass();
        return classe.getName().equals(PatNoExt.class.getName());
    }

    // Metodo para criar No Interno
    private PatNo criaNoInt(int i, PatNo esq, PatNo dir) {
        PatNoInt p = new PatNoInt();
        p.index = i;
        p.esq = esq;
        p.dir = dir;
        return p;
    }

    // Método para criar nó externo
    private PatNo criaNoExt(String k, ItemPatricia ip) {
        PatNoExt p = new PatNoExt();
        p.chave_str = k;
        p.ip = ip;
        return p;
    }

    // Método para pesquisa:
    public void pesquisa(String k, ExtraiPalavra ep) {
        this.pesquisa(k, this.raiz, ep);
    }

    private void pesquisa(String k, PatNo t, ExtraiPalavra ep) {

        String k_convertido = ep.convertePalavraParaBinario(k);

        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave_str.equals(k_convertido)) {
                System.out.println("\n" + Cores.ANSI_YELLOW + "Elemento '" + Cores.ANSI_GREEN + aux.ip.getChave()
                        + Cores.ANSI_YELLOW + "' encontrado" + Cores.ANSI_RESET);

                System.out.println(Cores.ANSI_YELLOW + "Este elemento tem ocorrencia nas posicao(oes): " + Cores.ANSI_GREEN
                        + aux.ip.getListaOcorrencia() + Cores.ANSI_RESET + "\n");
            } else {
                System.out.println(Cores.ANSI_RED + "Elemento nao encontrado" + Cores.ANSI_RESET);
            }
        } else {
            PatNoInt aux = (PatNoInt) t;

            if (this.bit(aux.index, k_convertido) == 0) {
                pesquisa(k, aux.esq, ep);
            } else {
                pesquisa(k, aux.dir, ep);
            }
        }
    }

    /**
     * @param k - Chave
     * @param t - Nó de inserção
     * @param i - valor do nó (bit de comparação)
     */
    private PatNo insereEntre(String k, PatNo t, int i, ItemPatricia ip) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // Cria um novo nó externo
            PatNo p = this.criaNoExt(k, ip);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i, ip);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i, ip);
            }
            return aux;
        }
    }

    private PatNo insere(String k, PatNo t, ItemPatricia ip) {
        if (t == null) {
            return this.criaNoExt(k, ip);
        } else {

            // Alterar
            PatNo p = t;
            while (!this.eExterno(p)) {
                PatNoInt aux = (PatNoInt) p;
                if (this.bit(aux.index, k) == 1) {
                    p = aux.dir;
                } else {
                    p = aux.esq;
                }
            }
            // --------------------------------

            PatNoExt aux = (PatNoExt) p;
            int i = 1; // acha o primeiro bit diferente
            while ((i <= this.nbitsChave) &&
                    (this.bit(i, k) == this.bit(i, aux.chave_str))) {
                i++;
            }
            if (i > this.nbitsChave) {
                System.out.println("Erro : chave ja esta na arvore");
                return t;
            } else {
                return this.insereEntre(k, t, i, ip);
            }
        }
    }

    // Metodos para inserção
    public void insere(String k, ItemPatricia ip) {
        this.raiz = this.insere(k, this.raiz, ip);
    }

}