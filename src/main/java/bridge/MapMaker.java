package bridge;// @ author ninaaano

import bridge.view.InputView;
import bridge.view.MapView;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MapMaker {
    private final StringBuilder upState = new StringBuilder();
    private final StringBuilder downState = new StringBuilder();

    private Queue<String> bridge;

    public MapMaker(List<String> bridge) {
        this.bridge = new LinkedList<>(bridge);
    }

    // boolean의 기본으 false다
    public void addMap(boolean answer){
        if(answer){
            upState.append(MapView.MAP_MIDDLE);
            upState.append(MapView.MAP_MIDDLE);
        }
        if(answer)
            addMapO(bridge.remove());
        if(!answer)
            addMapX(bridge.remove());

    }

    public void addMapO(String input){
        if(input.equals("U")){
            upState.append(MapView.MAP_O);
            downState.append(MapView.MAP_BLANK);
        }
        if(input.equals("D")){
            upState.append(MapView.MAP_BLANK);
            downState.append(MapView.MAP_O);
        }
    }

    public void addMapX(String input){
        if(input.equals("U")){
            upState.append(MapView.MAP_X);
            downState.append(MapView.MAP_BLANK);
        }
        if(input.equals("D")){
            upState.append(MapView.MAP_BLANK);
            downState.append(MapView.MAP_X);
        }
    }

    public List<String> printMap(){
        return List.of("[ "+upState+" ]","[ "+downState+" ]");
    }

}
