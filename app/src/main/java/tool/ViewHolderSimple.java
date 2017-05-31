package tool;

import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/7/17.
 */
public class ViewHolderSimple {
    public TextView name;

    public ViewHolderSimple(TextView name, TextView self_info, TextView contexttext, ImageView photo) {
        this.name = name;
        this.self_info=self_info;
        this.contexttext = contexttext;
        this.photo = photo;
    }

    public TextView getName() {
        return name;
    }

    public ViewHolderSimple() {
    }

    public void setName(TextView name) {
        this.name = name;
    }



    public TextView getContexttext() {
        return contexttext;
    }

    public void setContexttext(TextView contexttext) {
        this.contexttext = contexttext;
    }

    public ImageView getPhoto() {
        return photo;
    }

    public void setPhoto(ImageView photo) {
        this.photo = photo;
    }

    public TextView self_info;
    public TextView contexttext;//正文
    public ImageView photo;
}
