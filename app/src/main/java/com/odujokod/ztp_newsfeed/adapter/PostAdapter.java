package com.odujokod.ztp_newsfeed.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.source.ProgressiveMediaSource;
import com.google.android.exoplayer2.ui.PlayerView;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.odujokod.ztp_newsfeed.R;
import com.odujokod.ztp_newsfeed.RoundImageTransformation;
import com.odujokod.ztp_newsfeed.model.Posts;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostAdapterViewHolder>{

    private SimpleExoPlayer player;
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

    @Override
    public void onViewRecycled(@NonNull PostAdapterViewHolder holder) {
        int position = holder.getAdapterPosition();
        holder.releasePlayer();
        super.onViewRecycled(holder);
    }

    public void setPosts(List<Posts> posts) {
        this.posts = posts;
        notifyDataSetChanged();
    }

    class PostAdapterViewHolder extends RecyclerView.ViewHolder{

//        private ImageView ivBackground;
        private TextView mUsername;
        private ImageView mAvatar;
        private TextView description;
        private PlayerView playerView;


        PostAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
//            ivBackground = itemView.findViewById(R.id.iv_background);
            mUsername = itemView.findViewById(R.id.tv_user_name);
            mAvatar = itemView.findViewById(R.id.iv_avatar);
            description = itemView.findViewById(R.id.tv_description);
            playerView = itemView.findViewById(R.id.player_view);
        }

        void bind(int position) {
            Posts post = posts.get(position);
            String username = post.getUser_info().getUsername();
            String avatar = post.getUser_info().getAvatar();
//            String backgroundImage = post.getThumbnail_url();
            String desc = post.getDescription();
            String videoUrl = post.getMedia_url();

            mUsername.setText("@" + username);
            description.setText(desc);

            initializePlayer(videoUrl);
//            Picasso.with(context)
//                    .load(backgroundImage)
//                    .fit().centerCrop()
//                    .placeholder(R.drawable.ic_person)
//                    .error(R.drawable.ic_person)
//                    .into(ivBackground);

            Picasso.with(context)
                    .load(avatar)
                    .transform(new RoundImageTransformation())
                    .placeholder(R.drawable.ic_person)
                    .error(R.drawable.ic_person)
                    .into(mAvatar);
        }

        private void initializePlayer(String videoUrl){
            player = ExoPlayerFactory.newSimpleInstance(context);
            playerView.setPlayer(player);
            player.setPlayWhenReady(true);

            MediaSource videoSource = buildMediaSource(videoUrl);
            player.prepare(videoSource);
        }

        private MediaSource buildMediaSource(String videoUrl){
            Uri videoUri = Uri.parse(videoUrl);

            DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(context,
                    Util.getUserAgent(context, context.getString(R.string.app_name)));

            return new ProgressiveMediaSource.Factory(dataSourceFactory)
                    .createMediaSource(videoUri);
        }

        public void releasePlayer() {
            if(player != null){
                player.getCurrentPosition();
                player.getCurrentWindowIndex();
                player.getPlayWhenReady();
                player.release();
                player = null;
            }
        }
    }
}
