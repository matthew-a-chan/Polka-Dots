package cabinet;

import java.util.List;

import game.Move;
import game.Player;

/**
 * Template AI class for the Framework
 * @author bsea
 *
 */
public class OOTTestAI extends Player{

	/**
	 * Every plugin must provide this static method
	 * @return a filled object describing the plugin
	 */
	public static PluginInfo getInfo() {
		PluginInfo pi = new PluginInfo() {

			@Override
			public String name() {
				return "Out Of Time AI";
			}

			@Override
			public String description() {
				return "OOT AI";
			}

			@Override
			public Class<? extends Plugin> type() {
				return Player.class;
			}

			@Override
			public List<Class<? extends GameState>> supportedGames() {
				return null;
			}
			
		};
		return pi;
	}
	/**
	 * Your AI should call the super constructor and pass the AI's name
	 */
	public OOTTestAI(){
		super("OutOfTimeAI");
	}
	
	/**
	 * This method indicates which games the AI supports
	 * @return A list of class objects of games supported, or null if all games are supported
	 */
	public static List<Class<? extends GameState>> supportedGames(){
		return null;
	}
	
	/**
	 * This method is called when your AI needs to make a move.  It should fill in 'm'
	 * with the appropriate move.
	 * 
	 * <b>Note:</b>This method may be ended prematurely.  You should use the exit() method to check 
	 * see if you must exit.
	 * 
	 * @param gs the current state of the game
	 * @param m the object to fill in to represent the AIs move
	 */
	@Override
	public void makeMove( GameState gs, List<Move> m ){
		int i = 0;
		while(!exit()) {
			i++;
		}
	}
}
