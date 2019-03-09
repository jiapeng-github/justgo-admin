package com.justgo.admin.dto.menu;

public class MenuItemDTO {
    /**
     * 菜单名称
     */
    private String name;
    /**
     * URL
     */
    private String url;

    /**
     * 获取  菜单名称
     *
     * @return 菜单名称
     */
    public String getName() {
        return this.name;
    }

    /**
     * 设置  菜单名称
     *
     * @param name 菜单名称
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取  URL
     *
     * @return URL
     */
    public String getUrl() {
        return this.url;
    }

    /**
     * 设置  URL
     *
     * @param url URL
     */
    public void setUrl(String url) {
        this.url = url;
    }
}
