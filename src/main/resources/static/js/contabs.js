$(function () {
    /**
     * 元素的长度
     * @param t
     */
    function elementLength(t) {
        var e = 0;
        return $(t).each(function () {
            //获取第一个匹配元素外部宽度（默认包括补白和边框）。设置为 true 时，计算边距在内。
            e += $(this).outerWidth(true)
        }), e
    }

    /**
     * 调整标签页条的位置
     * @param e
     */
    function adjustTabLocation(e) {
        //preLength:获取当前元素前面元素的长度
        var preLength = elementLength($(e).prevAll()),
            //posLength:获取当前元素后面元素的长度
            posLength = elementLength($(e).nextAll()),
            //获取标签条上面固定元素长度(<,>, 操作)
            tabLength = elementLength($(".content-tabs").children().not(".J_menuTabs")),
            //标签条长度 - 所有固定标签的长度
            freeLength = $(".content-tabs").outerWidth(true) - tabLength,
            //标签页条向左移动的px距离
            moveLeft = 0;
        //标签页条小于freeLength时
        if ($(".page-tabs-content").outerWidth() < freeLength) tabCloseOther = 0;
        //如果freeLength的位置放得下当前标签和(下一个标签+后面的所有标签)
        else if (posLength <= freeLength - $(e).outerWidth(true) - $(e).next().outerWidth(true)) {
            //如果freeLength的位置放得下(当前标签的下一个标签+后面的所有标签)还有位置剩
            if (freeLength - $(e).next().outerWidth(true) > posLength) {
                //要向左移动的距离就是preLength
                moveLeft = preLength;
                //计算preLength里面的元素要向左移动多少px
                for (var o = e; moveLeft - $(o).outerWidth() > $(".page-tabs-content").outerWidth() - freeLength;)
                    moveLeft -= $(o).prev().outerWidth(), o = $(o).prev()
            }
        } else
        //preLength大于(freeLength-当前元素-当前元素的下一个元素)的距离就是需要移动preLength的元素
            preLength > freeLength - $(e).outerWidth(true) - $(e).prev().outerWidth(true) && (moveLeft = preLength - $(e).prev().outerWidth(true));
        //移动标签页条
        $(".page-tabs-content").animate({marginLeft: 0 - moveLeft + "px"}, "fast")
    }

    /**
     * 点击向左按钮
     * @returns {boolean}
     */
    function tabLeft() {
        //向左边缘的绝对值
        var e = Math.abs(parseInt($(".page-tabs-content").css("margin-left"))),
            //标签页条的长度
            tabsLength = elementLength($(".content-tabs").children().not(".J_menuTabs")),
            //标签条 - 标签页条
            i = $(".content-tabs").outerWidth(true) - tabsLength,
            n = 0;
        if ($(".page-tabs-content").width() < i)return false;
        for (var s = $(".J_menuTab:first"), r = 0; r + $(s).outerWidth(true) <= e;)r += $(s).outerWidth(true), s = $(s).next();
        if (r = 0, elementLength($(s).prevAll()) > i) {
            for (; r + $(s).outerWidth(true) < i && s.length > 0;)r += $(s).outerWidth(true), s = $(s).prev();
            n = elementLength($(s).prevAll())
        }
        $(".page-tabs-content").animate({marginLeft: 0 - n + "px"}, "fast")
    }

    function i() {
        var e = Math.abs(parseInt($(".page-tabs-content").css("margin-left"))),
            a = elementLength($(".content-tabs").children().not(".J_menuTabs")),
            i = $(".content-tabs").outerWidth(true) - a, n = 0;
        if ($(".page-tabs-content").width() < i)return false;
        for (var s = $(".J_menuTab:first"), r = 0; r + $(s).outerWidth(true) <= e;)r += $(s).outerWidth(true), s = $(s).next();
        for (r = 0; r + $(s).outerWidth(true) < i && s.length > 0;)r += $(s).outerWidth(true), s = $(s).next();
        n = elementLength($(s).prevAll()), n > 0 && $(".page-tabs-content").animate({marginLeft: 0 - n + "px"}, "fast")
    }

    /**
     * 展示菜单内容
     * @returns {boolean}
     */
    function showIframe() {
        //url:菜单URL; index:菜单编号; text:菜单名称; hasMenuTab:是否已经存在已经打开的标签
        var url = $(this).attr("href"), index = $(this).data("index"), text = $.trim($(this).text()), hasMenuTab = true;
        //菜单没有URL时返回false
        if (void 0 === url || 0 === $.trim(url).length) return false;
        //检查标签条上有没有打开该菜单,没有就打开,有就去到该标签
        if ($(".J_menuTab").each(function () {
                return $(this).data("id") === url ? ($(this).hasClass("active") ||
                //1.增加当前元素的active,其他元素就去掉active
                ($(this).addClass("active").siblings(".J_menuTab").removeClass("active"),
                        //调整当前标签的位置
                        adjustTabLocation(this),
                        //显示当前的iframe,隐藏其他的iframe
                        $(".J_mainContent .J_iframe").each(function () {
                            return $(this).data("id") === url ? ($(this).show().siblings(".J_iframe").hide(), false) : void 0
                        })
                ), hasMenuTab = false, false) : void 0
            }), hasMenuTab) {
            //创建标签,显示菜单内容
            var s = '<a href="javascript:;" class="active J_menuTab" data-id="' + url + '">' + text + ' <i class="fa fa-times-circle"></i></a>';
            //去掉所有的显示的标签
            $(".J_menuTab").removeClass("active");
            //创建一个iframe框架字符串
            var r = '<iframe class="J_iframe" name="iframe' + index + '" width="100%" height="100%" src="' + url + '" frameborder="0" data-id="' + url + '" seamless></iframe>';
            //1.隐藏所有之前已有的iframe,2.添加显示新增的iframe
            $(".J_mainContent").find("iframe.J_iframe").hide().parents(".J_mainContent").append(r);
            //创建载入动画
            var o = layer.load();
            //关闭载入动画
            $(".J_mainContent iframe:visible").load(function () {
                layer.close(o)
            }),
                //把之前创建的标签添加到标签条上
                $(".J_menuTabs .page-tabs-content").append(s),
                //调整标签页条的位置
                adjustTabLocation($(".J_menuTab.active"))
        }
        //点击菜单刷新对应iframe
        $('.J_iframe[name="iframe'+index+'"]').attr("src",url);
        return false;
    }

    /**
     * 关闭标签
     * @returns {boolean}
     */
    function closeTab() {
        //获取的当前需要关闭的标签id
        var tabId = $(this).parents(".J_menuTab").data("id"),
            //获取的当前需要关闭的标签的宽度
            tabWidth = $(this).parents(".J_menuTab").width();
        //如果要关闭的是当前显示的标签
        if ($(this).parents(".J_menuTab").hasClass("active")) {
            //如果要关闭的标签后面还有标签的情况
            if ($(this).parents(".J_menuTab").next(".J_menuTab").size()) {
                //当前标签的下一个标签的ID
                var nextTabId = $(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").data("id");
                //当前标签的下一个标签设置为active标签
                $(this).parents(".J_menuTab").next(".J_menuTab:eq(0)").addClass("active"),
                    //显示当前标签的下一个标签的iframe
                    $(".J_mainContent .J_iframe").each(function () {
                        return $(this).data("id") === nextTabId ? ($(this).show().siblings(".J_iframe").hide(), false) : void 0
                    });
                //获取标签页条向左移动的px数
                var n = parseInt($(".page-tabs-content").css("margin-left"));
                //如果本身有负移动的情况下,向右移动当前需要关闭的标签的宽度
                0 > n && $(".page-tabs-content").animate({marginLeft: n + tabWidth + "px"}, "fast"),
                    //删除当前要关闭的标签
                    $(this).parents(".J_menuTab").remove(),
                    //删除当前要关闭的标签的iframe
                    $(".J_mainContent .J_iframe").each(function () {
                        return $(this).data("id") === tabId ? ($(this).remove(), false) : void 0
                    })
            }
            //如果要关闭的标签前面还有标签的情况
            if ($(this).parents(".J_menuTab").prev(".J_menuTab").size()) {
                //获取当前关闭前面标签的id内容
                var showTabId = $(this).parents(".J_menuTab").prev(".J_menuTab:last").data("id");
                //设置要关闭的标签的前面一个标签为活动标签
                $(this).parents(".J_menuTab").prev(".J_menuTab:last").addClass("active"),
                    //显示要关闭的标签的前面一个标签的内容
                    $(".J_mainContent .J_iframe").each(function () {
                        return $(this).data("id") === showTabId ? ($(this).show().siblings(".J_iframe").hide(), false) : void 0
                    }),
                    //删除当前要关闭的标签的iframe
                    $(this).parents(".J_menuTab").remove(), $(".J_mainContent .J_iframe").each(function () {
                    return $(this).data("id") === tabId ? ($(this).remove(), false) : void 0
                })
            }
        } else //如果要关闭的不是当前显示的标签
        //移除需要删除的标签
            $(this).parents(".J_menuTab").remove(),
                //移除要删除的标签内容
                $(".J_mainContent .J_iframe").each(function () {
                    return $(this).data("id") === tabId ? ($(this).remove(), false) : void 0
                }),
                //调整标签页条的显示位置
                adjustTabLocation($(".J_menuTab.active"));
        return false
    }

    /**
     * 关闭其他所有的按钮
     */
    function tabCloseOther() {
        $(".page-tabs-content").children("[data-id]").not(":first").not(".active").each(function () {
            $('.J_iframe[data-id="' + $(this).data("id") + '"]').remove(), $(this).remove()
        }), $(".page-tabs-content").css("margin-left", "0")
    }

    /**
     * 定位当前选项卡
     */
    function tabShowActive() {
        adjustTabLocation($(".J_menuTab.active"))
    }

    /**
     * 点击标签条上的标签时显示该标签内容
     */
    function clickTab() {
        if (!$(this).hasClass("active")) {
            var t = $(this).data("id");
            $(".J_mainContent .J_iframe").each(function () {
                return $(this).data("id") === t ? ($(this).show().siblings(".J_iframe").hide(), false) : void 0
            }), $(this).addClass("active").siblings(".J_menuTab").removeClass("active"), adjustTabLocation(this)
        }
    }

    /**
     * 双击标签条上的标签时关闭标签
     */
    function dblclickTab() {
        var t = $('.J_iframe[data-id="' + $(this).data("id") + '"]'), e = t.attr("src"), a = layer.load();
        t.attr("src", e).load(function () {
            layer.close(a)
        })
    }


    $.ajax({
        type: "get",
        //url: "/static/json/adminData.json",
        url: "/user/getMenu?" + Date.parse(new Date()),
        dataType: "json",
        async: true,
        success: function (date) {
            var root = $("#side-menu");
            $.each(date, function (index, item) {
                if (item.list.length === 0) {
                    //console.info("一级菜单:" + index + "," + item.menu);
                    root.append("<li>" +
                        "<a class='J_menuItem' href='" + item.url + "'>" +
                        "<i class='" + item.icon + "'></i>" +
                        "<span class='nav-label'>" + item.menu + "</span>" +
                        "</a></li>");
                } else {
                    var lival = "";
                    lival += "<li><a href='#'>" +
                        "<i class='" + item.icon + "'></i>" +
                        "<span class='nav-label'>" + item.menu + "</span>" +
                        "<span class='fa arrow'></span></a><ul class='nav nav-second-level'>";
                    // console.info("一级菜单:" + index + "," + item.menu);
                    $.each(item.list, function (index, item) {
                        //console.info("   二级菜单内容:" + index + "," + item.name);
                        lival += "<li><a class='J_menuItem' href='" + item.url + "'>" + item.name + "</a></li>";
                    });
                    lival += "</ul></li>";
                    root.append(lival);
                }
            });

            $(".J_menuItem").each(function (t) {
                $(this).attr("data-index", t)
            }),
                //点击菜单时显示菜单页面
                $(".J_menuItem").on("click", showIframe),
                //标签上的关闭按钮
                $(".J_menuTabs").on("click", ".J_menuTab i", closeTab),
                //绑定关闭其他按钮的事件
                $(".J_tabCloseOther").on("click", tabCloseOther),
                //绑定定位当前选项卡的事件
                $(".J_tabShowActive").on("click", tabShowActive),
                //绑定点击标签条上的标签时的事件
                $(".J_menuTabs").on("click", ".J_menuTab", clickTab),
                //绑定双击标签条上的标签时的事件
                $(".J_menuTabs").on("dblclick", ".J_menuTab", dblclickTab),
                //绑定点击向左的按钮时的事件
                $(".J_tabLeft").on("click", tabLeft),
                $(".J_tabRight").on("click", i),
                $(".J_tabCloseAll").on("click", function () {
                    $(".page-tabs-content").children("[data-id]").not(":first").each(function () {
                        $('.J_iframe[data-id="' + $(this).data("id") + '"]').remove(), $(this).remove()
                    }),
                        $(".page-tabs-content").children("[data-id]:first").each(function () {
                            $('.J_iframe[data-id="' + $(this).data("id") + '"]').show(), $(this).addClass("active")
                        }), $(".page-tabs-content").css("margin-left", "0")
                });
            $("#side-menu").metisMenu();
            $("#side-menu>li").click(function () {
                $("body").hasClass("mini-navbar") && NavToggle()
            });
            $("#side-menu>li li a").click(function () {
                $(window).width() < 769 && NavToggle()
            });
        }
    });

});