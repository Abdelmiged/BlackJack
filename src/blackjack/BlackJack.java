/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */


package blackjack;
import java.util.Scanner;

public class BlackJack {

    static Game game = new Game();
    public static void main(String[] args) {
        GUI gui = new GUI();
        
        game.GenerateDeck();
        game.SetPlayerInfo();
        
        gui.runGUI(game.Deck, game.players[0].getPlayerHand(), game.players[1].getPlayerHand(), game.players[2].getPlayerHand(), game.players[3].getPlayerHand());
        
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Player " + (i + 1) + " Score: " + game.players[i].getScore());
        }
        
        System.out.println();
        PlayerHITorSTAND(gui);
        game.UpdateMaxScore();
        
        DealerTurn(gui);
        GameStatus();
    }
    
    public static void PlayerHITorSTAND(GUI gui)
    {
        Scanner input = new Scanner(System.in);
        int choice = -1;
        
        for(int i = 0; i < 3; i++)
        {
            while(!(choice == 0))
            {
                System.out.println("Player's " + (i + 1) + " turn: HIT = 1 or STAND = 0");
                System.out.println("Player's " + (i + 1) + " Score: " + game.players[i].getScore());
                System.out.println();
                choice = input.nextInt();
                if(choice == 1)
                {
                    Card card = game.DrawCard();
                    game.players[i].AddCard(card);
                    gui.updatePlayerHand(card, i);
                }
            }
            choice = -1;
        }
    }
    
    public static void DealerTurn(GUI gui)
    {
        
        while(game.players[3].getScore() < game.max || (game.players[3].getScore() == game.max && game.max < 21))
        {
            System.out.println("Dealer's turn");
            System.out.println("Dealer's Score: " + game.players[3].getScore());
            System.out.println();
            Card card = game.DrawCard();
            game.players[3].AddCard(card);
            gui.updateDealerHand(card, game.Deck);
        }
        System.out.println("Dealer's Score: " + game.players[3].getScore());
    }
    
    
    public static void GameStatus()
    {
        if(game.max == 0)
        {
            System.out.println("Dealer has Won!");
        }
        
        else if(game.max != 0)
        {
            if(game.max < 21)
            {
                if(game.players[3].getScore() > game.max && game.players[3].getScore() < 21)
                {
                    System.out.println("Dealer has Won!");
                }
                else
                {
                    int winCounter = 0 , indx = 0;
                    for(int i = 0; i < 3; i++)
                    {
                        if(game.highScore[i] == game.max)
                        {
                            winCounter++;
                            indx = i;
                        }
        
                    }
                    if(winCounter == 1)
                    {
                        System.out.println("Player " + game.players[indx].getName() + " has Won!");
                    }
                    else
                    {
                        System.out.println("It is a PUSH!");
                    }
                }
            }
            
            else if(game.max == 21)
            {
                if(game.players[3].getScore() == game.max)
                {
                    System.out.println("It is a PUSH!");
                }
                else if(game.players[3].getScore() != game.max)
                {
                    int winCounter = 0 , indx = 0;
                    for(int i = 0; i < 3; i++)
                    {
                        if(game.highScore[i] == game.max)
                        {
                            winCounter++;
                            indx = i;
                        }
        
                    }
                    if(winCounter == 1)
                    {
                        System.out.println("Player " + game.players[indx].getName() + " has Won!");
                    }
                    else
                    {
                        System.out.println("It is a PUSH!");
                    }
                }
            }
        }
    }
}

