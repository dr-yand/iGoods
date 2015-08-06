package client.igooods.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Stat implements Parcelable {
    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Stat() {
    }

    public Stat(JSONObject object) {
        try {
            title = object.getString("title");
        }
        catch (Exception e){}
    }

    public Stat(Parcel in) {
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
    }

    public static final Creator CREATOR = new Creator() {
        public Stat createFromParcel(Parcel in) {
            return new Stat(in);
        }

        public Stat[] newArray(int size) {
            return new Stat[size];
        }
    };
}
