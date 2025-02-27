class InimigoInteligente extends Personagem {
    InimigoInteligente(String nome, int hp, int mp, int alcance, int movimento, int ataque) {
        super(nome, hp, mp, alcance, movimento, ataque);
    }

    void moverInteligente(Personagem jogador) {
        System.out.println(nome + " (inimigo inteligente) está se movendo estrategicamente.");
        int novaPosX = posX;
        int novaPosY = posY;
        if (posX < jogador.posX) novaPosX++;
        else if (posX > jogador.posX) novaPosX--;
        if (posY < jogador.posY) novaPosY++;
        else if (posY > jogador.posY) novaPosY--;
        mover(novaPosX, novaPosY);
    }

    void decidirAcao(Personagem jogador) {
        if (estaDentroDoAlcance(jogador)) {
            System.out.println("Inimigo inteligente está ao alcance e vai atacar.");
            atacar(jogador);
        } else {
            moverInteligente(jogador);
        }
    }
}
