package com.justgo.admin.dto.base;

import java.util.List;

/**
 * bootstrap tree
 */
public class BootstrapTreeDTO {
    private Long id;
    /**
     * 列表树节点上的文本，通常是节点右边的小图标。
     */
    private String text;
    /**
     * 列表树节点上的图标，通常是节点左边的图标。
     */
    private String icon;
    /**
     * 当某个节点被选择后显示的图标，通常是节点左边的图标。
     */
    private String selectedIcon;
    /**
     * 结合全局enableLinks选项为列表树节点指定URL。
     */
    private String href;
    /**
     * 指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     */
    private boolean selectable;
    /**
     * 一个节点的初始状态。
     */
    private BootstrapTreeStateDTO state;
    /**
     * 节点的前景色，覆盖全局的前景色选项。
     */
    private String color;
    /**
     * 节点的背景色，覆盖全局的背景色选项。
     */
    private String backColor;
    /**
     * 父id
     */
    private Long fid;

    /**
     * 子模块
     */
    private List<BootstrapTreeDTO> nodes;


    /**
     * 获取  列表树节点上的文本，通常是节点右边的小图标。
     *
     * @return 列表树节点上的文本，通常是节点右边的小图标。
     */
    public String getText() {
        return this.text;
    }

    /**
     * 设置  列表树节点上的文本，通常是节点右边的小图标。
     *
     * @param text 列表树节点上的文本，通常是节点右边的小图标。
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * 获取  列表树节点上的图标，通常是节点左边的图标。
     *
     * @return 列表树节点上的图标，通常是节点左边的图标。
     */
    public String getIcon() {
        return this.icon;
    }

    /**
     * 设置  列表树节点上的图标，通常是节点左边的图标。
     *
     * @param icon 列表树节点上的图标，通常是节点左边的图标。
     */
    public void setIcon(String icon) {
        this.icon = icon;
    }

    /**
     * 获取  当某个节点被选择后显示的图标，通常是节点左边的图标。
     *
     * @return 当某个节点被选择后显示的图标，通常是节点左边的图标。
     */
    public String getSelectedIcon() {
        return this.selectedIcon;
    }

    /**
     * 设置  当某个节点被选择后显示的图标，通常是节点左边的图标。
     *
     * @param selectedIcon 当某个节点被选择后显示的图标，通常是节点左边的图标。
     */
    public void setSelectedIcon(String selectedIcon) {
        this.selectedIcon = selectedIcon;
    }

    /**
     * 获取  结合全局enableLinks选项为列表树节点指定URL。
     *
     * @return 结合全局enableLinks选项为列表树节点指定URL。
     */
    public String getHref() {
        return this.href;
    }

    /**
     * 设置  结合全局enableLinks选项为列表树节点指定URL。
     *
     * @param href 结合全局enableLinks选项为列表树节点指定URL。
     */
    public void setHref(String href) {
        this.href = href;
    }

    /**
     * 获取  指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     *
     * @return 指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     */
    public boolean isSelectable() {
        return this.selectable;
    }

    /**
     * 设置  指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     *
     * @param selectable 指定列表树的节点是否可选择。设置为false将使节点展开，并且不能被选择。
     */
    public void setSelectable(boolean selectable) {
        this.selectable = selectable;
    }

    /**
     * 获取  一个节点的初始状态。
     *
     * @return 一个节点的初始状态。
     */
    public BootstrapTreeStateDTO getState() {
        return this.state;
    }

    /**
     * 设置  一个节点的初始状态。
     *
     * @param state 一个节点的初始状态。
     */
    public void setState(BootstrapTreeStateDTO state) {
        this.state = state;
    }

    /**
     * 获取  节点的前景色，覆盖全局的前景色选项。
     *
     * @return 节点的前景色，覆盖全局的前景色选项。
     */
    public String getColor() {
        return this.color;
    }

    /**
     * 设置  节点的前景色，覆盖全局的前景色选项。
     *
     * @param color 节点的前景色，覆盖全局的前景色选项。
     */
    public void setColor(String color) {
        this.color = color;
    }

    /**
     * 获取  节点的背景色，覆盖全局的背景色选项。
     *
     * @return 节点的背景色，覆盖全局的背景色选项。
     */
    public String getBackColor() {
        return this.backColor;
    }

    /**
     * 设置  节点的背景色，覆盖全局的背景色选项。
     *
     * @param backColor 节点的背景色，覆盖全局的背景色选项。
     */
    public void setBackColor(String backColor) {
        this.backColor = backColor;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取  子模块
     *
     * @return 子模块
     */
    public List<BootstrapTreeDTO> getNodes() {
        return this.nodes;
    }

    /**
     * 设置  子模块
     *
     * @param nodes 子模块
     */
    public void setNodes(List<BootstrapTreeDTO> nodes) {
        this.nodes = nodes;
    }

    /**
     * 获取  父id
     *
     * @return 父id
     */
    public Long getFid() {
        return this.fid;
    }

    /**
     * 设置  父id
     *
     * @param fid 父id
     */
    public void setFid(Long fid) {
        this.fid = fid;
    }
}
