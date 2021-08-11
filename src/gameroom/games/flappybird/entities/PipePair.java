package gameroom.games.flappybird.entities;

import java.awt.Image;
import javax.swing.ImageIcon;

public class PipePair {
    
    public Collider upperPipeCollider = new Collider();
    public Collider lowerPipeCollider = new Collider();
    private Image upperPipeSprite;
    private String upperPipePath;
    private Image lowerPipeSprite;
    private String lowerPipePath;
    private int upperPipeYSpawn;
    private int lowerPipeYSpawn;
    private int pipesXPosition;
    private int pipesXSpawn;
    private int pipesWidth = 120;
    
    public PipePair(String _upperPipePath, String _lowerPipePath, int _pipesXSpawn){
        this.upperPipePath = _upperPipePath;
        this.lowerPipePath = _lowerPipePath;
        this.upperPipeSprite = new ImageIcon(_upperPipePath).getImage();
        this.lowerPipeSprite = new ImageIcon(_lowerPipePath).getImage();
        this.upperPipeYSpawn = 0;
        this.lowerPipeYSpawn = 555 - getPipeHeight(_lowerPipePath);
        this.pipesXSpawn = _pipesXSpawn;
        this.pipesXPosition = _pipesXSpawn;
        lowerPipeCollider.newCollider(pipesXPosition, lowerPipeYSpawn, pipesWidth, getPipeHeight(_lowerPipePath));
        upperPipeCollider.newCollider(pipesXPosition, upperPipeYSpawn, pipesWidth, getPipeHeight(_upperPipePath));
    }
    
    public String getPipeSpritePath(String _pipe){
        switch(_pipe){
            case "upperPipePath":
                return this.upperPipePath;
            case "lowerPipePath":
                return this.lowerPipePath;
            default:
                return "";
        }
    }
    
    public int getPipeHeight(String _pipePath){
        String height = _pipePath.split("\\\\")[3].split("_")[0];
        return Integer.parseInt(height);
    }
    
    public int getUpperPipeYSpawn(){
        return this.upperPipeYSpawn;
    }
    
    public int getLowerPipeYSpawn(){
        return this.lowerPipeYSpawn;
    }
    
    public Image getUpperPipeSprite(){
        return this.upperPipeSprite;
    }
    
    public Image getLowerPipeSprite(){
        return this.lowerPipeSprite;
    }
    
    public int getPipesXPosition(){
        return this.pipesXPosition;
    }
    
    public void setPipesXPosition(int _newPipesXPosition){
        this.pipesXPosition = _newPipesXPosition;
    }
    
    public void movePipes(){
        setPipesXPosition(getPipesXPosition() - 15);
        upperPipeCollider.updateCollider(getPipesXPosition(), upperPipeYSpawn, pipesWidth, getPipeHeight(getUpperPipePath()));
        lowerPipeCollider.updateCollider(getPipesXPosition(), lowerPipeYSpawn, pipesWidth, getPipeHeight(getLowerPipePath()));
    }
    
    public String getUpperPipePath(){
        return this.upperPipePath;
    }
    
    public String getLowerPipePath(){
        return this.lowerPipePath;
    }
    
}
