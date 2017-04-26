/**
 * Created by Barnabas_Ravindranath on 4/19/17.
 */
//node class
public class AVLNODE {

    AVLNODE left, right;
   
    public int height, value;

    //constructor
    public AVLNODE(int s){
        left = null;
        right = null;
        height = 0;
        value = s;
    }

    //sets node to lef
    public void setLeft(AVLNODE n){

        left = n;

    }
    //sets node to right node
    public void setRight(AVLNODE n) {

        right = n;
    }

    //gets pointer to left node
    public AVLNODE getLeft(){

        return left;

    }

    //gets pointer to right node
    public AVLNODE getRight(){

        return right;

    }

    //increments the height of value
    public void increaseFrequency(int n) {

        height = height + n;

    }

    public int decreaseFrequency(int n) {

        return height = height - n;

    }

    //sets the string value to new string s t
    public void setvalue(int s) {

        value = s;

    }

    //returns height of an individual node
    public int getHeight(){

        return height;

    }

    //returns value of an individual node
    public int getvalue(){

        return value;

    }


}

