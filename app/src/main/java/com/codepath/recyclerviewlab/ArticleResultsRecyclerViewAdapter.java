package com.codepath.recyclerviewlab;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.codepath.recyclerviewlab.models.Article;

import java.util.ArrayList;
import java.util.List;

public class ArticleResultsRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<Article> articles = new ArrayList<>();

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.fragment_article_result, parent, false);

        return new ArticleViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ArticleViewHolder newHolder = (ArticleViewHolder) holder;
        newHolder.bind(articles.get(position));
    }

    @Override
    public int getItemCount() {
        return articles.size();
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
}
