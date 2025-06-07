package com.example.service;

import io.micronaut.http.client.HttpClient;
import io.micronaut.http.client.annotation.Client;
import jakarta.inject.Singleton;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Singleton
public class VnExpressScraperService {

    private final HttpClient httpClient;

    public VnExpressScraperService(@Client HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    public List<Map<String, String>> scrapeNews() {
        List<Map<String, String>> newsItems = new ArrayList<>();
        try {
            String url = "https://vnexpress.net/tag/thpt-quoc-gia-578893";
            String response = httpClient.toBlocking().retrieve(url);
            Document doc = Jsoup.parse(response, StandardCharsets.UTF_8.name());

            // Lấy tất cả bài báo
            Elements articles = doc.select("article.item-news");
            // System.out.println("Found " + articles.size() + " articles");

            // Giới hạn 5 bài báo
            for (Element article : articles.subList(0, Math.min(articles.size(), 5))) {
                Map<String, String> news = new HashMap<>();

                // Lấy tiêu đề từ thẻ có id="title_brandsafe_video"
                Element titleElement = article.selectFirst("#title_brandsafe_video");
                String title = titleElement != null ? titleElement.text().trim() : "Không có tiêu đề";

                // Lấy link từ thẻ a gần nhất hoặc trong cùng article
                String link = "";
                Element linkElement = titleElement != null ? titleElement.selectFirst("a") : null;
                if (linkElement == null) {
                    // Thử lấy từ div.thumb-art a hoặc p.description a
                    linkElement = article.selectFirst("div.thumb-art a, p.description a");
                }
                if (linkElement != null) {
                    link = linkElement.attr("href").trim();
                }

                // Đảm bảo link đầy đủ
                if (!link.isEmpty()) {
                    if (!link.startsWith("http")) {
                        link = "https://vnexpress.net" + link;
                    }
                } else {
                    // System.out.println("Không tìm thấy link cho bài báo: " + title);
                }
                news.put("title", title);
                news.put("url", link);
                // System.out.println("Title: " + title + ", Link: " + link);

                // Lấy mô tả từ p.description
                Element descriptionElement = article.selectFirst("p.description");
                String description = descriptionElement != null ? descriptionElement.text().trim() : "Không có mô tả";
                news.put("description", description);

                // Lấy hình ảnh từ div.thumb-art img
                Element imageElement = article.selectFirst("div.thumb-art img");
                String image = imageElement != null
                        ? (imageElement.attr("data-src").isEmpty() ? imageElement.attr("src").trim() : imageElement.attr("data-src").trim())
                        : "";
                news.put("image", image);

                newsItems.add(news);
            }

            if (newsItems.isEmpty()) {
                // System.out.println("Không tìm thấy bài báo nào");
                Map<String, String> error = new HashMap<>();
                error.put("error", "Không tìm thấy bài báo");
                newsItems.add(error);
            }
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", "Không thể tải tin tức: " + e.getMessage());
            newsItems.add(error);
            // System.err.println("Scrape error: " + e.getMessage());
        }
        return newsItems;
    }
}