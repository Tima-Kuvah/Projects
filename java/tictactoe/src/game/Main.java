package game;

import game.game.Game;
import game.game.Tournament;
import game.players.HumanPlayer;
import game.players.IPlayer;
import game.players.RandomPlayer;
import game.players.SequentialPlayer;


import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        boolean tour = false;
        boolean log = false;
        int m = 0;
        int n = 0;
        int k = 0;
        Scanner in = new Scanner(System.in);
        System.out.println("Hi! Тебя приветствует программа для игры в крестики-нолики!!");
        System.out.print("Заранее попрошу быть внимательным с вводом чисел! Если вместо числа вы введете не число, ");
        System.out.print("я, к сожалению сломаюсь, и вы не сможете сыграть...");

        tour = askYN(in, "Пожалуйста, введите, желаете ли вы запустить турнир?");

        log = askYN(in, "Пожалуйста, введите, желаете ли вы включить пошаговое отображение игр(-ы)?");


        n = askNatural(in, "Пожалуйста, введите натуральное число для n: ");

        m = askNatural(in, "Пожалуйста, введите натуральное число для m: ");

        k = askNaturalWithCond(in, "Пожалуйста, введите натуральное число для число k: ", n, m);

        if (!tour) {
            ArrayList<IPlayer> players = new ArrayList<>();
            int firstP = askPlayerType(in, "введите тип первого игрока. 1 для HumanPlayer, 2 для SequentialPlayer, 3 для RandomPlayer");
            players.add(addingPlayer(firstP));

            int secondP = askPlayerType(in, "введите тип второго игрока. 1 для HumanPlayer, 2 для SequentialPlayer, 3 для RandomPlayer");
            players.add(addingPlayer(secondP));
            Game game = new Game(log, players.get(0), players.get(1), n, m, k);
            game.play();
        }
        else {
            int hP, sP, rP;
            hP = askNonNegative(in, "Пожалуйста, введите натуральное число: количество игроков типа HumanPlayer: ");
            sP = askNonNegative(in, "Пожалуйста, введите натуральное число: количество игроков типа SequentialPlayer: ");
            rP = askNonNegative(in, "Пожалуйста, введите натуральное число: количество игроков типа RandomPlayer:");
            Tournament tournament = new Tournament(hP, sP, rP, log, n, m, k);
            tournament.sartTournament();
        }
        in.close();
    }

    private static int askNaturalWithCond(Scanner in, String s, int n, int m) {
        int value = 0;
        while (value <= 0 || value > Math.max(n, m)){
            System.out.println(s);

            try {
                value = in.nextInt();

                if (value <= 0) {
                    System.out.println("Число должно быть натуральным (больше 0)!");
                }
                if (value > Math.max(n, m)) {
                    System.out.println("Число должно быть меньше n: " + n + " и m " + m);
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Пожалуйста, введите целое число.");
                in.next();
                value = 0;
            }
        }
        return value;
    }

    private static IPlayer addingPlayer(int playerType) {
        return switch (playerType) {
            case 1 -> new HumanPlayer();
            case 2 -> new SequentialPlayer();
            case 3 -> new RandomPlayer();
            default -> throw new IllegalArgumentException("не корректный тип игрока");
        };
    }

    private static boolean askYN(Scanner in, String question) {
        System.out.println(question);
        System.out.println("Ответ введите в формате: y/n: ");

        for (int i = 0; i < 10; i++) {
            try {
                String input = in.next();
                if (input.equals("y")) {
                    return true;
                } else if (input.equals("n")) {
                    return false;
                } else {
                    throw new IllegalArgumentException("Некорректный ввод");
                }
            } catch (IllegalArgumentException e) {
                if (i < 9) {
                    System.out.println("Пожалуйста, введите 'y' или 'n'. Осталось попыток: " + (9 - i));
                } else {
                    System.out.println("Превышено количество попыток. По умолчанию выбран 'n'.");
                    return false;
                }
            }
        }
        return false;
    }

    private static int askNatural(Scanner in, String massage) {
        int value = 0;
        while (value <= 0) {
            System.out.println(massage);

            try {
                value = in.nextInt();

                if (value <= 0) {
                    System.out.println("Число должно быть натуральным (больше 0)!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Пожалуйста, введите целое число.");
                in.next();
                value = 0;
            }
        }
        return value;
    }

    private static int askNonNegative(Scanner in, String massage) {
        int value = -1;
        while (value < 0) {
            System.out.println(massage);

            try {
                value = in.nextInt();

                if (value < 0) {
                    System.out.println("Число должно быть не отрицательным!");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Пожалуйста, введите целое, не отрицательное число.");
                in.next();
                value = -1;
            }
        }
        return value;
    }

    private static int askPlayerType(Scanner in, String massage) {
        int playerType = 0;
        System.out.println(massage);

        while (playerType < 1 || playerType > 3) {
            try {
                playerType = in.nextInt();
                if (playerType < 1 || playerType > 3) {
                    System.out.println("Пожалуйста, введите число от 1 до 3:");
                }
            } catch (InputMismatchException e) {
                System.out.println("Ошибка! Пожалуйста, введите число 1, 2 или 3:");
                in.next();
                playerType = 0;
            }
        }

        return playerType;
    }
}



