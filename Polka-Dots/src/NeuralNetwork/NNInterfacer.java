/**
 * @author Jon Wu & Stephen Chern
 */
package NeuralNetwork;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

import cabinet.GameState;
import game.Location;
import game.Move;
import game.Player;

/**
 * @author Jon Wu & Stephen Chern
 *
 */
public class NNInterfacer {

	NeuralNetwork NN;

	final int weights=5482;
	Player thisPlayer;

	public NNInterfacer(File f,Player p) {
		thisPlayer=p;
		ArrayDeque<Float> weightQueue=new ArrayDeque<Float>(weights);

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(f));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		for(int i=0;i<weights;i++) {
			weightQueue.add(getFloat(in));
		}


		NN=new NeuralNetwork(weightQueue);
	}


	public int evaluate(GameState gs) {
		float[] Inputs=new float[64];
		//GET THE INPUT ARRAY FROM THE GAMESTATE
		Location location=new Location();
		for(int r=0;r<8;r++) {
			for(int c=0;c<8;c++) {
				location.x=c;
				location.y=r;
				if(gs.getOwner(location).isEmpty()) {//If unowned
					Inputs[8*r+c]=0;
				}
				else if(gs.getOwner(location).get(0).get(0)==thisPlayer) {//If owned by AI -- happy
					Inputs[8*r+c]=1;
				}
				else {//If owned by non-AI -- sad
					Inputs[8*r+c]=-1;
				}
			}
		}
		
		ArrayList<Float> outputs=NN.evaluate(Inputs); 
		
		int maxLocation=-1;
		float maxValue=-Float.MAX_VALUE;
		
		List<Move> moveSet=gs.getValidMoves();
		ArrayList<Integer> validMoves=new ArrayList<Integer>();
		
		for(int i=0;i<moveSet.size();i++) {
			Move move=moveSet.get(i);
			validMoves.add(move.to.x*8+move.to.y);
		}
		
		for(int k=0;k<validMoves.size();k++) {
			if(outputs.get(validMoves.get(k))>maxValue) {
				maxValue=outputs.get(validMoves.get(k));
				maxLocation=validMoves.get(k);
			}
		}
		//GET BEST OUTPUT
		/*
		if(maxLocation==-1) {
			System.out.println("BAD THINGS");
		}*/
		
		return maxLocation;
	}

	private float getFloat(BufferedReader in) {
		char current=(char)-1;
		StringBuilder string=new StringBuilder();
		try {
			while((current=(char)in.read())!=' ') {
				string.append(current);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
			return (float)-1;
		}
		return Float.parseFloat(string.toString());
	}
}
