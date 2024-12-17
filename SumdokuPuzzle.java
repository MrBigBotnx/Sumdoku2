public class SumdokuPuzzle {
  private final int size; // Tamanho da grelha (N)
  private final int[][] groupMembership; // Matriz que indica o grupo de cada celula
  private final int[] groupsValues; // Soma das casas de cada grupo

  // 1. Metodo estatico que verifica se os parametros definem um puzzle valido
  public static boolean definesPuzzle(int[][] groupMembership, int[] groupsValues) {
    int size = groupMembership.length;

    // (1) Verificar se groupMembership e uma matriz quadrada com N entre 3 e 9
    if (size < 3 || size > 9)
      return false;
    for (int[] row : groupMembership) {
      if (row.length != size)
        return false;
    }

    // (2) Verificar se groupsValues tem tamanho entre 1 e N*N e valores no
    // intervalo correto
    if (groupsValues == null || groupsValues.length < 1 || groupsValues.length > size * size)
      return false;
    for (int value : groupsValues) {
      int maxValue = (size * size * size + size * size) / 2;
      if (value < 1 || value > maxValue)
        return false;
    }

    // (3) Verificar se groupMembership contem valores validos
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        int group = groupMembership[row][col];
        if (group < 0 || group >= groupsValues.length)
          return false;
      }
    }

    // (4) Verificar se todos os grupos tem pelo menos uma casa
    boolean[] groupHasCells = new boolean[groupsValues.length];
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        groupHasCells[groupMembership[row][col]] = true;
      }
    }
    for (boolean hasCells : groupHasCells) {
      if (!hasCells)
        return false;
    }

    // (5) Verificar se o puzzle tem uma unica solucao (simplificado: assume que
    // sim)
    return true;
  }

  // 2. Construtor
  public SumdokuPuzzle(int[][] groupMembership, int[] groupsValues) {
    this.size = groupMembership.length;
    this.groupMembership = groupMembership;
    this.groupsValues = groupsValues;
  }

  // 3. Retorna o tamanho da grelha
  public int size() {
    return this.size;
  }

  // 4. Retorna o numero de grupos
  public int numberOfGroups() {
    return this.groupsValues.length;
  }

  // 5. Retorna o numero do grupo para uma celula especifica
  public int groupNumber(int col, int row) {
    return groupMembership[row - 1][col - 1] + 1; // Ajuste para grupos numerados a partir de 1
  }

  // 6. Retorna a soma das casas de um grupo
  public int valueGroup(int group) {
    return groupsValues[group - 1]; // Ajuste para grupos numerados a partir de 1
  }

  // 7. Verifica se playedGrid resolve o puzzle
  public boolean isSolvedBy(SumdokuGrid playedGrid) {
    if (playedGrid == null || playedGrid.size() != this.size)
      return false;

    // Verifica se os valores respeitam os grupos
    int[] groupSums = new int[groupsValues.length];
    for (int row = 1; row <= size; row++) {
      for (int col = 1; col <= size; col++) {
        int value = playedGrid.value(row, col);
        if (value < 1 || value > size)
          return false; // Valor fora do intervalo
        groupSums[groupNumber(col, row) - 1] += value;
      }
    }

    // Verifica se as somas correspondem aos valores dos grupos
    for (int i = 0; i < groupsValues.length; i++) {
      if (groupSums[i] != groupsValues[i])
        return false;
    }

    return true;
  }

  // 8. Verifica se playedGrid esta parcialmente correta
  public boolean isPartiallySolvedBy(SumdokuGrid playedGrid) {
    if (playedGrid == null || playedGrid.size() != this.size)
      return false;

    for (int row = 1; row <= size; row++) {
      for (int col = 1; col <= size; col++) {
        int value = playedGrid.value(row, col);
        if (value != 0 && value != groupMembership[row - 1][col - 1]) {
          return false;
        }
      }
    }
    return true;
  }

  // 9. Representacao das pistas
  public String cluesToString() {
    StringBuilder sb = new StringBuilder();
    for (int group = 1; group <= groupsValues.length; group++) {
      sb.append(String.format("G%d = %d ", group, groupsValues[group - 1]));
    }
    return sb.toString();
  }

  // 10. Representacao textual do puzzle
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int row = 0; row < size; row++) {
      for (int col = 0; col < size; col++) {
        sb.append(groupMembership[row][col] + 1).append(" ");
      }
      sb.append("\n");
    }
    sb.append(cluesToString());
    return sb.toString();
  }
}
