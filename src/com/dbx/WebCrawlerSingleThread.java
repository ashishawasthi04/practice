package com.dbx;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.net.URL;
import java.util.*;

/*
Multithreading Things to consider:
which part is most time-consuming. The part that the thread knows the url to be visited and getting back list of URLs.
Network latency, webpage content parser and processing.

Should visit URL? e.g. define the depth of crawling, type of urls e.g. the one without ending in .pdf, size of the result set etc.
Crawler failed, will throw ExecutionException
Sleep the master thread a little each time after the checking of futures manager thread will not use all the resources

Simple single thread DFS, BFS
*/
public class WebCrawlerSingleThread {
    int TIMEOUT = 1000;

    public List<String> crawlBFS(String url) throws IOException {
        Set<String> result = new HashSet<>();
        Queue<String> q = new LinkedList<>();
        q.add(url);
        result.add(url);

        while (!q.isEmpty()) {
            String currUrl = q.poll();
            List<String> childrenUrls = getUrls(currUrl);
            if (childrenUrls != null) {
                for (String childUrl : childrenUrls) {
                    if (!result.contains(childUrl)) {
                        q.offer(childUrl);
                        result.add(childUrl);
                    }
                }
            }
        }
        return new ArrayList<>(result);
    }

    public List<String> crawlDFS(String url) throws IOException {
        Set<String> result = new HashSet<>();
        crawlDFSHelper(result, url);
        return new ArrayList<>(result);
    }

    public void crawlDFSHelper(Set<String> result, String url) throws IOException {
        if (url == null || result.contains(url)) return;
        result.add(url);
        List<String> childrenUrls = getUrls(url);
        if (childrenUrls != null) {
            for (String childUrl : childrenUrls) {
                crawlDFSHelper(result, childUrl);
            }
        }
    }

    // How the urls are gathered:
    public List<String> getUrls(String urlStr) throws IOException {
        List<String> urlList = new ArrayList<>();

        URL url = new URL(urlStr);
        Document doc = Jsoup.parse(url, TIMEOUT);

        Elements links = doc.select("a[href]");
        for (Element link : links) {
            String href = link.attr("href");
            if (StringUtils.isBlank(href) || href.startsWith("#")) {
                continue;
            }
            urlList.add(href);
        }
        return urlList;
    }
}
