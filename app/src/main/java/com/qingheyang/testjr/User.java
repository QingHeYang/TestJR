package com.qingheyang.testjr;

import java.util.List;

/**
 * project: TestJR
 * package: com.qingheyang.testjr
 * creater: qingheyang
 * date: 2018/5/17
 * describe: javaBean
 */
public class User {

    private List<MessageInfoBean> messageInfo;

    public List<MessageInfoBean> getMessageInfo() {
        return messageInfo;
    }

    public void setMessageInfo(List<MessageInfoBean> messageInfo) {
        this.messageInfo = messageInfo;
    }

    public static class MessageInfoBean {

        private String msg1;
        private String author;
        private String view;

        public String getMsg1() {
            return msg1;
        }

        public void setMsg1(String msg1) {
            this.msg1 = msg1;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getView() {
            return view;
        }

        public void setView(String view) {
            this.view = view;
        }
    }
}
