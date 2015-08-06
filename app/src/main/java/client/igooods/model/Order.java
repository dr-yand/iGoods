package client.igooods.model;

import android.os.Parcel;
import android.os.Parcelable;

import org.json.JSONObject;

public class Order implements Parcelable {

    private int employeeId, id;

    public int getId() {
        return id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setId(int id) {
        this.id = id;
    }

    private String title;

    public Order() {
    }

    public Order(JSONObject object) {
        try {
            employeeId = object.getInt("employee_id");
            title = object.getString("title");
        }
        catch (Exception e){}
    }

    public Order(Parcel in) {
        employeeId = in.readInt();
        title = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(employeeId);
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
