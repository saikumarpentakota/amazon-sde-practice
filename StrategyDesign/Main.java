package StrategyDesign;

interface LevelOfDifficulty {
    void playGame();
}


class EasyLevel implements LevelOfDifficulty {
    @Override
    public void playGame() {
        System.out.println("This is a Easy Level game");
    }
}

class MediumLevel implements LevelOfDifficulty {
    @Override
    public void playGame() {
        System.out.println("This is a Medium Level game");
    }
}

class DifficultLevel implements LevelOfDifficulty {
    @Override
    public void playGame() {
        System.out.println("This is a Difficult Level game");
    }
}


class ChessGame {
    private LevelOfDifficulty difficulty;

    public ChessGame(LevelOfDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void setDifficultyLevel(LevelOfDifficulty difficulty) {
        this.difficulty = difficulty;
    }

    public void startChessGame() {
        difficulty.playGame();
    }
}


public class Main {
    public static void main(String[] args) {
        System.out.println("Strategy Design pattern - Behavioral Design Pattern");

        ChessGame game = new ChessGame(new EasyLevel());
        game.startChessGame();

        game.setDifficultyLevel(new MediumLevel());
        game.startChessGame();

        game.setDifficultyLevel(new DifficultLevel());
        game.startChessGame();
    }
}