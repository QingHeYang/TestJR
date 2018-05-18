package com.qingheyang.testjr;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * project: TestJR
 * package: com.qingheyang.testjr
 * creater: qingheyang
 * date: 2018/5/18
 * describe:MainPresenter的单元测试
 */
public class MainPresenterTest {
    private MainPresenter presenter;

    @Before
    public void setUp() throws Exception {
        RxTools.asyncToSync();
        presenter = new MainPresenter();
    }

    @After
    public void tearDown() throws Exception {
        RxTools.resetPlugins();
        presenter = null;
    }

    @Test
    public void getUser() {
        presenter.getUser(HttpUtils.URL);
    }
}