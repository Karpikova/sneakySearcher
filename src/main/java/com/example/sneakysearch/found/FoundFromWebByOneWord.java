package com.example.sneakysearch.found;

import com.example.sneakysearch.SneakySearchException;
import com.example.sneakysearch.result.PurchaseObject;
import com.example.sneakysearch.result.Result;
import com.example.sneakysearch.result.ResultLink;
import com.example.sneakysearch.result.ResultLinkWithPurchaseObject;
import com.example.sneakysearch.result.ResultMy;
import com.example.sneakysearch.result.ToResultLink;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public final class FoundFromWebByOneWord implements FoundFromWeb {
    private static final Logger LOGGER = LogManager.getLogger(FoundFromWebByOneWord.class);
    private final static String BASE_URL = "https://zakupki.gov.ru";
    private final static String SEARCH_BASE_URL = "/epz/order/extendedsearch/results.html?";
    private final static String ALL_ELEMENTS_TAG = "div.search-registry-entry-block";
    private final static String PURCHASE_OBJECT_TAG = "div.registry-entry__body-value";
    private final static String PURCHASE_NUMBER_TAG = "div.registry-entry__header-mid__number";
    private final static String PURCHASE_CUSTOMER_TAG = "div.registry-entry__body-href";
    private final static String LINK_TAG = "div.registry-entry__header-mid__number > a";
    private final static int MAX_TRY_COUNT_OF_ONE_PAGE = 10;
    private final String word;
    private final Result result;
    private final ToResultLink toResultLink;

    public FoundFromWebByOneWord(String word, Result result, ToResultLink toResultLink) {
        this.word = word;
        this.result = result;
        this.toResultLink = toResultLink;
    }

    public FoundFromWebByOneWord(String word) {
        this(word,
                new ResultMy(),
                ResultLinkWithPurchaseObject::new);
    }

    @Override
    public Result foundFromWeb() throws SneakySearchException {
        LOGGER.info("Ищу " + word);
        int pageNumber = 1;
        boolean endOfSearchIsAchieved = false;
        do {
            final Document document = getHtml(pageNumber);
            final Elements select = document.select(ALL_ELEMENTS_TAG);
            if (select.isEmpty()) {
                endOfSearchIsAchieved = true;
            } else {
                processSelect(select);
                pageNumber++;
            }
        }
        while (!endOfSearchIsAchieved);

        return result;
    }

    private void processSelect(Elements select) {
        for (Element purchase : select) {
            createResult(purchase);
        }
    }

    private void createResult(Element purchase) {
        final String name = purchase.select(PURCHASE_OBJECT_TAG).text();
        final String number = purchase.select(PURCHASE_NUMBER_TAG).text();
        final String customer = purchase.select(PURCHASE_CUSTOMER_TAG).text();
        final String link = BASE_URL + purchase.select(LINK_TAG).first().attr("href");
        final ResultLink resultLink = toResultLink.resultLink(word, name, number, customer, link);
        result.addLink(resultLink);
        printToConsole(resultLink);
    }

    private void printToConsole(ResultLink result) {
        final PurchaseObject purchaseObject = result.purchaseObject();
        System.out.println("Объект закупки: " + purchaseObject.name());
        System.out.println("Заказчик: " + purchaseObject.customer());
        System.out.println("Ссылка: " + BASE_URL + result.link());
        System.out.println();
    }

    private Document getHtml(int pageNumber) throws SneakySearchException {
        final String url = createUrl(pageNumber);
        LOGGER.info("Вызываю " + url);
        int tryCount = 0;
        Document document = null;
        do {
            try {
                document = Jsoup.connect(url).maxBodySize(0).get();
            } catch (Exception ex) {
                LOGGER.error("Ошибка запроса " + url);
                tryCount++;
                if (tryCount == MAX_TRY_COUNT_OF_ONE_PAGE) {
                    throw new SneakySearchException(url);
                }
            }
        } while (document == null);
        return document;
    }

    private String createUrl(int page) {
        return BASE_URL + SEARCH_BASE_URL + "searchString=" + word +
                "&morphology=on" +
                "&search-filter=%D0%94%D0%B0%D1%82%D0%B5+%D1%80%D0%B0%D0%B7%D0%BC%D0%B5%D1%89%D0%B5%D0%BD%D0%B8%D1%8F" +
                "&pageNumber=" + page +
                "&sortDirection=false" +
                "&recordsPerPage=_50" +
                "&showLotsInfoHidden=false" +
                "&sortBy=UPDATE_DATE" +
                "&fz44=on" +
                "&fz223=on" +
                "&pc=on" +
                "&currencyIdGeneral=-1";
    }
}
