package quan.treenodeview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import quan.library.OnTreeNodeClickListener;
import quan.library.TreeAdapter;
import quan.library.TreeNode;


/**
 * Created by quan on 17/8/3.
 */

public class TreeTestActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        TreeAdapter<Bean> adapter = new TreeTestAdapter(initBean());
        adapter.setExpandAnimation(false);
        adapter.setListener(new OnTreeNodeClickListener<Bean>() {
            @Override
            public void onClick(TreeNode<Bean> node, TreeAdapter adapter, int position, TreeNodeExpandState state) {

            }
        });
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        setContentView(recyclerView);

//        recyclerView.smoothScrollToPosition(1000);
    }

    private List<Bean> initBean(){
        String dataStr = "[{\"name\":\"A\",\"child\":[{\"name\":\"A-0\",\"child\":[{\"name\":\"A-0-0\"},{\"name\":\"A-0-1\"},{\"name\":\"A-0-2\"}]},{\"name\":\"A-1\"}]},{\"name\":\"B\",\"child\":[{\"name\":\"B-0\"}]},{\"name\":\"C\"},{\"name\":\"D\"}]";
        return new Gson().fromJson(dataStr, new TypeToken<List<Bean>>(){}.getType());

    }

}
