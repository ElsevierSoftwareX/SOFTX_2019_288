package pcell.model;

//import cern.colt.list.tint.IntArrayList;
//import cern.colt.matrix.tdouble.DoubleMatrix2D;
import cern.colt.list.IntArrayList;
import cern.colt.matrix.DoubleMatrix2D;
import com.google.common.graph.EndpointPair;

import java.util.List;
import java.util.Set;


public abstract class ANN extends Model implements Comparable<ANN> {
    static int global_id = -1;
    public int bias_id = -1;
    int id;

    public void setId() {
        this.id = ++global_id;
    }

    public abstract void buildFromTemplate(ANN copy, boolean clone);

    public abstract ANN buildFromFitnessFunction(int nInputs, int nOutputs);

    public abstract int addRandomNeuron();

    public abstract void addNeuron(int neuronID);

    public abstract void deleteNeuron();

    public abstract void deleteNeuron(int neuronID);

    public abstract void deleteConnection();

    public abstract void deleteConnection(int originID, int destinyID);

    public abstract void addConnection();

    public abstract void addConnection(int originID, int destinyID);
    
    public abstract void removeConnection(int originID, int destinyID);

    public abstract void addConnection(int originID, int destinyID, double weight);

    public abstract int selectRandomActiveOrInputNeuron();

    public abstract int addRandomConnection();

    public abstract int selectRandomDisableNeuron();

    public abstract List<Integer> getHiddenNeurons();

    public abstract int selectRandomHiddenNeuron(boolean active);

    public abstract int selectRandomUpperNeuron(int id, boolean active);

    public abstract int selectRandomLowerNeuron(int id, boolean active);

    public abstract int selectRandomActiveLowerNeurons(int id, boolean active);
   
    
    public abstract Set<Integer> getPredecessorNeuronsOf(int id);
    
    public abstract Set<Integer> getSucessorNeuronsOf(int id);

//    public abstract boolean isInput();

    public abstract boolean isInput(int id);

    public abstract double getBias(int id);

//    public abstract double getActives(int id);

    public abstract IntArrayList getDestinies(int neuronID);

    public abstract Set<Integer> getDestiniesSet(int neuronID);

    public abstract void setBias(int id, double bias);

    public abstract void update();

    public abstract String toDot();

    public abstract void changeConnection();

    public abstract void changeConnection(int originID, int destinyID);

    public abstract DoubleMatrix2D getMatrixWeights();

    public abstract int getNumberOfNeurons();

    public abstract ANN clone();

    public abstract ANN cloneEmpty();

    public abstract ANN buildRandomANN(int nInputs, int nOutputs, int maxSize);

    public abstract Set<EndpointPair<Integer>> getEdges();

    public abstract double weight(Integer source, Integer target);

    public double getFitness() {
        return fitness;
    }

    public void setFitness(double fitness) {
        this.fitness = fitness;
    }

    @Override
    public int compareTo(ANN o) {
        if (fitness == o.fitness) return 0;
        return (fitness < o.fitness) ? -1 : 1;
    }

    public int getID() {
        return id;
    }

    public abstract EndpointPair<Integer> selectRandomWeight();

    public abstract Set<Integer> getNodes();

    public abstract boolean connectionExist(int origin, int destiny);

    public abstract double getWeight(int origin, int destiny);

    public abstract Set<Integer> getInputs();

    public abstract void addBias(int new_node, double bias);

    protected abstract void addBias(int new_node);

    public abstract boolean isOutput(int origin);

    public abstract Set<Integer> getActiveInputs();

    public abstract boolean isActive(int destiny);

    public abstract int getNumberOfConnections();
}
