package AI;
import java.io.File;
import java.util.List;

import Genetics.Individual;
import NeuralNetwork.NNInterfacer;
import cabinet.GameState;
import cabinet.Plugin;
import cabinet.PluginInfo;
import game.*;

/**
 * the framework of the AI
 * creates a MiniMaxShrubbery, regardless if it is given a file
 */
public class OthelloAI extends Player{
	
	File f;
	Individual i=null;
	String name="DaOthelloAI";

	NNInterfacer NN;
	
	public static PluginInfo getInfo() {
		PluginInfo pi = new PluginInfo() {

			@Override
			public String name() {
				return "OthelloAI";
			}

			@Override
			public String description() {
				return "An AI for Othello";
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

	public OthelloAI(File file) {
		NN=new NNInterfacer(file,(Player)this);
	}
	
	public OthelloAI(File file,Individual indiv) {
		this(file);
		this.i=indiv;
	}
	
	public Individual getIndividual() {
		return i;
	}

	public String getName(){
		return name;
	}
	
	public void setName(String name) {
		this.name=name;
	}

	@Override
	public void makeMove(GameState gs,List<Move> m){
		if(gs.getValidMoves().size()==0) {
			return;
		}

		int moveNum=NN.evaluate(gs);
		
		//printGS(gs);
		
		Move move=null;
		m.add(move=new Move());
		
		move.to.x=moveNum/8;
		move.to.y=moveNum%8;
		
		
		if(moveNum==-1) {
			move.to.x=gs.getValidMoves().get(0).to.x;
			move.to.y=gs.getValidMoves().get(0).to.y;
			//System.err.println("COULDNT SEARCH:: RANDOM MOVE--"+gs.isGameOver());
		}
		
		
		
		
		
		
		
		/*try {
			Thread.sleep(500);
		}
		catch(InterruptedException e) {};
		*/
		
		
		
		
		
		
	}
	
	private void printGS(GameState gs) {
		System.out.println("TEAM:"+gs.getPlayers()[0].contains(this));
		System.out.println("MOVES AVAILABLE::"+gs.getValidMoves().size());
		Location location=new Location();
		for(int r=0;r<8;r++) {
			for(int c=0;c<8;c++) {
				location.x=c;
				location.y=r;
				if(gs.getOwner(location).isEmpty()) {//If unowned
					System.out.print(0);
				}
				else if(gs.getOwner(location).get(0).get(0)==gs.getPlayers()[0].get(0)) {//If owned by AI -- happy
					System.out.print("Y");
				}
				else {//If owned by non-AI -- sad
					System.out.print("N");
				}
				System.out.print(" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
	}
	
}