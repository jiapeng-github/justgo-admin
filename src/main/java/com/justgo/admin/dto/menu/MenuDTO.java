package com.justgo.admin.dto.menu;

import java.util.List;

public class MenuDTO {
    private Long id;
    /**
     * 一级菜单名称
     */
    private String menu;
    /**
     * 图标
     */
    private String icon;
    /**
     * 二级菜单
     */
    private List<MenuItemDTO> list;

    /**
     * 获取  一级菜单名称
     *
     * @return 一级菜单名称
     */
    public String getMenu() {
        return this.menu;
    }

    /**
     * 设置  一级菜单名称
     *
     * @param menu 一级菜单名称
     */
    public void setMenu(String menu) {
        this.menu = menu;
    }

    /**
     * 获取  图标
     *
     * @return 图标
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * 设置  图标
     *
     * @param icon 图标
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取  二级菜单
     *
     * @return 二级菜单
     */
    public List<MenuItemDTO> getList() {
        return this.list;
    }

    /**
     * 设置  二级菜单
     *
     * @param list 二级菜单
     */
    public void setList(List<MenuItemDTO> list) {
        this.list = list;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
