package service.htmltolecture;

import data.Lecture;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class JsoupHtmlToLectureMap implements HtmlToLectureMap {
    public static final String ARTICLE_SELECT = "article.article";
    public static final String TOPIC_SELECT = "h1.article-head__title";
    public static final String COURSE_SELECT = "span.lecture-source-meta__link";
    public static final String LEVEL_SELECT = "div.lecture-source-meta__level";
    public static final String LECTURE_SELECT = "";
    private static final String NEXT_LINK_SELECT = "div.lectures-nav__next";

    public final String HTML;
    public final Document doc;


    public JsoupHtmlToLectureMap(String html) {
        this.HTML = html;
        this.doc = Jsoup.parse(this.HTML);
    }

    @Override
    public Lecture map() {

        if (HTML.isBlank()) {
            return new Lecture("EmptyLecture", "", 0, 0, "");
        }

        return new Lecture(getTopic(), getCourse(), getLevel(), getLecture(), getArticle());
    }

    public String getLinkToNext() {
        return doc
                .select(ARTICLE_SELECT)
                .select(NEXT_LINK_SELECT)
                .select("a")
                .attr("href");
    }

    public String getArticle() {
        return HTML;
    }

    private int getLecture() {
        String[] split = getFromArticle(LEVEL_SELECT).split(" ");
        return Integer.parseInt(split[split.length - 1]);
    }

    private int getLevel() {
        String[] split = getFromArticle(LEVEL_SELECT).split(" ");
        return Integer.parseInt(split[1]);
    }

    private String getCourse() {
        return getFromArticle(COURSE_SELECT);
    }

    private String getTopic() {
        return getFromArticle(TOPIC_SELECT);
    }

    private String getFromArticle(String select) {
        return doc
                .select(ARTICLE_SELECT)
                .select(select)
                .first()
                .text();
    }
}
