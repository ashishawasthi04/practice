package com.dbx;

import java.util.*;
import java.util.concurrent.*;

public class WebCrawlerMultiThread {
    /*
    http://scrumbucket.org/tutorials/neo4j-site-crawler/part-2-create-multi-threaded-crawl-manager/
    Multithreading version:
        - since the most time-consuming part is the getUrls(url) function,
        we can use a Leader/Follower approach to parallel processing the getUrls(url),
        - The benefit of also making the read write of result set parallel does not justify sync overhead.
        So, prefer just the master thread to do it.
    */
    static class CrawlerManager {
        public static final int THREAD_COUNT = 10;
        private static final long PAUSE_TIME = 1000;

        private final Set<String> result = new HashSet<>();
        private final List<Future<List<String>>> futures = new ArrayList<>();
        private final ExecutorService executor = Executors.newFixedThreadPool(THREAD_COUNT);
        private static Map<String, List<String>> connectedUrls;


        public static List<String> getUrls(String url) {
            return connectedUrls.getOrDefault(url, new ArrayList<String>());
        }

        public List<String> crawl(String startUrl) {
            submitNewUrl(startUrl);
            try {
                while (checkCrawlerResults()){};
            } catch (InterruptedException ignored) {}
            executor.shutdown();
            return new ArrayList<>(result);
        }

        public void submitNewUrl(String url) {
            if (!result.contains(url)) {
                result.add(url);

                Crawler crawler = new Crawler(url);
                Future<List<String>> future = executor.submit(crawler);
                futures.add(future);
            }
        }

        public boolean checkCrawlerResults() throws InterruptedException {
            Thread.sleep(PAUSE_TIME);
            Iterator<Future<List<String>>> iterator = futures.iterator();
            List<String> newUrls = new ArrayList<>();

            while (iterator.hasNext()) {
                Future<List<String>> future = iterator.next();
                if (future.isDone()) {
                    iterator.remove();
                    try {
                        newUrls.addAll(future.get());
                    } catch (ExecutionException ignored) {}
                }
            }
            // Do this after the while iterator because submitNewUrl will change the futures array list,
            // will cause concurrent modification error
            for (String url : newUrls) {
                submitNewUrl(url);
            }
            return futures.size() > 0;
        }

        private static class Crawler implements Callable<List<String>> {
            private final String url;

            public Crawler(String url) {
                this.url = url;
            }

            @Override
            public List<String> call() {
                return getUrls(url);
            }
        }

        public static void main(String[] args) {
            connectedUrls = new HashMap<>();
            List<String> aChildren = new ArrayList<>();
            aChildren.add("b");
            aChildren.add("c");
            aChildren.add("d");
            aChildren.add("e");
            List<String> bChildren = new ArrayList<>();
            bChildren.add("k");
            bChildren.add("m");
            bChildren.add("d");
            bChildren.add("z");
            List<String> kChildren = new ArrayList<>();
            kChildren.add("o");
            kChildren.add("j");
            kChildren.add("e");
            kChildren.add("z");

            connectedUrls.put("a", aChildren);
            connectedUrls.put("b", bChildren);
            connectedUrls.put("k", kChildren);

            CrawlerManager crawlerManager = new CrawlerManager();
            System.out.println(crawlerManager.crawl("a"));
        }
    }
}
