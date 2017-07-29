package dcd_love_letter;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

// player_status
class Player {
	int score;
	ArrayList<Integer> used_cards = new ArrayList<Integer>();
	int last_used_card;
	int card_in_hand;
	int drawn_card;
	int computer; // 0: human, 1~: ai tendency
	int status; // 0: die, 1: live
	int id;
}

public class dcd_love_letter {

	public int player_num;
	public int user_id;
	public int turn;
	public int next_player_id;
	public int[] score = new int[4];
	public ArrayList<Integer> deck = new ArrayList<Integer>();
	public static ArrayList<Player> players = new ArrayList<Player>(); 
	public int living_player_num;
	
	public int get_int_input() throws IOException {
		return System.in.read() - 48;
	}
	
//	input:
//		number of player (2~4) -> save at member variable
//	output: 
//		none
    public void init_game() throws IOException {
    	Random generator = new Random();
    	
    	System.out.print("input player number(2~4): ");
        this.player_num = this.get_int_input();
        //System.out.println("player number: " + this.player_num);
        
        this.user_id = generator.nextInt(this.player_num);
        
        System.out.println("your id: " + this.user_id);
        
        // create players & init score
        for (int i = 0; i < this.player_num; i++)
        {
        	Player p = new Player();
        	if (i == this.user_id)
        		p.computer = 0; // human
        	else
        		p.computer = 1; // computer
        	p.score = 0;
        	p.id = i;
        	players.add(p);
        }
        
        this.next_player_id = 0;
    }

    
//  if deck is empty, return 0
    public int draw_a_card() {
    	int cur_card;
    	
    	if (deck.isEmpty())
    	{
    		return 0;
    	}
    	else
    	{
    		cur_card = deck.get(0);
    		deck.remove(0);
    		return cur_card;
    	}
    }
    
    public void shuffle_deck() {
    	deck.clear();
    	
    	// guard(5)
    	// priest(2)
    	// baron(2)
    	// handmade(2)
    	// prince(2)
    	// king(1)
    	// countess(1)
    	// princess(1)
    	Collections.addAll(deck, 1, 1, 1, 1, 1, 2, 2, 3, 3, 4, 4, 5, 5, 6, 7, 8);
    	Collections.shuffle(deck);
    }
    
//  input:
//    	
//  output:  
//    
    public void init_round(){
        this.turn = 0;
        Player p;

        shuffle_deck();

        // test shuffle & draw a card
//        while (!deck.isEmpty())
//        {
//        	System.out.print(draw_a_card());
//        }
        
        this.draw_a_card(); // throw away a card
        
        this.living_player_num = this.player_num;
        
        for (int i = 0; i < this.player_num; i++)
        {
        	p = players.get(i);
        	p.drawn_card = 0;
        	p.last_used_card = 0;
        	p.status = 1;
        	p.card_in_hand = this.draw_a_card();
        }
    }
    
    public int select_player_id() {
    	Player p;
    	int cur_player_id;
    	
    	System.out.println("current turn " + this.turn + " next player id " + this.next_player_id);
    	
    	for (int i = 0; i < this.player_num; i++)
    	{
    		p = players.get((this.next_player_id + i) % this.player_num);
    		if (p.status == 1)
    		{
    			cur_player_id = (this.next_player_id + i) % this.player_num;
    			this.next_player_id = (cur_player_id + 1) % this.player_num;
    			return cur_player_id;
    		}
    	}
    	return -1;
    }
    
    public void player_action(Player p) throws IOException {
    	Random gen = new Random();
    	
    	// draw a card
    	p.drawn_card = this.draw_a_card();
    	
    	// print cards in hand and just drawn
    	
    	// get input user selection to use
    	System.out.print("select card: ");
    	
    	// print selected card
    	
    	// use card
    	
    	// save hand
    	

    	
    	//System.out.print("id: " + p.id + " player");

//    	if (gen.nextInt(4) == 0)
//    	{
//    		p.status = 0;
//    		this.living_player_num--;
//    		System.out.println(" died");    		
//    	}
//    	else
//    	{
//    		System.out.println(" lived");    		
//    	}
    	
    }
    
    public void computer_action(Player p) {
    	// bongjun 
    	Random gen = new Random();

    	p.drawn_card = this.draw_a_card();

    	System.out.print("id: " + p.id + " computer");

    	if (gen.nextInt(4) == 0)
    	{
    		p.status = 0;
    		this.living_player_num--;
    		System.out.println(" died");    		
    	}
    	else
    	{
    		System.out.println(" lived");    		
    	}
    }
    
    public boolean check_round_over() {
    	turn++;
    	
    	if (this.living_player_num == 1)
    	{
    		System.out.println("one player remained");
    		for (Player p: players)
    		{
    			if (p.status == 1)
    				System.out.println("id " + p.id + " win");
    		}
    		return true;
    	}
    	
    	if (this.deck.isEmpty())
    	{
    		System.out.println("deck is empty");
    		return true;
    	}
    	
    	return false;
    }
    
    public boolean check_game_over() {
    	return true;
    }
    
    public static void main(String args[]) throws IOException {
    	
    	dcd_love_letter game = new dcd_love_letter();

    	game.player_num = 4; // need to be input

    	game.init_game();

        while (true)
        {
        	game.init_round();

        	while (true)
        	{
        		int cur_player_id;
        		Player p;

        		cur_player_id = game.select_player_id();

        		p = players.get(cur_player_id);
        		
        		if (p.computer == 0)
        			game.player_action(p);
        		else
        			game.computer_action(p);
        		
        		if (game.check_round_over())
        			break;
        	}
        	
        	if (game.check_game_over())
        		break;
        }
    }

}
