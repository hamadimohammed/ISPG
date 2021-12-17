package Application;

import java.util.ArrayList;

public class ObservePrimaryStage {
    ArrayList<ObserverStage> opened_stage;

    public ObservePrimaryStage(){
        opened_stage=new ArrayList<ObserverStage>();
    }

    public void attach(ObserverStage o){
        opened_stage.add(o);
    }
    public void dettach(ObserverStage o){
        opened_stage.remove(o);
    }

    public void notifyObservateur(){
        for(ObserverStage o:opened_stage)
            o.close();
    }
    
}
