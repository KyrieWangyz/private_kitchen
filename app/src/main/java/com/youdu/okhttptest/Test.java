package com.youdu.okhttptest;

import java.util.List;

/**
 * Created by cleverlin on 2017/7/5.
 */

public class Test {


    private List<MenuBean> menu;

    public List<MenuBean> getMenu() {
        return menu;
    }

    public void setMenu(List<MenuBean> menu) {
        this.menu = menu;
    }

    public static class MenuBean {
        /**
         * content : 芦笋玉子烧（厚蛋烧）|切墩(初级)|10-30分钟|芦笋:两根>胡萝卜:一小条>鸡蛋:三个>盐:少许>油:少许
         * name : 芦笋玉子烧（厚蛋烧）
         */

        private String content;
        private String name;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
