/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author Ben
 */
public class BlackjackGame {
    private Dealer dealer;
    private Player[] players;
    private Rules rules;
    public BlackjackGame(Dealer dealer, Player[] players, Rules rules) {
       this.dealer = dealer;
       this.players = players;
       this.rules = rules;
       
        
        
    }
    
    public void Play(){
        dealer.deal(players);
        System.out.println("TestDealDone");
        for(Player player: players){
            System.out.println( player.getName()+": "+player.toString());
        }
       System.out.println( dealer.toString());
       
       for(Player player: players){
           player.Play();
       }
       for(Player player: players){
            System.out.println( player.getName()+": "+player.toString());
        }
       if(dealer.Play(players[1].getHand()) == ActionEnum.Hit){
           dealer.deal(dealer);
       }
          
       System.out.println( dealer.toString());
       
    }
    
}
