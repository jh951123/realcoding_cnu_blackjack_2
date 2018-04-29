package com.cnu.blackjack;

import java.util.Map;

public class Evaluator {

    private Map<String, Player> playerMap;
    private Dealer dealer;

    public Evaluator(Map<String, Player> playerMap) {
        this.playerMap = playerMap;
        dealer = new Dealer();
        dealCardToPlayers();
    }

    public void start() {
        int d_score = dealer.getDealerScore();
        playerMap.forEach((name, player)->{
            int result;
            while(true){
                result = Sum(player);
                System.out.println(name+" player의 result:"+result);
                if(result<=16){
                    player.hitCard();
                    System.out.println(name+" player가 hit하였습니다.");
                }else if(result==21){
                    System.out.println(name+" player가 blackjack이 나왔습니다");
                    break;
                }else{
                    System.out.println(name+" player가 stay하였습니다.");
                    break;
                }
            }
            if(d_score==result){
                System.out.println(" 비겼습니다.");
                player.setBalance(player.getBalance()+player.getCurrentBet());
            }
            else if(d_score>21){
                if(result>21){
                    System.out.println(" player가 졌습니다.");
                    player.setBalance(player.getBalance());
                }else{
                    player.setBalance(player.getBalance()+player.getCurrentBet()*2);
                    System.out.println(" player가 이겼습니다.");
                }
            }else{
                if(result>21){
                    System.out.println(" player가 졌습니다.");
                    player.setBalance(player.getBalance());
                }else if(result>d_score&&result<=21){
                    if(result==21){
                        player.setBalance(player.getBalance()+player.getCurrentBet()*3);
                        System.out.println(" player가 블랙잭으로 이겼습니다.");
                    }else{
                        System.out.println(" player가 이겼습니다.");
                        player.setBalance(player.getBalance()+player.getCurrentBet()*2);
                    }
                }else if(result<d_score&&result<=21){
                    System.out.println(" player가 졌습니다.");
                    player.setBalance(player.getBalance());
                }
            }
            System.out.println("");
        });
    }
    public int Sum(Player player){
        int sum=0;
        for(int i=0; i<player.getHand().getCardList().size(); i++){
            Card card = player.getHand().getCardList().get(i);
            if(card.getRank()>=10){
                sum = sum + 10;
            }else{
                sum = sum+ card.getRank();
            }
        }
        return sum;
    }
    private void dealCardToPlayers() {
        playerMap.forEach((name, player) -> {
            player.hitCard();
            player.hitCard();
        });
    }

}
