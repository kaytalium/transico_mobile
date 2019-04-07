package com.transico.codezero.transico.DriverScheduler;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.transico.codezero.transico.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViaRouteAdapter extends RecyclerView.Adapter<ViaRouteAdapter.RouteHolder> {

    private List<String> routes;
    private Context $this;

    ViaRouteAdapter(List<String> routes, Context context) {
       this.routes = routes;
       $this = context;
    }

    @NonNull
    @Override
    public RouteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.via_route, parent, false);
        return new RouteHolder(v);

    }

    @Override
    public void onBindViewHolder(@NonNull RouteHolder holder, int position) {
        holder.routeName.setText(routes.get(position));
    }

    @Override
    public int getItemCount() {
        return routes.size();
    }

    class RouteHolder extends RecyclerView.ViewHolder{

        TextView routeName;
        RouteHolder(@NonNull View itemView) {
            super(itemView);
            routeName = itemView.findViewById(R.id.txtRouteName);
        }
    }
}
