/**
 * A classe {@code SumdokuRandomPuzzle} gera puzzles aleatórios predefinidos
 * para um tamanho de grelha especificado.
 * Os puzzles são carregados e embaralhados para oferecer aleatoriedade.
 * 
 * @author Grupo
 */
public class SumdokuRandomPuzzle {
  private final int size;                     // Tamanho da grelha
  private final SumdokuPuzzle[] puzzles;      // Array de puzzles predefinidos
  private int currentIndex;                   // Índice do próximo puzzle a ser retornado

  /**
   * Constrói um gerador de puzzles aleatórios para um tamanho especificado.
   *
   * @param size O tamanho da grelha. Deve estar entre 3 e 9.
   */
  public SumdokuRandomPuzzle(int size) {
      if (size < 3 || size > 9) {
          size = 3;
      }
      this.size = size;
      this.puzzles = loadPuzzles(size);
      this.currentIndex = 0;
      shufflePuzzles();
  }

  /**
   * Retorna o tamanho da grelha.
   *
   * @return O tamanho da grelha.
   */
  public int size() {
      return this.size;
  }

  /**
   * Verifica se há mais puzzles disponíveis.
   *
   * @return {@code true} se houver mais puzzles, {@code false} caso contrário.
   */
  public boolean hasNextPuzzle() {
      return currentIndex < puzzles.length;
  }

  /**
   * Retorna o próximo puzzle.
   *
   * @return Um objeto {@code SumdokuPuzzle} ou {@code null} se não houver mais puzzles.
   */
  public SumdokuPuzzle nextPuzzle() {
      if (hasNextPuzzle()) {
          return puzzles[currentIndex++];
      }
      return null;
  }

  // Carrega puzzles predefinidos
  private SumdokuPuzzle[] loadPuzzles(int size) {
      if (size == 3) {
          return new SumdokuPuzzle[]{
              new SumdokuPuzzle(
                  new int[][] {{0, 0, 1}, {0, 2, 1}, {3, 3, 4}},
                  new int[] {5, 5, 2, 5, 1}
              ),
              new SumdokuPuzzle(
                  new int[][] {{0, 1, 1}, {0, 1, 2}, {3, 3, 2}},
                  new int[] {6, 5, 4, 3}
              )
          };
      } else if (size == 5) {
          return new SumdokuPuzzle[]{
              new SumdokuPuzzle(
                  new int[][] {{0, 0, 1, 1, 1}, {2, 2, 1, 1, 1}, {3, 4, 5, 5, 6}, {3, 4, 7, 7, 7}, {8, 8, 7, 9, 9}},
                  new int[] {14, 3, 5, 8, 5, 9, 6, 10, 5, 7}
              )
          };
      }
      return new SumdokuPuzzle[0];
  }

  // Embaralha os puzzles usando Fisher-Yates
  private void shufflePuzzles() {
      for (int i = puzzles.length - 1; i > 0; i--) {
          int j = (int) (Math.random() * (i + 1));
          SumdokuPuzzle temp = puzzles[i];
          puzzles[i] = puzzles[j];
          puzzles[j] = temp;
      }
  }
}
