package client.igoods.model;

import android.os.Parcel;
import android.os.Parcelable;

public class User implements Parcelable {
    public String name, login, password;

    public User() {
    }

    public User(Parcel in) {
        name = in.readString();
        login = in.readString();
        password = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(login);
        dest.writeString(password);
    }

    public static final Creator CREATOR = new Creator() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
