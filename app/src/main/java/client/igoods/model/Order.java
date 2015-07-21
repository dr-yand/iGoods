package client.igoods.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Order implements Parcelable {
    public String title;

    public Order() {
    }

    public Order(Parcel in) {
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
        public Order createFromParcel(Parcel in) {
            return new Order(in);
        }

        public Order[] newArray(int size) {
            return new Order[size];
        }
    };
}
