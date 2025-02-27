import javax.swing.*;
import java.awt.*;
import javax.swing.Timer;

public class Tabuleiro extends JPanel {
    Personagem jogador;
    Personagem inimigo;
    boolean attacking = false;

    Tabuleiro(Personagem jogador, Personagem inimigo) {
        this.jogador = jogador;
        this.inimigo = inimigo;
        setPreferredSize(new Dimension(600, 600));
        setBackground(Color.DARK_GRAY);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        int cellSize = 20;
        for (int x = 0; x < 30; x++) {
            for (int y = 0; y < 30; y++) {
                g.setColor(new Color(30, 30, 30));
                g.fillOval(x * cellSize, y * cellSize, cellSize, cellSize);
                if (Math.abs(x - jogador.posX) + Math.abs(y - jogador.posY) <= 5) {
                    g.setColor(new Color(255, 255, 0, 128));
                    g.fillRect(x * cellSize + 5, y * cellSize + 5, cellSize - 10, cellSize - 10);
                    if (inimigo.posX == x && inimigo.posY == y) {
                        if (!inimigo.estaVivo()) {
                            g.setColor(new Color(150, 0, 0));
                        } else {
                            g.setColor(Color.RED);
                        }
                        g.fillRect(inimigo.posX * cellSize + 5, inimigo.posY * cellSize + 5, cellSize - 10, cellSize - 10);
                    }
                }
            }
        }
        g.setColor(Color.BLUE);
        g.fillRect(jogador.posX * cellSize + 5, jogador.posY * cellSize + 5, cellSize - 10, cellSize - 10);
        adicionarDirecoes(g);
        if (attacking) {
            g.setColor(new Color(0, 255, 0, 128));
            for (int dx = -jogador.alcance; dx <= jogador.alcance; dx++) {
                for (int dy = -jogador.alcance; dy <= jogador.alcance; dy++) {
                    if (Math.abs(dx) + Math.abs(dy) <= jogador.alcance) {
                        g.fillRect((jogador.posX + dx) * cellSize + 5, (jogador.posY + dy) * cellSize + 5, cellSize - 10, cellSize - 10);
                    }
                }
            }
        }
    }

    public void setAtackRange(int posX, int posY, int alcance) {
        attacking = true;
        repaint();
        Timer timer = new Timer(500, e -> {
            attacking = false;
            repaint();
        });
        timer.setRepeats(false);
        timer.start();
    }

    private void adicionarDirecoes(Graphics g) {
        g.setColor(Color.WHITE);
        int cellSize = 20;
        if (Math.abs(jogador.posX - jogador.posX) + Math.abs(jogador.posY - (jogador.posY - 5)) <= 5) {
            g.drawString("N", jogador.posX * cellSize + cellSize / 2 - 5, (jogador.posY - 5) * cellSize + cellSize / 2 + 5);
        }
        if (Math.abs(jogador.posX - jogador.posX) + Math.abs(jogador.posY - (jogador.posY + 5)) <= 5) {
            g.drawString("S", jogador.posX * cellSize + cellSize / 2 - 5, (jogador.posY + 5) * cellSize + cellSize / 2 + 5);
        }
        if (Math.abs(jogador.posX - (jogador.posX + 5)) + Math.abs(jogador.posY - jogador.posY) <= 5) {
            g.drawString("L", (jogador.posX + 5) * cellSize + cellSize / 2 - 5, jogador.posY * cellSize + cellSize / 2 + 5);
        }
        if (Math.abs(jogador.posX - (jogador.posX - 5)) + Math.abs(jogador.posY - jogador.posY) <= 5) {
            g.drawString("O", (jogador.posX - 5) * cellSize + cellSize / 2 - 5, jogador.posY * cellSize + cellSize / 2 + 5);
        }
    }
}
