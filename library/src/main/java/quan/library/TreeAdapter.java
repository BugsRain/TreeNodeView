package quan.library;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by quan on 17/8/7.
 */

public abstract class TreeAdapter<T> extends RecyclerView.Adapter<TreeHolder<T>> {

    private OnTreeNodeClickListener<T> listener;

    private List<TreeNode<T>> nodes;

    private boolean expandAnimation;

    public TreeAdapter(List<T> beans) {
        this.initData(beans, 0);
        this.expandAnimation = true;

    }


    @Override
    final public int getItemCount() {
        return this.nodes == null ? 0:this.nodes.size();
    }

    @Override
    final public TreeHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        return createHolder(parent, viewType);
    }

    @Override
    final public void onBindViewHolder(TreeHolder<T> holder, final int position) {
        final TreeNode<T> node = this.nodes.get(position);
        final int pos = holder.getAdapterPosition();

        holder.hold(node, position);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TreeAdapter<T> adapter = TreeAdapter.this;

                if(node.isParent()){
                    if(node.isExtent()){
                        adapter.hideData(pos, node);
                        if(adapter.listener != null){
                            adapter.listener.onClick(node, adapter, pos, OnTreeNodeClickListener.TreeNodeExpandState.Close);
                        }
                    }else{
                        adapter.showData(pos, node);
                        if(adapter.listener != null){
                            adapter.listener.onClick(node, adapter, pos, OnTreeNodeClickListener.TreeNodeExpandState.Expand);
                        }
                    }
                }else{
                    if(adapter.listener != null){
                        adapter.listener.onClick(node, adapter, pos, OnTreeNodeClickListener.TreeNodeExpandState.EndNode);
                    }
                }
            }
        });
    }


    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        recyclerView.getItemAnimator().setAddDuration(200);
        recyclerView.getItemAnimator().setRemoveDuration(200);
        recyclerView.getItemAnimator().setChangeDuration(200);
    }

    public void setListener(OnTreeNodeClickListener<T> listener) {
        this.listener = listener;
    }

    private void hideData(int start, TreeNode<T> node){

        node.setExtent(false);

        if(node.isParent()){
            for (int i = 0; i < node.getChild().size() ; i++) {
                TreeNode<T> subNode = node.getChild().get(i);
                subNode.setHidden(true);
            }

            if(this.nodes != null){
                int count = 0;
                for (Iterator<TreeNode<T>> it = this.nodes.iterator(); it.hasNext(); ) {
                    TreeNode<T> next =  it.next();
                    if(next.isHidden()){
                        it.remove();
                        count++;
                    }
                }
                if(this.isExpandAnimation()){
                    this.notifyItemChanged(start);
                    this.notifyItemRangeRemoved(start + 1, count);
                    this.notifyItemRangeChanged(start + 1, this.nodes.size() - (start + 1 + count));
                }else{
                    this.notifyDataSetChanged();
                }


            }
        }


    }

    private void showData(int start, TreeNode<T> node){
        node.setExtent(true);
        if(node.isParent()){

            for (TreeNode<T> subNode : node.getChild()){
                subNode.setHidden(false);
            }

            this.nodes.addAll(start+1, node.getChild());

            if(this.isExpandAnimation()){
                this.notifyItemChanged(start);
                this.notifyItemRangeInserted(start+1, node.getChild().size());
                this.notifyItemRangeChanged(start+1+node.getChild().size(), this.nodes.size()-(start+1+node.getChild().size()));
            }else{
                this.notifyDataSetChanged();
            }
        }


    }

    private List<TreeNode<T>> initData(List<T> beans, int level){
        if(this.nodes == null){
            this.nodes = new LinkedList<>();
        }

        List<TreeNode<T>> arr = new ArrayList<>();
        for (int i = 0; i < beans.size(); i++) {

            TreeNode<T> node = new TreeNode<>();
            T bean = beans.get(i);

            node.setData(bean);

            node.setLevel(level);
            node.setHidden(false);
            node.setExtent(true);
            arr.add(node);

            this.nodes.add(node);
            if(this.getChildList(bean)!=null){
                node.setChild(this.initData(this.getChildList(bean), (level + 1)));
            }

            this.handlerBean(node);
        }
        return arr;
    }

    public boolean isExpandAnimation() {
        return expandAnimation;
    }

    public void setExpandAnimation(boolean expandAnimation) {
        this.expandAnimation = expandAnimation;
    }

    public abstract TreeHolder<T> createHolder(ViewGroup parent, int viewType);

    public abstract void handlerBean(TreeNode<T> node);

    public abstract List<T> getChildList(T t);


}
