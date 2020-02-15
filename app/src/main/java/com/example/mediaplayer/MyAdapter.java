package com.example.mediaplayer;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.ThumbnailUtils;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.VideoHolder> {


    private Context context;
    ArrayList<File>videoArrayList;

    public MyAdapter(Context context, ArrayList<File> videoArrayList) {
        this.context = context;
        this.videoArrayList = videoArrayList;
    }

    @NonNull
    @Override
    public VideoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View mview = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.video_item,parent,false);

        return new VideoHolder(mview);
    }

    @Override
    public void onBindViewHolder(@NonNull final VideoHolder holder, int position) {

        holder.txtFileName.setText(VideoPlayer.fileArrayList.get(position).getName());
        Bitmap bitmap = ThumbnailUtils.createVideoThumbnail(videoArrayList.get(position).getPath(), MediaStore.Images.Thumbnails.MINI_KIND);
        holder.imageView.setImageBitmap(bitmap);

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,videoPlayerActivity.class);
                intent.putExtra("position",holder.getAdapterPosition());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (videoArrayList.size()>0){
            return videoArrayList.size();
        }else
            return 1;
    }

    class VideoHolder extends RecyclerView.ViewHolder{

        TextView txtFileName;
        ImageView imageView;
        CardView cardView;

        VideoHolder(View view){
            super(view);

            txtFileName = view.findViewById(R.id.txt_videoFile);
            imageView = view.findViewById(R.id.image1);
            cardView = view.findViewById(R.id.myCardView);

        }
    }
}
