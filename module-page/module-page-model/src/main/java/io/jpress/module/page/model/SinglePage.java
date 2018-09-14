package io.jpress.module.page.model;

import io.jboot.db.annotation.Table;
import io.jboot.utils.StringUtils;
import io.jpress.module.page.model.base.BaseSinglePage;

/**
 * Generated by Jboot.
 */
@Table(tableName = "single_page", primaryKey = "id")
public class SinglePage extends BaseSinglePage<SinglePage> {

    public static final String STATUS_NORMAL = "normal";
    public static final String STATUS_DRAFT = "draft";
    public static final String STATUS_TRASH = "trash";


    public boolean isNormal() {
        return STATUS_NORMAL.equals(getStatus());
    }

    public boolean isDraft() {
        return STATUS_DRAFT.equals(getStatus());
    }

    public boolean isTrash() {
        return STATUS_TRASH.equals(getStatus());
    }


    public String getHtmlView() {
        return StringUtils.isBlank(getStyle()) ? "page.html" : "page_" + getStyle().trim() + ".html";
    }

}
