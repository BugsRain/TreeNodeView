package quan.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by quan on 17/8/7.
 */

public class TreeHolder<T> extends RecyclerView.ViewHolder {


    public TreeHolder(View itemView) {
        super(itemView);
    }

    public void hold(TreeNode<T> node, int position){

    };
}
