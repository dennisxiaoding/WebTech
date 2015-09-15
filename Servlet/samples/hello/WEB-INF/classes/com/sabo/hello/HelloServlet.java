package com.sabo.hello;

import javax.servlet.AsyncContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * Created by canbin.zhang on 2015/9/15.
 */
@WebServlet(urlPatterns = "/AsyncServletSample", asyncSupported = true)
public class HelloServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter out = resp.getWriter();
        out.println("进入Servlet的时间:" + new Date() + ".");
        out.flush();
        //在子线程里执行业务，并且子线程负责响应，主线程退出
        /////////////////////////////////////////////////////////
        AsyncContext ctx = req.startAsync();
        new Thread(new Executor(ctx)).start();

        /////////////////////////////////////////////////////////
        out.println("结束Servlet时间:" + new Date() + ".");
        out.flush();
    }

    class Executor implements Runnable {
        AsyncContext ctx;

        public Executor(AsyncContext ctx) {
            this.ctx = ctx;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(3000);
                PrintWriter out = ctx.getResponse().getWriter();
                out.println("业务处理完毕的时间:" + new Date() + ".");
                out.flush();
                ctx.complete();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
