import javax.swing.*;
import java.util.Random;
import java.util.Scanner;

class Jogo {
    Personagem jogador;
    Personagem inimigo;
    int inimigosDerrotados = 0;
    Tabuleiro tabuleiro;

    Jogo() {
        inicializarJogo();
        criarNovoInimigo();
        iniciarTabuleiro();
        jogar();
    }

    void inicializarJogo() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma classe:");
        System.out.println("1. Monge (135 Hp, 10 Mp, alcance 2, move 6, ataque 15)");
        System.out.println("2. Guerreiro (130 Hp, 10 Mp, alcance 3, move 3, ataque 20)");
        System.out.println("3. Arqueiro (85 Hp, 20 Mp, alcance 6, move 2, ataque 30)");
        System.out.println("4. Mago (70 Hp, 30 Mp, alcance 4, move 1, ataque 35)");

        int escolha = scanner.nextInt();
        jogador = criarPersonagem(escolha, "Jogador");

        System.out.println("Você escolheu a classe " + escolha);
    }

    Personagem criarPersonagem(int classe, String nome) {
        switch (classe) {
            case 1:
                return new Personagem(nome, 135, 10, 2, 4, 15);
            case 2:
                return new Personagem(nome, 130, 10, 3, 3, 20);
            case 3:
                return new Personagem(nome, 85, 20, 5, 2, 30);
            case 4:
                return new Personagem(nome, 70, 30, 4, 1, 35);
            default:
                throw new IllegalArgumentException("Classe inválida!");
        }
    }

    void criarNovoInimigo() {
        inimigo = criarInimigoAleatorio();
        inimigo.posX = new Random().nextInt(30);
        inimigo.posY = new Random().nextInt(30);
    }

    Personagem criarInimigoAleatorio() {
        String[] classes = {"Monge", "Guerreiro", "Arqueiro", "Mago"};
        String classeInimigo = classes[new Random().nextInt(classes.length)];
        return criarPersonagemPorNome(classeInimigo, "Inimigo");
    }

    Personagem criarPersonagemPorNome(String classe, String nome) {
        switch (classe) {
            case "Monge":
                return new Personagem(nome, 135, 10, 2, 4, 15);
            case "Guerreiro":
                return new Personagem(nome, 130, 10, 3, 3, 20);
            case "Arqueiro":
                return new Personagem(nome, 85, 20, 5, 2, 30);
            case "Mago":
                return new Personagem(nome, 70, 30, 4, 1, 35);
            default:
                throw new IllegalArgumentException("Classe inválida!");
        }
    }

    void iniciarTabuleiro() {
        JFrame frame = new JFrame("Jogo RPG");
        tabuleiro = new Tabuleiro(jogador, inimigo);
        frame.add(tabuleiro);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void jogar() {
        Scanner scanner = new Scanner(System.in);
        boolean jogoAtivo = true;

        while (jogoAtivo) {
            System.out.println("Sua vez! Escolha uma ação:");
            System.out.println("1. Andar");
            System.out.println("2. Atacar");
            System.out.println("3. Curar");
            System.out.println("4. Passar a vez");

            int acao = scanner.nextInt();

            switch (acao) {
                case 1:
                    andar();
                    break;
                case 2:
                    if (jogador.estaDentroDoAlcance(inimigo)) {
                        jogador.atacar(inimigo);
                    } else {
                        System.out.println("Inimigo fora do alcance.");
                    }
                    break;
                case 3:
                    jogador.curar();
                    break;
                case 4:
                    System.out.println("Você passou a vez.");
                    break;
                default:
                    System.out.println("Ação inválida.");
                    break;
            }

            tabuleiro.repaint();

            if (!inimigo.estaVivo()) {
                System.out.println("Você derrotou o inimigo!");
                inimigosDerrotados++;
                criarNovoInimigo();
                continue;
            }

            if (!jogador.estaVivo()) {
                System.out.println("Você perdeu!");
                jogoAtivo = false;
            }
        }

        scanner.close();
    }

    void andar() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Escolha uma direção:");
        System.out.println("N - Norte");
        System.out.println("S - Sul");
        System.out.println("L - Leste");
        System.out.println("O - Oeste");

        char direcao = scanner.next().charAt(0);

        int novaPosX = jogador.posX;
        int novaPosY = jogador.posY;

        switch (direcao) {
            case 'N':
            case 'n':
                novaPosY = Math.max(0, jogador.posY - 1);
                break;
            case 'S':
            case 's':
                novaPosY = Math.min(29, jogador.posY + 1);
                break;
            case 'L':
            case 'l':
                novaPosX = Math.min(29, jogador.posX + 1);
                break;
            case 'O':
            case 'o':
                novaPosX = Math.max(0, jogador.posX - 1);
                break;
            default:
                System.out.println("Direção inválida.");
                return;
        }

        jogador.mover(novaPosX, novaPosY);
        tabuleiro.repaint();
    }
}
