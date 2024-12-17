/**
 * A classe {@code SumdokuGrid} representa uma grelha quadrada NxN para o jogo Sumdoku.
 * Permite armazenar e manipular valores nas células da grelha.
 *
 * @author Grupo
 */
public class SumdokuGrid {
    private final int[][] grid; // Matriz que armazena os valores da grelha
    private final int size;     // Tamanho da grelha (N)

    /**
     * Constrói uma grelha vazia de tamanho especificado.
     *
     * @param size O tamanho da grelha (N). Deve ser entre 3 e 9.
     */
    public SumdokuGrid(int size) {
        this.size = size;
        this.grid = new int[size][size];
    }

    /**
     * Retorna o tamanho da grelha.
     *
     * @return O tamanho da grelha (N).
     */
    public int size() {
        return this.size;
    }

    /**
     * Obtém o valor da célula na posição especificada.
     *
     * @param row A linha da célula (1 ≤ row ≤ size).
     * @param col A coluna da célula (1 ≤ col ≤ size).
     * @return O valor armazenado na célula.
     */
    public int value(int row, int col) {
        return this.grid[row - 1][col - 1];
    }

    /**
     * Verifica se a célula na posição especificada está preenchida.
     *
     * @param row A linha da célula (1 ≤ row ≤ size).
     * @param col A coluna da célula (1 ≤ col ≤ size).
     * @return {@code true} se a célula estiver preenchida, {@code false} caso contrário.
     */
    public boolean isFilled(int row, int col) {
        return this.grid[row - 1][col - 1] != 0;
    }

    /**
     * Preenche a célula com o valor especificado.
     *
     * @param row   A linha da célula (1 ≤ row ≤ size).
     * @param col   A coluna da célula (1 ≤ col ≤ size).
     * @param value O valor a ser preenchido (1 ≤ value ≤ size).
     * @return {@code true} se o preenchimento for bem-sucedido, {@code false} caso contrário.
     */
    public boolean fill(int row, int col, int value) {
        if (row >= 1 && row <= size && col >= 1 && col <= size && value >= 0 && value <= size) {
            this.grid[row - 1][col - 1] = value;
            return true;
        }
        return false;
    }

    /**
     * Retorna uma representação textual da grelha.
     *
     * @return A grelha como uma string.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(this.headRow());
        for (int row = 1; row <= this.size; ++row) {
            for (int col = 1; col <= this.size; ++col) {
                if (this.isFilled(row, col)) {
                    sb.append(this.value(row, col));
                } else {
                    sb.append(".");
                }
                sb.append(" ");
            }
            sb.append("\n");
        }
        sb.append(this.headRow());
        return sb.toString();
    }

    /**
     * Gera uma linha horizontal de separação.
     *
     * @return Uma linha de separação.
     */
    private String headRow() {
        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= this.size; ++i) {
            sb.append("--");
        }
        sb.append("\n");
        return sb.toString();
    }
}
