package com.example.proyecto.model;

import java.util.List;

public class NewsResponse {
    private List<Article> results;

    public List<Article> getResults() {
        return results;
    }

    public void setResults(List<Article> results) {
        this.results = results;
    }

    public static class Article {
        private String title;
        private String description;
        private String link;
        private String image_url;

        // Getters and setters
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getImageUrl() {
            return image_url;
        }

        public void setImageUrl(String image_url) {
            this.image_url = image_url;
        }
    }
}
