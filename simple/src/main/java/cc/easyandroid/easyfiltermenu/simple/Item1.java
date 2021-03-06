package cc.easyandroid.easyfiltermenu.simple;

import android.os.Parcel;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import cc.easyandroid.easyfiltermenu.core.EasyItemManager;
import cc.easyandroid.easyfiltermenu.core.IEasyItem;
import cc.easyandroid.easyrecyclerview.EasyFlexibleAdapter;
import cc.easyandroid.easyrecyclerview.holders.FlexibleViewHolder;
import cc.easyandroid.listfiltermenu.simple.R;

/**
 * Created by cgpllx on 2016/10/17.
 */
public class Item1 extends Text1.ResultEntity implements IEasyItem<Item1.ListViewHolder>, Parcelable {
    EasyItemManager easyItemManager;//一定要是单列的
    HashMap<String, String> para = new HashMap<>();

    @Override
    public EasyItemManager getEasyItemManager() {
        if (easyItemManager == null) {
            synchronized (Item1.class) {
                if (easyItemManager == null) {
                    ArrayList list = getSubregions();
                    easyItemManager = new EasyItemManager(list);
                }
            }
        }
        return easyItemManager;
    }

    @Override
    public CharSequence getEasyItemTag() {
        return getName();
    }

    @Override
    public HashMap<String, String> getEasyParameter() {
        return para;
    }


    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public void setEnabled(boolean b) {

    }

    @Override
    public boolean isSelectable() {
        return true;
    }

    @Override
    public void setSelectable(boolean b) {

    }

    @Override
    public int getLayoutRes() {
        return R.layout.list_item;
    }


    @Override
    public ListViewHolder createViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, LayoutInflater inflater, ViewGroup viewGroup) {
        return new ListViewHolder(inflater.inflate(getLayoutRes(), viewGroup, false), easyFlexibleListViewAdapter);
    }

    @Override
    public void bindViewHolder(EasyFlexibleAdapter easyFlexibleListViewAdapter, ListViewHolder viewHolder, int i, List list) {
        viewHolder.setData(this);
    }

    public class ListViewHolder extends FlexibleViewHolder {
        TextView textView;

        public ListViewHolder(final View header_ad, EasyFlexibleAdapter adapter) {
            super(header_ad, adapter);
            textView = (TextView) header_ad.findViewById(R.id.easyListFilter_ItemDisplayName);
        }

        @Override
        public void onClick(View view) {
            super.onClick(view);
//            Toast.makeText(view.getContext(), "po" + position, Toast.LENGTH_SHORT).show();

        }

        public void setData(Item1 entity) {
            textView.setText(entity.getEasyItemTag());
            if (entity.getEasyItemManager().isChildSelected()) {
                textView.setTextColor(getContentView().getResources().getColor(R.color.q_ff0000));
            } else {
                textView.setTextColor(getContentView().getResources().getColor(R.color.colorPrimary));
            }
        }

    }

    public Item1() {
    }

    //
//        @Override
//        public void writeToParcel(Parcel dest, int flags) {
//            dest.writeString(this.fullPinyin);
//            dest.writeString(this.id);
//            dest.writeDouble(this.latitude);
//            dest.writeDouble(this.longitude);
//            dest.writeString(this.name);
//            dest.writeTypedList(subregions);
//        }
//
//        protected Item1(Parcel in) {
//            this.fullPinyin = in.readString();
//            this.id = in.readString();
//            this.latitude = in.readDouble();
//            this.longitude = in.readDouble();
//            this.name = in.readString();
//            this.subregions = in.createTypedArrayList(Item1.CREATOR);
//        }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.easyItemManager, 0);
        dest.writeString(this.fullPinyin);
        dest.writeString(this.id);
        dest.writeDouble(this.latitude);
        dest.writeDouble(this.longitude);
        dest.writeString(this.name);
        dest.writeTypedList(subregions);
    }

    protected Item1(Parcel in) {
        this.easyItemManager = in.readParcelable(EasyItemManager.class.getClassLoader());
        this.fullPinyin = in.readString();
        this.id = in.readString();
        this.latitude = in.readDouble();
        this.longitude = in.readDouble();
        this.name = in.readString();
        this.subregions = in.createTypedArrayList(Item1.CREATOR);
    }

    public static final Creator<Item1> CREATOR = new Creator<Item1>() {
        public Item1 createFromParcel(Parcel source) {
            return new Item1(source);
        }

        public Item1[] newArray(int size) {
            return new Item1[size];
        }
    };
}
