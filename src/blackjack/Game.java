
package blackjack;
import java.util.Random;
import java.util.Scanner;

public class Game {
    public Player[] players = new Player[4];
    public Card[] Deck = new Card[52];
    public int[] highScore = new int[3];
    public int max = 0;
    
    int indx =0;
    public void GenerateDeck()
    {
        for(int suit = 0; suit < 4; suit++)
        {
            
            for(int rank = 0; rank < 13; rank++)
            {
                if(rank >= 10)
                {
                    Card c = new Card(suit , rank , 10);
                    Deck[indx] = c;
                }
                else if(rank < 10)
                {
                    Card c = new Card(suit , rank , rank + 1);
                    Deck[indx] = c;
                }
                indx++;
            }
        }
    }
    
    public Card DrawCard()
    {
        Random rand = new Random();
        Card c = null;
        int randomNumber = 0;
        while(c == null)
        {
            randomNumber = rand.nextInt(52);
            c = Deck[randomNumber];
            Deck[randomNumber] = null;
        }
        
        return c;
    }
    
    public void SetPlayerInfo()
    {
        Scanner input = new Scanner(System.in);
        for(int i = 0; i < 3; i++)
        {
            System.out.println("Please enter name of player " + (i + 1) + ".");
            players[i] = new Player();
            players[i].setName(input.next());
            
            players[i].AddCard(DrawCard());
            players[i].AddCard(DrawCard());
            System.out.println();
        }
        
        players[3] = new Player();
        players[3].setName("Dealer");
        players[3].AddCard(DrawCard());
        players[3].AddCard(DrawCard());
    }
    
    public void UpdateMaxScore()
    {
        for(int i = 0; i < 3; i++)
        {
            highScore[i] = 0;
            if(players[i].getScore() <= 21)
            {
                highScore[i] = players[i].getScore();
            }
            
            if(max < highScore[i])
            {
                max = highScore[i];
            }
        }
    }
    
    
}
