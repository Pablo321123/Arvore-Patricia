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

public class ArvorePatricia {

    private PatNo raiz;
    private int nbitsChave;

    /**
     * @param nbitsChave - 128 bits para caracteres ASCII
     */
    public ArvorePatricia(int nbitsChave) {
        this.raiz = null;
        this.nbitsChave = nbitsChave;
    }

    // Retorna o i-ésimo bit da chave k a partir da esquerda
    private int bit(int i, char k) {
        if (i == 0) {
            return 0;
        }

        int c = (int) k;

        for (int j = 1; j <= this.nbitsChave - i; j++) {
            c = c / 2;
        }
        return c % 2;
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
    private PatNo criaNoExt(char k) {
        PatNoExt p = new PatNoExt();
        p.chave = k;
        return p;
    }

    // Método para pesquisa:

    public void pesquisa(char k) {
        this.pesquisa(k, this.raiz);
    }

    public void pesquisaPalavra(char k) {
        this.pesquisaPalavra(k, this.raiz);
    }

    private void pesquisa(char k, PatNo t) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave == k) {
                System.out.println("Elemento encontrado");
            } else {
                System.out.println("Elemento nao encontrado");
            }
        } else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisa(k, aux.esq);
            } else {
                pesquisa(k, aux.dir);
            }
        }
    }

    private void pesquisaPalavra(char k, PatNo t) {
        if (this.eExterno(t)) {
            PatNoExt aux = (PatNoExt) t;
            if (aux.chave == k) {
                System.out.println("Elemento encontrado");
            } else {
                System.out.println("Elemento nao encontrado");
                System.exit(1); 
            }
        } else {
            PatNoInt aux = (PatNoInt) t;
            if (this.bit(aux.index, k) == 0) {
                pesquisaPalavra(k, aux.esq);
            } else {
                pesquisaPalavra(k, aux.dir);
            }
        }
    }

    // Metodos para inserção
    public void insere(char k) {
        this.raiz = this.insere(k, this.raiz);
    }

    /**
     * @param k - Chave
     * @param t - Nó de inserção
     * @param i - valor do nó (bit de comparação)
     */
    private PatNo insereEntre(char k, PatNo t, int i) {
        PatNoInt aux = null;
        if (!this.eExterno(t)) {
            aux = (PatNoInt) t;
        }
        if (this.eExterno(t) || (i < aux.index)) { // Cria um novo nó externo
            PatNo p = this.criaNoExt(k);
            if (this.bit(i, k) == 1) {
                return this.criaNoInt(i, t, p);
            } else {
                return this.criaNoInt(i, p, t);
            }
        } else {
            if (this.bit(aux.index, k) == 1) {
                aux.dir = this.insereEntre(k, aux.dir, i);
            } else {
                aux.esq = this.insereEntre(k, aux.esq, i);
            }
            return aux;
        }
    }

    private PatNo insere(char k, PatNo t) {
        if (t == null) {
            return this.criaNoExt(k);
        } else {

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
                    (this.bit(i, k) == this.bit(i, aux.chave))) {
                i++;
            }
            if (i > this.nbitsChave) {
                System.out.println("Erro : chave ja esta na arvore");
                return t;
            } else {
                return this.insereEntre(k, t, i);
            }
        }
    }

}