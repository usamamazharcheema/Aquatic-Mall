






package com.example.androiddeveloper.aquatic_mall.Adapters;

        import android.content.Context;

        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

        import android.widget.TextView;

        import com.example.androiddeveloper.aquatic_mall.R;

        import com.example.androiddeveloper.aquatic_mall.ResponseLists.Newinvestordetaillist;

        import java.util.ArrayList;


/**
 * Created by ANDROID DEVELOPER on 05/02/2018.
 */






public class RentalAdapter extends RecyclerView.Adapter<RentalAdapter.MyViewHolder> {



    Context context;
    ArrayList<Newinvestordetaillist> investorPropertyNews;
    public RentalAdapter( Context context,ArrayList<Newinvestordetaillist> investorPropertyNews) {

        this.context = context;
        this.investorPropertyNews=investorPropertyNews;

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView date,dueamount,paidamount,cash;



        public MyViewHolder(View view) {
            super(view);
            date=view.findViewById(R.id.installdate);
            dueamount=view.findViewById(R.id.in_amonut);
            paidamount=view.findViewById(R.id.in_status);
            cash=view.findViewById(R.id.in_cash);


        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rentalnew, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.date.setText(investorPropertyNews.get(position).getIduedate());
        holder.cash.setText(investorPropertyNews.get(position).getCash());

        holder.paidamount.setText(investorPropertyNews.get(position).getIstatus());
        holder.dueamount.setText(investorPropertyNews.get(position).getIamount());

    }

    @Override
    public int getItemCount() {
        return investorPropertyNews.size();
    }


}