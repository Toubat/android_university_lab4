package com.codepath.recyclerviewlab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.widget.ContentLoadingProgressBar;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.recyclerviewlab.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // Two view types which will be used to determine whether a row should be displaying
    // data or a Progressbar
    public static final int VIEW_TYPE_LOADING = 0, VIEW_TYPE_ARTICLE = 1, VIEW_TYPE_FIRST = 2;
    List<Article> articles = new ArrayList<>();

    //Returns the view type of the item at position for the purposes of view recycling.
    @Override
    public int getItemViewType(int position) {
        if (position >= articles.size()) {
            return VIEW_TYPE_LOADING;
        } else if ("1".equals(articles.get(position).printPage)) {
            return VIEW_TYPE_FIRST;
        } else {
            return VIEW_TYPE_ARTICLE;
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view;
        if (viewType == VIEW_TYPE_ARTICLE) {
            view = inflater.inflate(R.layout.fragment_article_result, parent, false);
            return new ArticleViewHolder(view);
        } else if (viewType == VIEW_TYPE_LOADING){
            view = inflater.inflate(R.layout.article_progress, parent, false);
            return new LoadingViewHolder(view);
        } else {
            view = inflater.inflate(R.layout.fragment_article_result_first_page, parent, false);
            return new FirstViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_ARTICLE) {
            ArticleViewHolder newHolder = (ArticleViewHolder) holder;
            newHolder.bind(articles.get(position));
        } else if (holder.getItemViewType() == VIEW_TYPE_FIRST) {
            FirstViewHolder newHolder = (FirstViewHolder) holder;
            newHolder.bind(articles.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return (articles.size() == 0) ? 0 : articles.size() + 1;
    }

    // This method clears the existing dataset and adds new articles
    void setNewArticles(List<Article> articles) {
        this.articles.clear();
        this.articles.addAll(articles);
    }

    // This method adds all articles to the existing dataset
    void addArticles(List<Article> articles) {
        this.articles.addAll(articles);
    }

    static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView article_pub_date;
        TextView article_headline;
        TextView article_snippet;

        public ArticleViewHolder(@NonNull View itemView) {
            super(itemView);

            article_pub_date = itemView.findViewById(R.id.article_pub_date);
            article_headline = itemView.findViewById(R.id.article_headline);
            article_snippet = itemView.findViewById(R.id.article_snippet);
        }

        public void bind(Article article) {
            article_pub_date.setText(article.publishDate);
            article_headline.setText(article.headline.main);
            article_snippet.setText(article.snippet);
        }
    }

    static class LoadingViewHolder extends RecyclerView.ViewHolder {

        ContentLoadingProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progress);
        }
    }

    static class FirstViewHolder extends RecyclerView.ViewHolder {

        TextView first_page_header;
        TextView article_pub_date;
        TextView article_headline;
        TextView article_snippet;

        public FirstViewHolder(@NonNull View itemView) {
            super(itemView);

            first_page_header = itemView.findViewById(R.id.first_page_header);
            article_pub_date = itemView.findViewById(R.id.article_pub_date);
            article_headline = itemView.findViewById(R.id.article_headline);
            article_snippet = itemView.findViewById(R.id.article_snippet);
        }

        public void bind(Article article) {
            String header = String.format("FIRST PAGE ARTICLE IN %s", article.sectionName);
            first_page_header.setText(header);
            article_pub_date.setText(article.publishDate);
            article_headline.setText(article.headline.main);
            article_snippet.setText(article.snippet);
        }
    }
}
