package quan.treenodeview;

import android.view.View;
import android.widget.TextView;

import quan.library.TreeHolder;
import quan.library.TreeNode;


/**
 * Created by quan on 17/8/8.
 */

public class TreeTestHolder extends TreeHolder<Bean> {

    private TextView textView;

    public TreeTestHolder(View itemView) {
        super(itemView);
        this.textView = (TextView) itemView.findViewById(R.id.item_tree_txt);
    }

    @Override
    public void hold(TreeNode<Bean> node, int position) {
        super.hold(node, position);

        String paddingString = "";
        for (int i = 0; i < node.getLevel(); i++) {
            paddingString+="\t\t\t";
        }

        if(node.isParent()){
            if(node.isExtent()){
                paddingString+="-";
            }else{
                paddingString+="+";
            }
        }else{
            paddingString+="*";
        }

        this.textView.setText(paddingString + "name："+node.getData().getName() + "，level："+node.getLevel());
    }
}
