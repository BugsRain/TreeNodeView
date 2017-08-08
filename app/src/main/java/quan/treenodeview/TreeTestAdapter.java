package quan.treenodeview;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

import quan.library.TreeAdapter;
import quan.library.TreeHolder;
import quan.library.TreeNode;


/**
 * Created by quan on 17/8/8.
 */

public class TreeTestAdapter extends TreeAdapter<Bean> {


    public TreeTestAdapter(List<Bean> beans) {
        super(beans);
    }

    @Override
    public List<Bean> getChildList(Bean bean) {
        return bean.getChild();
    }

    @Override
    public void handlerBean(TreeNode<Bean> node) {

    }

    @Override
    public TreeHolder<Bean> createHolder(ViewGroup parent, int viewType) {
        return new TreeTestHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tree, parent, false));
    }


}
