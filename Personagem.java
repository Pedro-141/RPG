class Personagem {
    String nome;
    int hp, mp, alcance, movimento, ataque;
    int posX, posY;

    Personagem(String nome, int hp, int mp, int alcance, int movimento, int ataque) {
        this.nome = nome;
        this.hp = hp;
        this.mp = mp;
        this.alcance = alcance;
        this.movimento = movimento;
        this.ataque = ataque;
    }

    void mover(int destinoX, int destinoY) {
        posX = destinoX;
        posY = destinoY;
        System.out.println(nome + " se moveu para (" + posX + ", " + posY + ")");
    }

    void atacar(Personagem inimigo) {
        System.out.println(nome + " atacou " + inimigo.nome + " causando " + ataque + " de dano.");
        inimigo.hp -= ataque;
    }

    void curar() {
        if (mp >= 10) {
            mp -= 10;
            hp += 30;
            System.out.println(nome + " curou 30 de vida.");
        } else {
            System.out.println(nome + " não tem MP suficiente para curar.");
        }
    }

    boolean estaVivo() {
        return hp > 0;
    }

    boolean estaDentroDoAlcance(Personagem inimigo) {
        int distancia = Math.abs(posX - inimigo.posX) + Math.abs(posY - inimigo.posY);
        return distancia <= alcance;
    }
}
