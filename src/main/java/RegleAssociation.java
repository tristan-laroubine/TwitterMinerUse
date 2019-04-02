import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RegleAssociation {
    private AproriTuple myBefore;

    private AproriTuple myAfter;


    private Double minConfiance;

    public RegleAssociation(AproriTuple myBefore, AproriTuple myAfter, Double minConfiance) {
        this.myBefore = myBefore;
        this.myAfter = myAfter;
        this.minConfiance = minConfiance;
    }



    public boolean isRightRuleAssociation()
    {
        if ((double)myAfter.myOccurence / (double) myBefore.myOccurence >= minConfiance)
        {
            return true;
        }
        return false;
    }


    @Override
    public String toString() {
        return myBefore.getMyNumberInString() + "| " + myAfter.getMyNumberInString() + "(" + ((double) myAfter.myOccurence / (double) myBefore.myOccurence)+")";
    }
}
