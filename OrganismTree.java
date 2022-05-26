//Hadi Salameh
//#112110954
//R05

public class OrganismTree {

    //Member Variables
    private OrganismNode root;
    private OrganismNode cursor;

    /**
     * Brief:
     * Constructor that creates a new OrganismTree with apexPredator as the root. Creating other constructor(s) with other parameters is fine.
     * Parameter:
     * apexPredator The apex predator of the food pyramid. This must be an animal, as plants cannot be predators. The user will be asked information about the apex predator upon the start of the program.
     * Postcondition:
     * An OrganismTree object is made, with apexPredator representing the apex predator. Both root and cursor reference this node.
     */
    //Constructors
    public OrganismTree(OrganismNode apexPredator) {
        root = apexPredator;
        cursor = apexPredator;
    }

    //Setter methods
    public void setRoot(OrganismNode root) {
        this.root = root;
    }

    public void setCursor(OrganismNode cursor) {
        this.cursor = cursor;
    }

    //Getter methods
    public OrganismNode getRoot() {
        return root;
    }

    public OrganismNode getCursor() {
        return cursor;
    }

    /**
     * Brief: Moves the cursor back to the root of the tree.
     * Postcondition: cursor now references the root of the tree.
     */
    //cursorReset() method
    public void cursorReset() {
        cursor = root;
    }

    /**
     * Brief: Moves cursor to one of cursor’s children.
     * Parameter:
     * name The name of the node to be moved to.
     * Precondition: name references a valid name of one of cursor’s children.
     * Postcondition: Either an exception is thrown, or cursor now points to the node whose name is referenced by name, and cursor now points to a child of the original cursor reference.
     * Throws:
     * IllegalArgumentException: Thrown if name does not reference a direct, valid child of cursor.
     */
    //moveCursor() method
    public void moveCursor(String name) throws IllegalArgumentException {
        //if the child to move to is the left child node
        if(cursor.getLeft().getName().equalsIgnoreCase(name)) {
            cursor = cursor.getLeft();
        }
        //if the child to move to is the middle child node
        else if(cursor.getMiddle().getName().equalsIgnoreCase(name)) {
            cursor = cursor.getMiddle();
        }
        //if the child to move to is the right child node
        else if(cursor.getRight().getName().equalsIgnoreCase(name)) {
            cursor = cursor.getRight();
        }
        //if the child to move to is in none of the cursor's child nodes
        else {
            throw new IllegalArgumentException();
        }
    }

    /**
     * Brief: Returns a String including the organism at cursor and all its possible prey (i.e. in the case of if cursor was at the root of the tree in the diagram above: bald eagle -> python, carp, raccoon).
     * Postcondition: cursor has not moved.
     * Returns: A String containing the name of the cursor, and all the cursor’s possible prey.
     * Throws:
     * IsPlantException: Thrown if the cursor currently references a plant object.
     */
    //listPrey() method
    public String listPrey() {    //throws IsPlantException
        String list = "";
        list += cursor.getName() + " -> ";
        //if there is a left child node
        if(cursor.getLeft() != null) {
            list += cursor.getLeft().getName();
        }
        //if there is a middle child node
        if(cursor.getMiddle() != null) {
            list += ", " + cursor.getMiddle().getName();
        }
        //if there is a right child node
        if (cursor.getRight() != null) {
            list += ", " + cursor.getRight().getName();
        }
        return list;
    }

    /**
     * Brief: Returns a String containing the path of organisms that leads from the apex predator (root) to the organism at cursor.
     * Postcondition: cursor has not moved.
     * Returns: A String containing the food chain from the apex predator to the cursor.
     */
    //listFoodChain() method
    public String listFoodChain() {
        String foodChain = "";
        OrganismNode temp = cursor;
        String[] array = new String[100];
        int counter = 0;
        while(cursor.getParent() != null) {
            array[counter] = cursor.getParent().getName();
            cursor = cursor.getParent();
            counter++;
        }
        for(int i = array.length-1; i >= 0; i--) {
            if(array[i] != null) {
                foodChain += array[i] + " -> ";
            }
        }
        cursor = temp;
        foodChain += cursor.getName();
        return foodChain;
    }

    /**
     *Brief: Prints out a layered, indented tree by performing a preorder traversal starting at cursor.
     * The cursor should act as the root of the printed tree, per se, but the root reference does not move.
     * Postconditions:
     * cursor has not moved.
     * root has not moved.
     */
    //printOrganismTree() method
    public void printOrganismTree() {
        OrganismNode temp = cursor;
        temp.preOrder(temp);
    }

    /**
     * Brief: Returns a list of all plants currently at cursor and beneath cursor in the food pyramid.
     * Postconditions:
     * cursor has not moved.
     * root has not moved.
     * Returns: A String containing a list of all the plants in the food pyramid.
     */
    //listAllPlants() method
    public String listAllPlants() {
        OrganismNode temp = cursor;
        String plants = "";
        plants = temp.traverse(temp, plants);
        return plants;
    }

    /**
     * Brief: Creates a new animal node with a specific name and diet and adds it as a child of the cursor node.
     * Parameters:
     * name The name of the child node.
     * isHerbivore value depending on whether the animal consumes plants.
     * isCarnivore value depending on whether the animal consumes other animals.
     * Preconditions:
     * name does not reference another direct child of the cursor (a.k.a. it does not share an exact name with one of its would-be siblings).
     * The cursor has an available position for another child node.
     * Postconditions:
     * Either an exception is thrown, or a new animal node named name is added as a child of the cursor with a specific diet.
     * The cursor does not move.
     * Throws:
     * IllegalArgumentException: Thrown if name references an exact name with one of its would-be siblings.
     * PositionNotAvailableExcpetion: Thrown if there is no available child position for a new node to be added.
     */
    //addAnimalChild() method
    public void addAnimalChild(String name, boolean isHerbivore, boolean isCarnivore) {
        OrganismNode node = new OrganismNode(name, isHerbivore, isCarnivore);
        //if the right child exists and a new child is trying to be added, throw an exception
        if(cursor.getRight() != null) {
            try {
                cursor.addPrey(node);
                //add parent node to child node
                node.setParent(cursor);
            } catch(PositionNotAvailableException pnae) {
                System.out.println(pnae.getMessage());
            }
              catch(IsPlantException ipe) {
                System.out.println(ipe.getMessage());
              }
        }
        //if the new child does not have the same name as one of the cursor's children and there is less than 3 children, add it as a new child
        else {
            try {
                cursor.addPrey(node);
                //add parent node to child node
                node.setParent(cursor);
            } catch(PositionNotAvailableException pnae) {
                System.out.println(pnae.getMessage());
            }
              catch(IsPlantException ipe) {
                System.out.println(ipe.getMessage());
              }
        }
    }

    /**
     * Brief:
     * Creates a new plant node with a specific name and adds it as a child of the cursor node.
     * Parameter:
     * name The name of the child node.
     * Preconditions:
     * name does not reference another direct child of the cursor (a.k.a. it does not share an exact name with one of its would-be siblings).
     * The cursor has an available position for another child node.
     * Postconditions:
     * Either an exception is thrown, or a new plant node named name is added as a child of the cursor.
     * The cursor does not move.
     * Throws:
     * IllegalArgumentException: Thrown if name references an exact name with one of its would-be siblings.
     * PositionNotAvailableExcpetion: Thrown if there is no available child position for a new node to be added.
     */
    //addPlantChild() method
    public void addPlantChild(String name) {
        OrganismNode node = new OrganismNode();
        node.setName(name);
        node.setPlant(true);
        //if the right child exists and a new child is trying to be added, throw an exception
        if(cursor.getRight() != null) {
            try {
                cursor.addPrey(node);
                //add parent node to child node
                node.setParent(cursor);
            } catch(PositionNotAvailableException pnae) {
                System.out.println(pnae.getMessage());
            }
              catch(IsPlantException ipe) {
                System.out.println(ipe.getMessage());
              }
        }
        //if the new child does not have the same name as one of the cursor's children and there is less than 3 children, add it as a new child
        else {
            try {
                cursor.addPrey(node);
                //add parent node to child node
                node.setParent(cursor);
            } catch(PositionNotAvailableException pnae) {
                System.out.println(pnae.getMessage());
            }
              catch(IsPlantException ipe) {
                System.out.println(ipe.getMessage());
              }
        }
    }

    /**
     * Brief:
     * Removes the child node of cursor with name name, and properly shifts the deleted node’s other siblings if necessary. If the deleted node has any descendants, those nodes are deleted as well.
     * Parameter:
     * name The name of the node to be deleted.
     * Precondition:
     * name references a direct child of the cursor.
     * Postconditions:
     * The child node of cursor with name name has been removed, and all the deleted node’s descendants have been removed from the tree as well.
     * The deleted node’s siblings are shifted if necessary (i.e. if left is removed, and middle and right are both occupied, then middle will shift to the left position, and right will shift to the middle position).
     * cursor has not moved.
     * Throws:
     * IllegalArgumentException: Thrown if name does not reference a direct child of the cursor.
     */
    //removeChild() method
    public void removeChild(String name) throws IllegalArgumentException {
        //if the child to remove isn't a child of the cursor
        try {
            if (name.equalsIgnoreCase(cursor.getLeft().getName()) && name.equalsIgnoreCase(cursor.getMiddle().getName()) && name.equalsIgnoreCase(cursor.getRight().getName())) {
                throw new IllegalArgumentException();
            }
        } catch(NullPointerException npe) {
            if(cursor.getLeft() == null) {
                throw new IllegalArgumentException();
            }
        }
        //if the child to remove is the cursor's left child
        if(cursor.getLeft() != null) {
            if (name.equalsIgnoreCase(cursor.getLeft().getName())) {
                //if the middle child exists, shift it to the left child
                if (cursor.getMiddle() != null) {
                    cursor.setLeft(cursor.getMiddle());
                    //if the right child exists, shift it to the middle child
                    if (cursor.getRight() != null) {
                        cursor.setMiddle(cursor.getRight());
                        cursor.setRight(null);
                    }
                }
                //if there is no middle child, set the left child to null
                else {
                    cursor.setLeft(null);
                }
            }
        }
        //if the child to remove is the middle child
        if(cursor.getMiddle() != null) {
            if (name.equalsIgnoreCase(cursor.getMiddle().getName())) {
                //if the right child exists, shift it to the middle child
                if (cursor.getRight() != null) {
                    cursor.setMiddle(cursor.getRight());
                    cursor.setRight(null);
                }
                //if there is no right child, set the middle child to null
                else {
                    cursor.setMiddle(null);
                }
            }
        }
        //if the child to remove is the right child
        if(cursor.getRight() != null) {
            if (name.equalsIgnoreCase(cursor.getRight().getName())) {
                cursor.setRight(null);
            }
        }
    }

}

