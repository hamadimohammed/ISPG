package Controller.Article;

import Interfaces.ObservablesControllers;

public interface ObservableArticle extends ObservablesControllers {
    public void attach(ObserverArticle observer);
    public void dettach(ObserverArticle observer);
}
