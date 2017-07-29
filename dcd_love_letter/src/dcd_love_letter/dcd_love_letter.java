package dcd_love_letter;

public class dcd_love_letter {

	public int player_num;
	public int turn;
	
    public void init_game () {
        
    }

    public void init_round (){
        turn = 0;
    }
    
    public int select_player () {
    	int player;
    	
    	player = 0; // 0, 1, 2, 3
    	
        return player;
    }
    
    public void player_action () {
    	
    }
    
    public void computer_action () {
    	
    }
    
    public boolean check_round_over () {
    	return true;
    }
    
    public boolean check_game_over () {
    	return true;
    }
    
    public static void main (String args[]) {
    	
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
