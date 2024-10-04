import java.util.Scanner;

class Tree {
    String character;
    Tree left, right;

    public Tree(String character) {
        this.character = character;
        this.left = null;
        this.right = null;
    }

    public Tree(String character, Tree left, Tree right) {
        this.character = character;
        this.left = left;
        this.right = right;
    }
}

public class Main {

    public static String morseToChar(Tree morseTree, String sequence, int i) {
        if (i == sequence.length()) {
            return morseTree.character;
        } else if (sequence.charAt(i) == '.') {
            return morseToChar(morseTree.left, sequence, i + 1);
        } else {
            return morseToChar(morseTree.right, sequence, i + 1);
        }
    }

    public static String decodeMorse(Tree morseTree, String str) {
        StringBuilder decoded = new StringBuilder();
        String[] sequences = str.split(" ");

        for (String sequence : sequences) {
            if (sequence.equals("/")) {
                decoded.append(" ");
            } else {
                decoded.append(morseToChar(morseTree, sequence, 0));
            }
        }
        return decoded.toString();
    }

    public static void printTree(Tree node, String prefix, boolean isLeft) {
        if (node != null) {
            System.out.println(prefix + (isLeft ? "|-- " : "\\-- ") + node.character);
            printTree(node.left, prefix + (isLeft ? "|   " : "    "), true);
            printTree(node.right, prefix + (isLeft ? "|   " : "    "), false);
        }
    }

    public static void main(String[] args) {
        Tree morseTree = new Tree("",
                new Tree("E",
                        new Tree("I",
                                new Tree("S",
                                        new Tree("H", new Tree("5"), new Tree("4")),
                                        new Tree("V", null, new Tree("3"))),
                                new Tree("U",
                                        new Tree("F", null, null),
                                        new Tree("", null, new Tree("2")))),
                        new Tree("A",
                                new Tree("R",
                                        new Tree("L", null, null),
                                        new Tree("", new Tree("+"), null)),
                                new Tree("W",
                                        new Tree("P", null, null),
                                        new Tree("J", null, new Tree("1"))))),
                new Tree("T",
                        new Tree("N",
                                new Tree("D",
                                        new Tree("B", new Tree("6"), new Tree("=")),
                                        new Tree("X", new Tree("/"), null)),
                                new Tree("K",
                                        new Tree("C", null, null),
                                        new Tree("Y", null, null))),
                        new Tree("M",
                                new Tree("G",
                                        new Tree("Z", new Tree("7"), null),
                                        new Tree("Q", null, null)),
                                new Tree("O",
                                        new Tree("", new Tree("8"), null),
                                        new Tree("", new Tree("9"), new Tree("0"))))));

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite o código Morse (use '.' para pontos, '-' para traços, e '/' para espaços entre palavras):");
        String inputMorse = scanner.nextLine();

        String decodedMessage = decodeMorse(morseTree, inputMorse);
        System.out.println("Mensagem decodificada: " + decodedMessage);

        System.out.println("\nÁrvore Morse Binária:");
        printTree(morseTree, "", true);

        scanner.close();
    }
}
