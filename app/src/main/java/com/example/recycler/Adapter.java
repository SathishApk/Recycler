package com.example.recycler;

import android.content.Context;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ImgViewHolder> {
    private Context context;
    private List<model> lstuploads;
    private  OnItemClickListener mListener;

    public Adapter(Context context, List<model> lstuploads) {
        this.context = context;
        this.lstuploads = lstuploads;
    }

    @NonNull
    @Override
    public ImgViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.list_item_single,parent,false);
        return new ImgViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ImgViewHolder holder, int position) {
        model modelCurrent = lstuploads.get(position);
        holder.txtname.setText(modelCurrent.getName());
        Picasso.get().load(modelCurrent.getImgUri())
                .placeholder(R.drawable.ic_loading)
                .fit()
                .centerCrop()
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return lstuploads.size();
    }

    public class ImgViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {
        public TextView txtname;
        public ImageView imageView;

        @Override
        public void onClick(View v) {
            if(mListener!= null)
            {
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION)
                {
                    mListener.onItemClick(position);
                }
            }
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            menu.setHeaderTitle("Select Action");
            MenuItem doWhatever = menu.add(Menu.NONE,1,1,"Do Whatever");
            MenuItem delete = menu.add(Menu.NONE,2,2,"Delete");
            doWhatever.setOnMenuItemClickListener(this);
            delete.setOnMenuItemClickListener(this);
        }

        @Override
        public boolean onMenuItemClick(MenuItem item) {
            if(mListener!= null)
            {
                int position = getAdapterPosition();
                if(position!= RecyclerView.NO_POSITION)
                {
                   switch (item.getItemId())
                   {
                       case 1:
                           mListener.onWhatEverClick(position);
                           return true;
                       case 2:
                           mListener.onDeleteClick(position);
                           return true;
                   }
                }
            }
            return false;
        }

        public ImgViewHolder(View itemView) {
            super(itemView);
            this.txtname = itemView.findViewById(R.id.textnameRC);
            this.imageView = itemView.findViewById(R.id.profileImage);

            itemView.setOnClickListener(this);
            itemView.setOnCreateContextMenuListener(this);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
        void onWhatEverClick(int position);
        void onDeleteClick(int position);
    }
    public  void setOnItemClickListener(OnItemClickListener listener){
        mListener = listener;
    }
}
