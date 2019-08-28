package com.itheima.initFunction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class StreamInit {
  private final   InputStream is;
  private final   OutputStream os;
  private final HttpServletRequest request;
  private final HttpServletResponse response;


    public StreamInit(HttpServletRequest request, HttpServletResponse response) throws IOException {
        this.request=request;
        this.response=response;

        is = this.request.getSession().getServletContext().getResourceAsStream("health_web/src/lib/report_template.xlsx");
        os = this.response.getOutputStream();
    }

    public void callTheMan(){
        response.setHeader("Content-Disposition","attachment;filename=report.xlsx"); //告诉浏览器去下载
        response.setHeader("Content-Type","application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public InputStream getIs() {
        return is;
    }

    public OutputStream getOs() {
        return os;
    }

    public void closeStream() throws IOException {
        is.close();
        os.close();
    }
}
