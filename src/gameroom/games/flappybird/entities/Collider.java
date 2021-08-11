
package gameroom.games.flappybird.entities;

import java.util.List;
import java.util.ArrayList;


public class Collider {

    public int x;
    public int y;
    public int width;
    public int height;
    
    public List<List> colliderArea = new ArrayList<>();
    
    public void newCollider(int _x, int _y, int _width, int _height){
        
        this.x = _x;
        this.y = _y;
        this.width = _width;
        this.height = _height;
    }
    
    public List<List> getColliderArea(){
        return this.colliderArea;
    }
    
    public void updateCollider(int _x, int _y, int _width, int _height){
        newCollider(_x, _y, _width, _height);
    }
    
    public boolean onCollision(Collider collider){
        
        if((this.x + this.width ) - collider.x >= 0 && (collider.x + collider.width) - this.x >= 0){
            if((this.y + this.height ) - collider.y >= 0 && (collider.y + collider.height) - this.y >= 0){
                return true;
            }
        }else{
            return false;
        }
        return false;
    }
}
