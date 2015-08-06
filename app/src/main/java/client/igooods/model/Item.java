package client.igooods.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Item implements Parcelable {

    private int orderId, id, count;
    private String title;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Item() {
    }

    public Item(JSONObject object) {
        try {
            orderId = object.getInt("orderId");
            title = object.getString("title");
            count = object.getInt("count");
        }
        catch (Exception e){}
    }

    public Item(Parcel in) {
        orderId = in.readInt();
        title = in.readString();
        count = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(orderId);
        dest.writeString(title);
        dest.writeInt(count);
    }

    public static final Creator CREATOR = new Creator() {
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        public Item[] newArray(int size) {
            return new Item[size];
        }
    };
}
