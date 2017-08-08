package quan.library;

/**
 * Created by quan on 17/8/7.
 */

public interface OnTreeNodeClickListener<T> {
    enum TreeNodeExpandState{
        EndNode,
        Expand,
        Close
    }

    void onClick(TreeNode<T> node, TreeAdapter<T> adapter, int position, TreeNodeExpandState state);
}
