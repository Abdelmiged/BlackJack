
package blackjack;


public class Player {
    private String Name;
    private int Score = 0;
    private Card[] playerHand = new Card[11];
    private int cardsInHand = 0;
    
    public String getName() {
        return Name;
    }

    public Card[] getPlayerHand() {
        return playerHand;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public int getScore() {
        return Score;
    }
    
    public void AddCard(Card card)
    {
        if(cardsInHand < 11)
        {
            playerHand[cardsInHand] = card;
            Score += card.getValue();
        }
        else
        {
            return;
        }
        cardsInHand++;
    }
}
