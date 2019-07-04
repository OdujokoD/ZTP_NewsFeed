package com.odujokod.ztp_newsfeed.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.odujokod.ztp_newsfeed.R;
import com.odujokod.ztp_newsfeed.model.Posts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder>{

    private List<Posts> posts;
    private Context context;

    @NonNull
    @Override
    public PostAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        context = parent.getContext();
        View view = LayoutInflater.from(context)
                .inflate(R.layout.post_item, parent, false);
        return new PostAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostAdapterViewHolder postAdapterViewHolder, int position) {
        postAdapterViewHolder.bind(position);
    }

    @Override
    public int getItemCount() {
        if(this.posts != null)
            return posts.size();
        return 0;
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    class PostAdapterViewHolder extends RecyclerView.ViewHolder{

        private ImageView ivBackground;
        private TextView mUsername;
        private ImageView mAvatar;
        private TextView description;


        PostAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            ivBackground = itemView.findViewById(R.id.iv_background);
            mUsername = itemView.findViewById(R.id.tv_user_name);
            mAvatar = itemView.findViewById(R.id.iv_avatar);
            description = itemView.findViewById(R.id.tv_description);
        }

        void bind(int position) {
            Posts post = posts.get(position);
            String username = post.getUser_info().getUsername();
            String avatar = post.getUser_info().getAvatar();
            String backgroundImage = post.getThumbnail_url();
            String desc = post.getDescription();

            mUsername.setText("@" + username);
            description.setText(desc);

            Picasso.with(context)
                    .load(backgroundImage)
                    .fit().centerCrop()
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(ivBackground);

            Picasso.with(context)
                    .load(avatar)
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(mAvatar);
        }
    }
}
