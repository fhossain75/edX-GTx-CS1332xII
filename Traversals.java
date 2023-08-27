import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * Your implementation of the pre-order, in-order, and post-order
 * traversals of a tree.
 */
public class Traversals<T extends Comparable<? super T>> {

    /**
     * DO NOT ADD ANY GLOBAL VARIABLES!
     */

    /**
     * Given the root of a binary search tree, generate a
     * pre-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the pre-order traversal of the tree.
     */
    public List<T> preorder(TreeNode<T> root) {

        List<T> preorderTraversal = new ArrayList<T>();

        preorderHelper(root, preorderTraversal);

        return preorderTraversal;

    }

    /**
     * Helper method for performing pre-order traversal recursively.
     *
     * @param node The current node in the traversal.
     * @param preorderTraversal The list to store the pre-order traversal.
     */
    private void preorderHelper(TreeNode<T> node, List<T> preorderTraversal) {

        if (node != null) {
            // Add parent
            preorderTraversal.add(node.getData());
            // Add left child
            preorderHelper(node.getLeft(), preorderTraversal);
            // Add right child
            preorderHelper(node.getRight(), preorderTraversal);
        }
    }

    /**
     * Given the root of a binary search tree, generate an
     * in-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the in-order traversal of the tree.
     */
    public List<T> inorder(TreeNode<T> root) {

        List<T> inorderTraversal = new ArrayList<T>();

        inorderHelper(root, inorderTraversal);

        return inorderTraversal;

    }

    /**
     * Helper method for performing in-order traversal recursively.
     *
     * @param node The current node in the traversal.
     * @param inorderTraversal The list to store the in-order traversal.
     */
    private void inorderHelper(TreeNode<T> node, List<T> inorderTraversal) {

        if (node != null) {
            // Add left child
            inorderHelper(node.getLeft(), inorderTraversal);
            // Add parent
            inorderTraversal.add(node.getData());
            // Add right child
            inorderHelper(node.getRight(), inorderTraversal);
        }
    }

    /**
     * Given the root of a binary search tree, generate a
     * post-order traversal of the tree. The original tree
     * should not be modified in any way.
     *
     * This must be done recursively.
     *
     * Must be O(n).
     *
     * @param <T> Generic type.
     * @param root The root of a BST.
     * @return List containing the post-order traversal of the tree.
     */
    public List<T> postorder(TreeNode<T> root) {

        List<T> postorderTraversal = new ArrayList<T>();

        postorderHelper(root, postorderTraversal);

        return postorderTraversal;

    }

    /**
     * Helper method for performing post-order traversal recursively.
     *
     * @param node The current node in the traversal.
     * @param postorderTraversal The list to store the post-order traversal.
     */
    private void postorderHelper(TreeNode<T> node, List<T> postorderTraversal) {

        if (node != null) {
            // Add left child
            postorderHelper(node.getLeft(), postorderTraversal);
            // Add right child
            postorderHelper(node.getRight(), postorderTraversal);
            // Add parent
            postorderTraversal.add(node.getData());
        }
    }
}