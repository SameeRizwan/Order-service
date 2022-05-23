package com.devionics.co.Pagination;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;

@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Paging {

    private static final int PAGINATION_STEP = 2;

    public static final String PAGE_SIZE = "3";

    public static final String CURRENT_PAGE = "1";

    private static Logger logger = LogManager.getLogger(Paging.class);

    private boolean nextEnabled;
    private boolean prevEnabled;
    private int pageSize;
    private int pageNumber;

    private List<PageItem> items = new ArrayList<>();

    public void addPageItems(int from, int to, int pageNumber) {
        for (int i = from; i < to; i++) {
            items.add(PageItem.builder()
                              .active(pageNumber != i)
                              .index(i)
                              .pageItemType(PageItemType.PAGE)
                              .build());
        }
    }

    public void last(int pageSize) {
        items.add(PageItem.builder()
                          .active(false)
                          .pageItemType(PageItemType.DOTS)
                          .build());

        items.add(PageItem.builder()
                          .active(true)
                          .index(pageSize)
                          .pageItemType(PageItemType.PAGE)
                          .build());
    }

    public void first(int pageNumber) {
        items.add(PageItem.builder()
                          .active(pageNumber != 1)
                          .index(1)
                          .pageItemType(PageItemType.PAGE)
                          .build());

        items.add(PageItem.builder()
                          .active(false)
                          .pageItemType(PageItemType.DOTS)
                          .build());
    }

    public static Paging of(int totalPages, int pageNumber, int pageSize) {
        Paging paging = new Paging();
        paging.setPageSize(pageSize);
        paging.setNextEnabled(pageNumber != totalPages);
        paging.setPrevEnabled(pageNumber != 1);
        paging.setPageNumber(pageNumber);

        if (totalPages < PAGINATION_STEP * 2 + 6) {
            paging.addPageItems(1, totalPages + 1, pageNumber);

        } else if (pageNumber < PAGINATION_STEP * 2 + 1) {
            paging.addPageItems(1, PAGINATION_STEP * 2 + 4, pageNumber);
            paging.last(totalPages);

        } else if (pageNumber > totalPages - PAGINATION_STEP * 2) {
            paging.first(pageNumber);
            paging.addPageItems(totalPages - PAGINATION_STEP * 2 - 2, totalPages + 1, pageNumber);

        } else {
            paging.first(pageNumber);
            paging.addPageItems(pageNumber - PAGINATION_STEP, pageNumber + PAGINATION_STEP + 1, pageNumber);
            paging.last(totalPages);
        }

        return paging;
    }

    /*
     *   PAGINATION METHODS
     *
     */

    public static void getPagination(Page<?> list, Map<String, Object> map, List<?> list1)
    {
        if (list != null)
        {
            map.put("currentPage", list.getNumber());
            map.put("totalPage", list.getTotalPages());
            map.put("payments", list.getContent());
        }
        else
        {
            int totalSize = list1.size();
            double totalPage = Math.ceil(((double) list1.size() / Double.parseDouble(PAGE_SIZE)));
            map.put("totalEntries", totalSize);
            map.put("currentPage", CURRENT_PAGE);
            map.put("totalPage", (int) totalPage);
            map.put("payments", list1);
        }
    }


}
