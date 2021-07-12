package com.example.androiddeveloper.aquatic_mall.Adapters;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.androiddeveloper.aquatic_mall.R;
import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newinvestordetaillist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by ANDROID DEVELOPER on 18/04/2018.
 */

public class ExpandableAdapterRental extends BaseExpandableListAdapter {

    private Context _context;
    private List<String> _listDataHeader; // header titles
    // child data in format of header title, child title
    private HashMap<String, List<Newinvestordetaillist>> _listDataChild;
    ArrayList<Newinvestordetaillist> newinvestordetaillists;
    public ExpandableAdapterRental(Context context, List<String> listDataHeader,
                                 HashMap<String, List<Newinvestordetaillist>> listChildData, ArrayList<Newinvestordetaillist> newinvestordetaillists) {
        this._context = context;
        this._listDataHeader = listDataHeader;
        this._listDataChild = listChildData;
        this.newinvestordetaillists=newinvestordetaillists;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this._listDataChild.get(this._listDataHeader.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

     //   final String childText = (String) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.rentalheadersdetail, null);
        }
        ArrayList<Newinvestordetaillist> rentlist = new ArrayList<>();

        RecyclerView recyclerView=convertView.findViewById(R.id.install_recview);
        RentalAdapter iNstalllistadapternew;

        iNstalllistadapternew= new RentalAdapter(_context,rentlist);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(_context);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(iNstalllistadapternew);
        for(int c=0;c<newinvestordetaillists.size();c++)
        {
            if(newinvestordetaillists.get(c).getAmountpaid().equals(_listDataHeader.get(groupPosition)))
            {
                rentlist.add(newinvestordetaillists.get(c));
            }
            if(newinvestordetaillists.size()-c==1)
            {
                iNstalllistadapternew.notifyDataSetChanged();
            }

        }
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return 1;
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this._listDataHeader.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this._listDataHeader.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.rentalyearslist, null);
        }

        ImageView imgs=convertView.findViewById(R.id.imgyears);
        TextView lblListHeader = (TextView) convertView
                .findViewById(R.id.shopeno);
        lblListHeader.setTypeface(null, Typeface.BOLD);
        lblListHeader.setText(headerTitle);

        if (isExpanded) {
            imgs.setImageResource(R.drawable.downarrowexpandable);
        }
        else {
            imgs.setImageResource(R.drawable.uparrowexpanable);
        }

        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
