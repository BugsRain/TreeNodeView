package quan.treenodeview;

import java.util.List;

/**
 * Created by quan on 17/8/3.
 */

public class Bean {
    private List<Bean> child;
    private String name;

    public List<Bean> getChild() {
        return child;
    }

    public void setChild(List<Bean> child) {
        this.child = child;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
