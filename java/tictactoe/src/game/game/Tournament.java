package game.game;

import game.players.HumanPlayer;
import game.players.IPlayer;
import game.players.RandomPlayer;
import game.players.SequentialPlayer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class Tournament {
    private int[][] numbers;
    private ArrayList<IPlayer> players;
    private final int size;
    private boolean log;
    private final int n, m, k;

    public Tournament(int hP, int sP, int rP, boolean log, int n, int m, int k) {
        this.log = log;
        this.n = n;
        this.m = m;
        this.k = k;
        size = hP + sP + rP;
        numbers = new int[size][2];
        players = new ArrayList<>(size);
        for (int i = 0; i < hP; i++) {
            numbers[i][0] = i;
            players.add(new HumanPlayer());
        }
        for (int i = 0; i < sP; i++) {
            numbers[hP + i][0] = hP + i;
            players.add(new SequentialPlayer());
        }
        for (int i = 0; i < rP; i++) {
            numbers[sP + hP +i][0] = sP + hP + i;
            players.add(new RandomPlayer());
        }
    }


    public void sartTournament() {
        Game game;
        int counter = 0;
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                game = new Game(log, players.get(i), players.get(j), n, m, k);
                System.out.println("Номер первого игрока: " + (i + 1) + ", номер второго игрока: " + (j + 1));
                switch (game.play()) {
                    case WIN -> numbers[i][1] += 3;
                    case DRAW -> {
                        numbers[i][1]++;
                        numbers[j][1]++;
                    }
                    case LOSE -> numbers[j][1] += 3;
                }
                System.out.print("новые баллы после результатов игры номер " + (++counter) + ": " + (i + 1) + " игрок имеет ");
                System.out.println(numbers[i][1] + " баллов общего зачета, а игрок номер " + (j + 1) + " получает" + numbers[j][1] + " баллов всего");
                System.out.println("Номер первого игрока: " + (i + 1) + ", номер второго игрока: " + (j + 1));
                game = new Game(log, players.get(j), players.get(i), n, m, k);
                switch (game.play()) {
                    case WIN -> numbers[j][1] += 3;
                    case DRAW -> {
                        numbers[i][1]++;
                        numbers[j][1]++;
                    }
                    case LOSE -> numbers[i][1] += 3;
                }

                System.out.print("новые баллы после результатов игры номер " + (++counter) + ": " + (i + 1) + " игрок имеет ");
                System.out.println(numbers[i][1] + " баллов общего зачета, а игрок номер " + (j + 1) + " имеет суммарно " + numbers[j][1] + " баллов");
            }
        }
        Arrays.sort(numbers, Comparator.comparingInt(a -> -a[1]));
        System.out.println("Ознакомимся с общей таблицей результатов: ");
        for (int i = 0; i < size; i++) {
            System.out.println("игрок с номером " + numbers[i][0] + " набирает " + numbers[i][1] + " баллов и занимает " +
                    (i + 1) + " место. Поздравим его и пожелаем удачи в дальнейших соревнованиях!");
        }
    }
}
