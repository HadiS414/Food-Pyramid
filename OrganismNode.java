//Hadi Salameh
//#112110954
//R05

/**
 * This class defines the OrganismNode object and all of its attributes
 */

public class OrganismNode {

    //Member Variables
    private String name;
    private boolean isPlant;
    private boolean isHerbivore;
    private boolean isCarnivore;
    private OrganismNode left;
    private OrganismNode middle;
    private OrganismNode right;
    private OrganismNode parent;

    /**
     * Brief: Constructor for plant nodes
     */
    //Constructors
    public OrganismNode() {
        name = "";
        isPlant = false;
        isHerbivore = false;
        isCarnivore = false;
        left = null;
        middle = null;
        right = null;
        parent = null;
    }

    /**
     * Brief: Constructor for animal nodes
     */
    public OrganismNode(String name, boolean isHerbivore, boolean isCarnivore) {
        this.name = name;
        isPlant = false;
        this.isHerbivore = isHerbivore;
        this.isCarnivore = isCarnivore;
        left = null;
        middle = null;
        right = null;
        parent = null;
    }

    //Getter Methods
    public String getName() {
        return name;
    }

    public boolean getIsPlant() {
        return isPlant;
    }

    public boolean getIsHerbivore() {
        return isHerbivore;
    }

    public boolean getIsCarnivore() {
        return isCarnivore;
    }

    public OrganismNode getLeft() {
        return left;
    }

    public OrganismNode getMiddle() {
        return middle;
    }

    public OrganismNode getRight() {
        return right;
    }

    public OrganismNode getParent() { return parent; }

    //Setter Methods
    public void setName(String name) {
        this.name = name;
    }

    public void setPlant(boolean isPlant) {
        this.isPlant = isPlant;
    }

    public void setHerbivore(boolean isHerbivore) {
        this.isHerbivore = isHerbivore;
    }

    public void setCarnivore(boolean isCarnivore) {
        this.isCarnivore = isCarnivore;
    }

    public void setLeft(OrganismNode left) {
        this.left = left;
    }

    public void setMiddle(OrganismNode middle) {
        this.middle = middle;
    }

    public void setRight(OrganismNode right) {
        this.right = right;
    }

    public void setParent(OrganismNode parent) { this.parent = parent; }

    /**
     * Brief:
     * Adds preyNode as prey to this node.
     * Add child nodes in the following order: left filled first, middle filled second, and right filled third.
     * Parameter:
     * preyNode The OrganismNode to be added as prey of this organism.
     * Preconditions:
     * This node is not a plant.
     * At least one of the three child node positions of this node is available.
     * The type of prey correctly corresponds to the diet of this node.
     * Postcondition: Either an exception is thrown, or preyNode is added as a child of this node.
     * Throws:
     * PositionNotAvailableException: Thrown if there is no available child position for preyNode to be added (i.e. left, middle, and right are all being used).
     * IsPlantException: Thrown if this node is a plant node (Reminder: Plant nodes cannot have children).
     * DietMismatchException: Thrown if preyNode does not correctly correspond to the diet of this animal.
     */
    //addPrey() method
    public void addPrey(OrganismNode preyNode) throws PositionNotAvailableException, IsPlantException {
        //if the predator is a plant, throw an exception
        if(getIsPlant()) {
            throw new IsPlantException("\n" + "ERROR: The cursor is at a " +
                    "plant node. Plants cannot be predators." + "\n");
        }
        //if the predator is not a plant
        else {
            //if left child node is available
            if(left == null) {
                setLeft(preyNode);
            }
            //if middle child node is available
            else if(middle == null) {
                setMiddle(preyNode);
            }
            //if right child node is available
            else if(right == null) {
                setRight(preyNode);
            }
            //if all child nodes are not available
            else {
                throw new PositionNotAvailableException("\n" + "ERROR: " +
                        "There is no more room for more prey for this " +
                        "predator." + "\n");
            }
        }
    }

    /**
     * Brief: Aids the printOrganismTree() method
     * @param node - The cursor node
     */
    //preOrder() method
    public void preOrder(OrganismNode node) {
        if(!node.isPlant) {
           System.out.print("| - ");
        }
        else {
            System.out.print("- ");
        }
        System.out.println(node.getName());
        if(node.getLeft() != null) {
            preOrder(node.getLeft());
        }
        if(node.getMiddle() != null) {
            preOrder(node.getMiddle());
        }
        if(node.getRight() != null) {
            preOrder(node.getRight());
        }
    }

    /**
     * Brief: Aids the listAllPlants() method
     * @param node - The cursor node
     * @param result - The string being returned
     */
    //traverse() method
    public String traverse(OrganismNode node, String result) {
        if(node.isPlant) {
            result += node.getName();
        }
        if(node.getLeft() != null) {
            if(node.getLeft().isPlant) {
                if(result.equalsIgnoreCase("")) {
                    result += node.getLeft().getName();
                }
                else {
                    result += ", " + node.getLeft().getName();
                }
            }
            traverse(node.getLeft(), result);
        }
        if(node.getMiddle() != null) {
            if(node.getMiddle().isPlant) {
                if(result.equalsIgnoreCase("")) {
                    result += node.getMiddle().getName();
                }
                else {
                    result += ", " + node.getMiddle().getName();
                }
            }
            traverse(node.getMiddle(), result);
        }
        if(node.getRight() != null) {
            if(node.getRight().isPlant) {
                if(result.equalsIgnoreCase("")) {
                    result += node.getRight().getName();
                }
                else {
                    result += ", " + node.getRight().getName();
                }
            }
            traverse(node.getRight(), result);
        }
        return result;
    }

}
