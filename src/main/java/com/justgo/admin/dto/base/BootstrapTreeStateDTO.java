package com.justgo.admin.dto.base;

public class BootstrapTreeStateDTO {

    /**
     * 指示一个节点是否处于checked状态，用一个checkbox图标表示。
     */
    private boolean checked;
    /**
     * 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     */
    private boolean disabled;
    /**
     * 指示一个节点是否处于展开状态。
     */
    private boolean expanded;
    /**
     * 指示一个节点是否处于展开状态。
     */
    private boolean selected;

    /**
     * 获取  指示一个节点是否处于checked状态，用一个checkbox图标表示。
     *
     * @return 指示一个节点是否处于checked状态，用一个checkbox图标表示。
     */
    public boolean isChecked() {
        return this.checked;
    }

    /**
     * 设置  指示一个节点是否处于checked状态，用一个checkbox图标表示。
     *
     * @param checked 指示一个节点是否处于checked状态，用一个checkbox图标表示。
     */
    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    /**
     * 获取  指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     *
     * @return 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     */
    public boolean isDisabled() {
        return this.disabled;
    }

    /**
     * 设置  指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     *
     * @param disabled 指示一个节点是否处于disabled状态。（不是selectable，expandable或checkable）
     */
    public void setDisabled(boolean disabled) {
        this.disabled = disabled;
    }

    /**
     * 获取  指示一个节点是否处于展开状态。
     *
     * @return 指示一个节点是否处于展开状态。
     */
    public boolean isExpanded() {
        return this.expanded;
    }

    /**
     * 设置  指示一个节点是否处于展开状态。
     *
     * @param expanded 指示一个节点是否处于展开状态。
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * 获取  指示一个节点是否处于展开状态。
     *
     * @return 指示一个节点是否处于展开状态。
     */
    public boolean isSelected() {
        return this.selected;
    }

    /**
     * 设置  指示一个节点是否处于展开状态。
     *
     * @param selected 指示一个节点是否处于展开状态。
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }


}
