package dcd_love_letter;

//import java.io.BufferedReader;
import java.io.IOException;
//import java.io.InputStreamReader;
import java.util.Random;
import java.util.ArrayList;
import java.util.Collections;

public class dcd_love_letter {

	public int player_num;
	public int player_id;
	public int turn;
	public int[] score = new int[4];
	public ArrayList<Integer> deck = new ArrayList<Integer>();
	
	public int get_int_input() throws IOException {
		return System.in.read() - 48;
	}
	
//	input:
//		number of player (2~4) -> save at member variable
//	output: 
//		none
    public void init_game() throws IOException {
    	int i;
    	Random generator = new Random();
    	
    	System.out.print("input player number(2~4): ");
        this.player_num = this.get_int_input();
        //System.out.println("player number: " + this.player_num);
        
        this.player_id = generator.nextInt(this.player_num);
        
        System.out.println("your id: " + this.player_id);
        
        // init score
        for (i = 0; i < this.player_num; i++)
        {
        	this.score[i] = 0;
        }
        
        shuffle_deck();

        // test shuffle & draw a card
//        while (!deck.isEmpty())
//        {
//        	System.out.print(draw_a_card());
//        }
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
        turn = 0;
    }
    
    // asfsfs
    
    public int select_player() {
    	int player;
    	
    	player = 0; // 0, 1, 2, 3
    	
        return player;
    }
    
    public void player_action() {
    	
    }
    
    public void computer_action() {
    	
    }
    
    public boolean check_round_over() {
    	return true;
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
        		int cur_player;

        		cur_player = game.select_player();
        		
        		if (cur_player == 0)
        			game.player_action();
        		else
        			game.computer_action();
        		
        		if (game.check_round_over())
        			break;
        	}
        	
        	if (game.check_game_over())
        		break;
        }
    }

}
