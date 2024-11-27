package service.sitereader;

import org.jsoup.Jsoup;

import java.io.IOException;

public class JsoupSiteReader implements SiteReader {

    @Override
    public String read(String url) {
        try {
            return Jsoup.connect(url).get().html();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            return "";
        }
    }
}
