package com.cnu.blackjack;

public class Application {
    public static void main(String[] args){
        Game game = new Game(new Deck(1));
        game.addPlayer("ajeong",20000);
        game.addPlayer("hyemin",10000);
        game.addPlayer("jahyeon",10000);
        game.addPlayer("jaehun",10000);
        game.getPlayerList().forEach((name,player)->{
          player.placeBet(2000);
        });
        game.start();
        Evaluator evaluator = new Evaluator(game.getPlayerList());
        evaluator.start();
        game.getPlayerList().forEach((name,player)->{
            System.out.println(name+" player의 최종 금액은 "+player.getBalance()+" 입니다.");
        });
    }
}
