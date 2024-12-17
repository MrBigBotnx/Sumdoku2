import java.util.Scanner;

/**
 * A classe {@code SumdokuTxt} é a interface de texto do jogo Sumdoku.
 * Permite ao jogador resolver puzzles interativamente.
 * 
 * @author Grupo
 */
public class SumdokuTxt {

    /**
     * Método principal do programa.
     * Recebe o tamanho da grelha e inicia o jogo.
     *
     * @param args Argumentos da linha de comando.
     */
    public static void main(String[] args) {
        // Valida o tamanho da grelha passado como argumento
        if (args.length < 1) {
            System.out.println("Uso: java SumdokuTxt <tamanho da grelha>");
            return;
        }

        int size;
        try {
          size = Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("O tamanho da grelha deve ser um número inteiro.");
            return;
        }

        if (size < 3 || size > 9) {
            System.out.println("O tamanho da grelha deve estar entre 3 e 9.");
            return;
        }

        // Cria o gerador de puzzles para o tamanho dado
        SumdokuRandomPuzzle generator = new SumdokuRandomPuzzle(size);
        Scanner reader = new Scanner(System.in);

        // Loop para permitir que o jogador jogue varios puzzles
        while (generator.hasNextPuzzle()) {
            SumdokuPuzzle puzzle = generator.nextPuzzle();
            System.out.println("Novo puzzle:");
            System.out.println(puzzle);

            System.out.print("Número máximo de tentativas permitidas: ");
            int maxAttempts = reader.nextInt();

            // Jogar o puzzle
            play(puzzle, maxAttempts, reader);

            // Pergunta ao jogador se deseja continuar jogando
            System.out.print("Deseja jogar outro puzzle? (s/n): ");
            String response = reader.next().toLowerCase();
            if (!response.equals("s")) {
                break;
            }
        }
        System.out.println("Obrigado por jogar Sumdoku!");
    }

    /**
     * Gerencia o fluxo de um único jogo.
     *
     * @param puzzle       O puzzle a ser jogado.
     * @param maxAttempts  O número máximo de tentativas.
     * @param reader       Scanner para entrada do usuário.
     */
    public static void play(SumdokuPuzzle puzzle, int maxAttempts, Scanner reader) {
        SumdokuGrid playedGrid = new SumdokuGrid(puzzle.size());
        int attempts = 0;

        while (attempts < maxAttempts) {
            System.out.println("\nPuzzle atual:");
            System.out.println(playedGrid);

            // Solicita ao jogador para inserir linha, coluna e valor
            System.out.print("Insira linha, coluna e valor (ex: 1 2 3): ");
            int row = reader.nextInt();
            int col = reader.nextInt();
            int value = reader.nextInt();

            // Preenche a grade jogada
            boolean filled = playedGrid.fill(row, col, value);
            if (!filled) {
                System.out.println("Coordenadas ou valor inválido. Tente novamente.");
                continue;
            }

            // Verifica se o puzzle foi resolvido
            if (puzzle.isSolvedBy(playedGrid)) {
                System.out.println("Parabéns! Você resolveu o puzzle!");
                return;
            }

            attempts++;
            System.out.println("Tentativas restantes: " + (maxAttempts - attempts));
        }

        System.out.println("Você atingiu o número máximo de tentativas.");
        System.out.println("Solução correta:");
        System.out.println(puzzle.cluesToString());
    }
}
