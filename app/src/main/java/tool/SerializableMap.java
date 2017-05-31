package tool;

import android.graphics.Bitmap;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2016/8/13.
 */
public class SerializableMap implements Serializable {
    private Map<String,Object> map;

    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }
}
