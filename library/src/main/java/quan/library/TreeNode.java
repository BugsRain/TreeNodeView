package quan.library;

import java.util.List;

/**
 * Created by quan on 17/8/3.
 */

public class TreeNode<T> {

    private int level;
    private List<TreeNode<T>> child;
    private T data;
    private boolean isHidden;
    private boolean isExtent;

    public TreeNode(){
    }


    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public List<TreeNode<T>> getChild() {
        return child;
    }

    public void setChild(List<TreeNode<T>> child) {
        this.child = child;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public boolean isParent(){
        return (child != null && !child.isEmpty());
    }

    public boolean isHidden() {
        return isHidden;
    }

    public void setHidden(boolean hidden) {
        isHidden = hidden;
        if(isParent()) {
            for (int i = 0; i < child.size(); i++) {
                TreeNode node = child.get(i);
                node.setHidden(hidden);
//                node.setExtent(!hidden);
            }
        }

    }

    public boolean isExtent() {
        return isExtent;
    }

    public void setExtent(boolean extent) {
        isExtent = extent;
        if(isParent()) {
            for (int i = 0; i < child.size(); i++) {
                TreeNode node = child.get(i);

                if(!extent){
                    node.setExtent(false);
                }

            }
        }
    }
}
