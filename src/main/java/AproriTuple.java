import java.util.ArrayList;

public class AproriTuple implements Comparable<AproriTuple> {

    public ArrayList<Integer> getMyNumber() {
        return myNumber;
    }

    private ArrayList<Integer> myNumber = new ArrayList<>();
    int myOccurence;
    public Double myConfMin;

    public AproriTuple(ArrayList<Integer> myNumber, int myOccurence) {
        this.myNumber = myNumber;
        this.myOccurence = myOccurence;
    }

    @Override
    public String toString() {
        return myNumber + " => " + myOccurence;
    }

    @Override
    public int compareTo(AproriTuple o) {
        return  this.getMyNumber().size() - o.getMyNumber().size() ;
    }

    public boolean isContainsIn(AproriTuple aprioriTuple)
    {
        for(int i=0; i < this.myNumber.size(); ++i)
        {
            boolean contenue = false;
            for (int j = 0; j < aprioriTuple.myNumber.size() ; j++) {
                if (this.myNumber.get(i) == aprioriTuple.myNumber.get(j))
                {
                    contenue = true;
                }
            }
            if(!contenue)return false;
        }
        return true;
    }

    public String getMyNumberInString() {
        String str = "";
        for (Integer integer : this.getMyNumber())
        {
            str += integer + " ";
        }
        return str;
    }
}
