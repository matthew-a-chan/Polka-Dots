/**
 * @author Jon Wu & Stephen Chern
 */
package NeuralNetwork;

import java.io.File;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Vector;


/**
 * @author Matthew Chan
 *
 */
class NeuralNetwork {

	final int layers=3;
	final int[] layerSizes= {64,42,64};	


	private ArrayList<ArrayList<ArrayList<Float>>>Weight;
	private ArrayList<ArrayList<Float>>Node;




	protected NeuralNetwork(ArrayDeque<Float> weights) {


		Weight=new ArrayList<ArrayList<ArrayList<Float>>>(2);//ijk
		Node=new ArrayList<ArrayList<Float>>(3);

		
		
		for(int i=0;i<layers;i++) {
			Node.add(i, new ArrayList<Float>(layerSizes[i]+1));
			//Set getNodeLayer(i).set(layerSizes[i],1) FOR BIAS
			
			ArrayList<Float>currentLayer=Node.get(i);
			
			for(int k=0;k<layerSizes[i];k++) {
				currentLayer.add(0f);
			}
			if(i<layers-1)
				currentLayer.add(1f);
		}
		
		
		for(int i=0;i<layers-1;i++) {//Establish layers to IK Level
			Weight.add(i,new ArrayList<ArrayList<Float>>(layerSizes[i+1]));
		}
		


		int count=0;
		for(int i=1;i<layers;i++) {//Establish weights to IKJ Level
			for(int k=0;k<layerSizes[i];k++) {
				Weight.get(i-1).add(k, new ArrayList<Float>(layerSizes[i-1]+1));
				
				ArrayList<Float> currentWeights=getInputWeights(i-1,k);
				for(int j=0;j<layerSizes[i-1]+1;j++) {
					currentWeights.add(j,weights.poll());
					if(currentWeights.get(j)==null) {
						System.err.println("THIS WEIGHT IS INITIALIZED AS NULL!!!i"+i+":k"+k+":j"+j);
						System.exit(-1);
					}
					count++;
				}
				//+1 bc getInputWeights(i,k).get(layerSizes[i-1]) is the bias
			}
		}

		//Read from file and put values into the things :/ :D D: :P d:
	}

	protected ArrayList<Float> evaluate(float[] state) {

		for(int k=0;k<layerSizes[0];k++) {
			getNodeLayer(0).set(k,state[k]);
		}

		feedForward();

		return getNodeLayer(layers-1);
	}

	private void feedForward() {
		for(int i=1;i<layers;i++) {
			
			ArrayList<Float> currentLayer=getNodeLayer(i);
			ArrayList<Float> lastLayer=getNodeLayer(i-1);
			ArrayList<ArrayList<Float>> currentWeightLayer=Weight.get(i-1);
			
			for(int k=0;k<layerSizes[i];k++) {
				currentLayer.set(k,dotActive(lastLayer,currentWeightLayer.get(k)));
			}
		}
	}

	private float dotActive(ArrayList<Float> a,ArrayList<Float> b) {
		float dot = 0;

		// FOR DEBUGGING ONLY-- DELETE LATER ----------------------------
	/*	if(a.size()!=b.size()) {
			System.err.println("ERROR: VECTOR LENGTH MISMATCH IN DOT");
			System.exit(-1);
		}
*/
		for(int alpha=0;alpha<a.size();alpha++) {
			float aValue=a.get(alpha);
			for(int beta=0;beta<a.size();beta++) {
				dot+=aValue*b.get(beta);
			}
		}
		
		return (float)(Math.tanh(dot));
	}

	private ArrayList<Float> getNodeLayer(int layer) {
		return Node.get(layer);
	}

	private ArrayList<Float> getInputWeights(int layer,int node){
		return Weight.get(layer).get(node);
	}

}
