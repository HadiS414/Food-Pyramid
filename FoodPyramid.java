//Hadi Salameh
//#112110954
//R05

import java.util.Scanner;

/**
 * The main method. This class should contain an OrganismTree member variable named tree that will serve as the tree for the user to interact with.
 */

public class FoodPyramid {

    //Member Variables
    private OrganismTree tree;

    //Constructors
    public FoodPyramid() {
        tree = null;
    }

    //Main method
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String selection = "";
        String apexName = "";
        String apexDiet = "";
        System.out.print("What is the name of the apex predator?: ");
        apexName = input.nextLine();
        System.out.print("Is the organism an herbivore / a carnivore " +
                "/ an omnivore? (H / C / O) : ");
        apexDiet = input.nextLine();
        OrganismNode apex = new OrganismNode();
        apex.setName(apexName);
        //if the user selects H, the apex is a herbivore
        if(apexDiet.equalsIgnoreCase("h")) {
            apex.setHerbivore(true);
        }
        //if the user selects C, the apex is a carnivore
        else if(apexDiet.equalsIgnoreCase("c")) {
            apex.setCarnivore(true);
        }
        //if the user selects O, the apex is a omnivore
        else if(apexDiet.equalsIgnoreCase("o")) {
            apex.setHerbivore(true);
            apex.setCarnivore(true);
        }
        OrganismTree tree = new OrganismTree(apex);
        System.out.println("\n" + "Constructing food pyramid..." + "\n");
        while(!selection.equalsIgnoreCase("q")) {
            //Menu
            System.out.println("Menu:" + "\n");
            System.out.println("(PC) - Create New Plant Child");
            System.out.println("(AC) - Create New Animal Child");
            System.out.println("(RC) - Remove Child");
            System.out.println("(P) - Print Out Cursor's Prey");
            System.out.println("(C) - Print Out Food Chain");
            System.out.println("(F) - Print Out Food Pyramid at Cursor");
            System.out.println("(LP) - List All Plants Supporting Cursor");
            System.out.println("(R) - Reset Cursor to Root");
            System.out.println("(M) - Move Cursor to Child");
            System.out.println("(Q) - Quit" + "\n");
            System.out.print("Please select an option: ");
            selection = input.nextLine();

            // (PC) - Create New Plant Child
            if(selection.equalsIgnoreCase("pc")) {
                //if the predator is a plant, print an error message
                if(tree.getCursor().getIsPlant()) {
                    System.out.println("\n" + "ERROR: The cursor is at a " +
                            "plant node. Plants cannot be predators." + "\n");
                    continue;
                }
                //if the predator is a carnivore, print an error message
                if(!tree.getCursor().getIsHerbivore()) {
                    System.out.println("\n" + "ERROR: This prey cannot be" +
                            " added as it does not match the diet of " +
                            "the predator." + "\n");
                    continue;
                }
                String plantName = "";
                System.out.print("\n" + "What is the name of the organism?: ");
                plantName = input.nextLine();
                if(tree.getCursor().getLeft() == null) {
                    ;
                }
                else if (plantName.equalsIgnoreCase(tree.getCursor().getLeft().getName())){
                    System.out.println("\n" + "ERROR: This prey already exists" +
                            " for this predator." + "\n");
                    continue;
                }
                if (tree.getCursor().getMiddle() == null) {
                    ;
                }
                else if (plantName.equalsIgnoreCase(tree.getCursor().getLeft().getName()) || plantName.equalsIgnoreCase(tree.getCursor().getMiddle().getName())) {
                    System.out.println("\n" + "ERROR: This prey already exists" +
                            " for this predator." + "\n");
                    continue;
                }
                //if all the child nodes are not available, don't print that the prey has been successfully added
                if(tree.getCursor().getRight() == null) {
                    System.out.println("\n" + plantName + " has successfully" +
                            " been added as prey for the "
                            + tree.getCursor().getName() + "!" + "\n");
                }
                tree.addPlantChild(plantName);
            }

            // (AC) - Create New Animal Child
            else if(selection.equalsIgnoreCase("ac")) {
                //if the predator is a plant, print an error message
                if(tree.getCursor().getIsPlant()) {
                    System.out.println("\n" + "ERROR: The cursor is at a " +
                            "plant node. Plants cannot be predators." + "\n");
                    continue;
                }
                //if the predator is a herbivore, print an error message
                if(!tree.getCursor().getIsCarnivore()) {
                    System.out.println("\n" + "ERROR: This prey cannot be" +
                            "added as it does not match the diet of " +
                            "the predator." + "\n");
                    continue;
                }
                String animalName = "";
                String animalDiet = "";
                System.out.print("\n" + "What is the name of the organism?: ");
                animalName = input.nextLine();
                if(tree.getCursor().getLeft() == null) {
                    ;
                }
                else if (animalName.equalsIgnoreCase(tree.getCursor().getLeft().getName())){
                    System.out.println("\n" + "ERROR: This prey already exists" +
                            " for this predator." + "\n");
                    continue;
                }
                if (tree.getCursor().getMiddle() == null) {
                    ;
                }
                else if (animalName.equalsIgnoreCase(tree.getCursor().getLeft().getName()) || animalName.equalsIgnoreCase(tree.getCursor().getMiddle().getName())) {
                    System.out.println("\n" + "ERROR: This prey already exists" +
                            " for this predator." + "\n");
                    continue;
                }
                System.out.print("Is the organism an herbivore / a " +
                        "carnivore / an omnivore? (H / C / O): ");
                animalDiet = input.nextLine();
                //if all the child nodes are not available, don't print that the prey has been successfully added
                if(tree.getCursor().getRight() == null) {
                    System.out.println("\n" + "A(n) " + animalName + " has" +
                            " successfully been added as prey for the " +
                            tree.getCursor().getName() + "!" + "\n");
                }
                //if the user selects H, the animal is a herbivore
                if(animalDiet.equalsIgnoreCase("h")) {
                    tree.addAnimalChild(animalName, true, false);
                }
                //if the user selects C, the animal is a carnivore
                else if(animalDiet.equalsIgnoreCase("c")) {
                    tree.addAnimalChild(animalName, false, true);
                }
                //if the user selects O, the animal is a omnivore
                else if(animalDiet.equalsIgnoreCase("o")) {
                    tree.addAnimalChild(animalName, true, true);
                }
            }

            // (RC) - Remove Child
            else if(selection.equalsIgnoreCase("rc")) {
                try {
                    String removeName = "";
                    System.out.print("\n" + "What is the name of the organism" +
                            " to be removed?: ");
                    removeName = input.nextLine();
                    tree.removeChild(removeName);
                    System.out.println("\n" + "A(n) " + removeName + " has been" +
                            " successfully removed as prey for the " +
                            tree.getCursor().getName() + "!" + "\n");
                } catch(IllegalArgumentException iae) {
                    System.out.println("\n" + "ERROR: This prey does not exist for" +
                            " this predator" + "\n");
                }
            }

            // (P) - Print Out Cursor's Prey
            else if(selection.equalsIgnoreCase("p")) {
                System.out.println("\n" + tree.listPrey() + "\n");
            }

            // (C) - Print Out Food Chain
            else if(selection.equalsIgnoreCase("c")) {
                System.out.println("\n" + tree.listFoodChain() + "\n");
            }

            // (F) - Print Out Food Pyramid at Cursor
            else if(selection.equalsIgnoreCase("f")) {
                System.out.println();
                tree.printOrganismTree();
                System.out.println();
            }

            // (LP) - List All Plants Supporting Cursor
            else if(selection.equalsIgnoreCase("LP")) {
                System.out.println("\n" + tree.listAllPlants() + "\n");
            }

            // (R) - Reset Cursor to Root
            else if(selection.equalsIgnoreCase("r")) {
                tree.cursorReset();
                System.out.println("\n" + "Cursor successfully reset to " +
                        "root!" + "\n");
            }

            //(M) - Move Cursor to Child
            else if(selection.equalsIgnoreCase("m")) {
                try {
                    String moveName = "";
                    System.out.print("\n" + "Move to?: ");
                    moveName = input.nextLine();
                    tree.moveCursor(moveName);
                    System.out.println("\n" + "Cursor successfully moved to " +
                            moveName + "!" + "\n");
                } catch(Exception e) {
                    System.out.println("\n" + "ERROR: This prey does not" +
                            " exist for this predator." + "\n");
                }
            }

            // (Q) - Quit
            else if(selection.equalsIgnoreCase("q")) {
                System.out.println("\n" + "Program terminating successfully...");
                break;
            }

            // Invalid input
            else {
                System.out.println("\n" + "Invalid input" + "\n");
            }

        }
    }

}
