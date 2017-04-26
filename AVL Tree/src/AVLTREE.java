/**
 * Created by Barnabas_Ravindranath on 4/19/17.
 */


//Binary Search Tree Class
public class AVLTREE {

    //used to check imbalance when deleting node
    private static final int ALLOWED_IMBALANCE = 1;

    //creates node class
    private AVLNODE root;

    //Main Constructor sets Root to Null
    public AVLTREE() { root = null; }

    //Calls private method get_node_depth
    public int get_node_depth(int val) { return get_node_depth(root, val, 0); }

    //Calls the private delete method
    public void delete(int s) { root =  delete(root, s); }

    //Calls the private method count nodes and returns integer of how many nodes in Binary Tree
    public int countNodes() {

        return countNodes(root);

    }

    //Calls the private insertnode method
    public void insertnode(int s) {

        root = insertnode(root, s);

    }

    //Calls private method search and returns True/False
    public boolean search(int val) {

        return search(root, val);

    }

    //Calls private method that will display Pre-Order traversal method
    public void preorder() {

        preorder(root);

    }

    //Calls private method that will display In-Order traversal method
    public void inorder() {

        inorder(root);

    }

    //Calls private method that will display Post-Order traversal method
    public void postorder() {
        postorder(root);
    }

    //recursively checks and places the new node in its correct order
    private AVLNODE insertnode(AVLNODE n, int s) {

        if (n == null) {
            n = new AVLNODE(s);

        }

        else
        {
            //left
            if (s < n.getvalue()) { n.left = insertnode(n.left, s); }

            //right
            else if (s > n.getvalue()) { n.right = insertnode(n.right, s); }

            //updates hight
            else { n.height = max(height(n.getLeft()), height(n.getRight())+1); }

        }

        //balances node
        return Balance(n);
    }

    //Finds max between left/right node
    private int max(int left, int right)
    {
        return left > right ? left : right;
    }

    //Case 1 Single rotate implementation
    private AVLNODE singlerotationLeft(AVLNODE k2){

        AVLNODE k1 = k2.getLeft();
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right) + 1);
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    //Case 4 Single rotate implementation
    private AVLNODE singlerotationRight(AVLNODE k1){

        AVLNODE k2 = k1.getRight();
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right) + 1);
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }

    //Case 2 Double rotate implementation
    private AVLNODE doublerotationLeft (AVLNODE k3) {

        k3.left = singlerotationRight(k3.left);
        return singlerotationLeft(k3);

    }

    //Case 3 Double rotate implementation
    private AVLNODE doublerotationRight (AVLNODE k1) {
        k1.right = singlerotationLeft(k1.right);
        return singlerotationRight(k1);
    }

    //returns height of node
    private int height(AVLNODE node) {

        if (node == null) return -1;

        return node.getHeight();

    }

    //searches and deletes correct node
    private AVLNODE delete(AVLNODE n, int s) {

        //base case: if tree is empy
        if (root == null)
        {
            return root;
        }
        else {  //recursively find node to delete

            if (s < n.getvalue()) { //left
                n.left = delete(n.getLeft(), s);


            } else if (s > n.getvalue()) { //right
                n.right = delete(n.getRight(), s);

            }

            else if (n.getLeft() != null && n.getRight() != null) { //Node has two children


                //the successor is the smallest value in the right subtree
                n.value = findMinValue(n.right);

                //deletes the inorder successor
                n.right = delete(n.right, n.getvalue());

            }

            else{ //node with only one child or no child

                n = n.getLeft() != null ? n.getLeft() : n.getRight();
            }

            //call to balance node
            return Balance(n);

        }

    }

    //Balances the Binary Search Tree Making it an AVL Tree
    private AVLNODE Balance(AVLNODE n)
    {
        if(n == null)
            return n;

        if(height(n.getLeft()) - height(n.getRight()) > ALLOWED_IMBALANCE) {

            if (height(n.getLeft().getLeft()) >= height(n.getLeft().getRight())) {
                n = singlerotationLeft(n);
            } else {
                n = doublerotationLeft(n);
            }
        }
        else {
            if (height(n.getRight()) - height(n.getLeft()) > ALLOWED_IMBALANCE){

                if(height(n.getRight().getRight()) >= height(n.getRight().getLeft())){
                    n = singlerotationRight(n);
                }
                else{
                    n = doublerotationRight(n);
                }

            }

        }
        n.height = Math.max(height(n.getLeft()), height(n.getRight())) + 1;
        return n;


    }

    //function to find minimum value in the Binary Search tree
    private int findMinValue(AVLNODE node){

        if(node != null) {

            while (node.left != null) {
                node = node.left;
            }

        }

        return node.getvalue();
    }

    //recursively checks how many nodes in tree and returns integer
    private int countNodes(AVLNODE r) {

        if (r == null)

            return 0;

        else {

            int l = 1;

            l += countNodes(r.getLeft());
            l += countNodes(r.getRight());

            return l;
        }
    }

    //Function to search for an element recursively and will update frequency of element,
    // if element already exist in Binary Search Tree
    private boolean search(AVLNODE r, int val) {

        boolean found = false;

        while ((r != null) && !found) {

            if (val < r.getvalue()) {
                r = r.getLeft();
            }

            else if (val > r.getvalue()) {
                r = r.getRight();
            }

            else {

                found = true;
                break;
            }

            found = search(r, val);
        }
        if(!found){

        }
        return found;

    }


    //Recursively displays the In-Order Traversal of the Binary Search Tree
    private void inorder(AVLNODE r) {

        if (r != null) {

            inorder(r.getLeft());

            System.out.println("Value " + r.getvalue());

            inorder(r.getRight());
        }
    }

    //Recursively displays the Pre-Order Traversal of the Binary Search Tree
    private void preorder(AVLNODE r) {

        if (r != null) {

            add_tab(get_node_depth(r.getvalue()));

            System.out.println(r.getvalue());

            preorder(r.getLeft());

            preorder(r.getRight());
        }
    }


    //Recursively displays the Post-Order Traversal of the Binary Search Tree
    private void postorder(AVLNODE r) {
        if (r != null) {
            postorder(r.getLeft());

            postorder(r.getRight());

           // System.out.println(r.getvalue() + " | Frequency: " + r.getFrequency());

        }
    }

    //calls private method to get a single nodes depth in the Binary Search Tree and returns an integer of depth

    //recursively finds an individual nodes depth
    private int get_node_depth(AVLNODE n, int val, int level) {


        if (n == null) return 0;

        else {

            if (val < n.getvalue()) return get_node_depth(n.getLeft(), val, (level + 1));

            else if (val > n.getvalue()) return get_node_depth(n.getRight(), val, (level + 1));

            else return level;

        }

    }


    //prints n tabs according to an individual nodes depth
    public void add_tab(int x){

        for(int y = 0; y <= x; y++){
            System.out.print("\t" + "- ");
        }
    }


}


