//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
import java.util.Scanner;
import java.util.InputMismatchException;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static BinarySearchTree bst = null;

    public static void main(String[] args) {
        boolean running = true;
        while (running) {
            printMenu();
            try {
                int choice = scanner.nextInt();
                scanner.nextLine();

                switch (choice) {
                    case 1:
                        createBST();
                        break;
                    case 7:
                        running = false;
                        break;
                    default:
                        // Check if BST exists before performing operations
                        if (bst == null) {
                            System.out.println("Please create a binary search tree first.");
                        } else {
                            performOperation(choice);
                        }
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
            } catch (Exception e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
        exitProgram();
    }

    private static void printMenu() {
        System.out.println("\nBinary Search Tree Operations:");
        System.out.println("1. Create a binary search tree");
        System.out.println("2. Add a node");
        System.out.println("3. Delete a node");
        System.out.println("4. Print nodes by InOrder");
        System.out.println("5. Print nodes by PreOrder");
        System.out.println("6. Print nodes by PostOrder");
        System.out.println("7. Exit program");
        System.out.print("Enter your choice: ");
    }

    private static void createBST() {
        bst = new BinarySearchTree();
        int[] initialData = {1, 2, 3, 4, 5, 6, 7};
        // Populate the BST with initial data
        for (int data : initialData) {
            bst.insert(data);
        }
        System.out.println("The Binary Search Tree has been created.");
    }

    private static void performOperation(int choice) {
        switch (choice) {
            case 2:
                System.out.print("Enter the node value: ");
                int insertValue = scanner.nextInt();
                System.out.print("Node has been inserted successfully.");
                bst.insert(insertValue);
                break;
            case 3:
                System.out.print("Enter the value to delete: ");
                int deleteValue = scanner.nextInt();
                System.out.print("Node has been deleted successfully.");
                bst.delete(deleteValue);
                break;
            case 4:
                System.out.print("InOrder traversal: ");
                bst.inorderTraversal();
                break;
            case 5:
                System.out.print("PreOrder traversal: ");
                bst.preorderTraversal();
                break;
            case 6:
                System.out.print("PostOrder traversal: ");
                bst.postorderTraversal();
                break;
            default:
                System.out.println("Invalid choice. Please try again.");
        }
    }

    private static void exitProgram() {
        System.out.println("Exiting program. Goodbye!");
        scanner.close();
        System.exit(0);
    }
}

// Node class represents a single node in the BST
class Node {
    int data;
    Node left;
    Node right;

    public Node(int data) {
        this.data = data;
        left = right = null;
    }
}

class BinarySearchTree {
    private Node root;

    public BinarySearchTree() {
        root = null;
    }

    // Public insert method that calls the recursive insertNode method
    public void insert(int data) {
        root = insertNode(root, data);
        System.out.println();
    }

    // Recursive method to insert a new node
    private Node insertNode(Node current, int data) {
        if (current == null) {
            return new Node(data);
        }

        if (data < current.data) {
            current.left = insertNode(current.left, data);
        } else if (data > current.data) {
            current.right = insertNode(current.right, data);
        }

        return current;
    }

    // Public delete method that calls the recursive deleteNode method
    public void delete(int data) {
        root = deleteNode(root, data);
        System.out.println();
    }

    // Recursive method to delete a node
    private Node deleteNode(Node current, int data) {
        if (current == null) {
            return null;
        }

        if (data == current.data) {
            // Node to delete found
            // If no children node
            if (current.left == null && current.right == null) {
                return null;
            }
            // If only one child node
            if (current.left == null) {
                return current.right;
            }
            if (current.right == null) {
                return current.left;
            }
            // If two children nodes
            int smallestValue = minValue(current.right);
            current.data = smallestValue;
            current.right = deleteNode(current.right, smallestValue);
            return current;
        }
        if (data < current.data) {
            current.left = deleteNode(current.left, data);
            return current;
        }
        current.right = deleteNode(current.right, data);
        return current;
    }

    // Helper method to find the minimum value in a subtree
    private int minValue(Node root) {
        int minv = root.data;
        while (root.left != null) {
            minv = root.left.data;
            root = root.left;
        }
        return minv;
    }

    // Public method for inorder traversal
    public void inorderTraversal() {
        inorderTraversal(root);
        System.out.println();
    }

    // Recursive inorder traversal
    private void inorderTraversal(Node node) {
        if (node != null) {
            inorderTraversal(node.left);
            System.out.print(node.data + " ");
            inorderTraversal(node.right);
        }
    }

    // Public method for preorder traversal
    public void preorderTraversal() {
        preorderTraversal(root);
        System.out.println();
    }

    // Recursive preorder traversal
    private void preorderTraversal(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preorderTraversal(node.left);
            preorderTraversal(node.right);
        }
    }

    // Public method for postorder traversal
    public void postorderTraversal() {
        postorderTraversal(root);
        System.out.println();
    }

    // Recursive postorder traversal
    private void postorderTraversal(Node node) {
        if (node != null) {
            postorderTraversal(node.left);
            postorderTraversal(node.right);
            System.out.print(node.data + " ");
        }
    }

}