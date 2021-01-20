package com.demo.springboot.web.controller;

import com.demo.springboot.web.vo.echarts.HistogramVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 测试echarts图表
 * @date 2021/1/20 9:41
 * @see
 */
@Controller
@RequestMapping("/echarts")
public class EchartsController {

    @GetMapping("/histogram/index")
    public String histogramIndex() {
        System.out.println("hhaha====>>>热部署111111.。。springloaded。。");
        return "echarts/histogram";
    }

    /**
     * @param
     * @return
     * @author Wenyi Cao
     * @version 1.0
     * @description 堆叠区域图
     * @date 2021/1/20 9:57
     */
    @GetMapping("/histogram/data")
    @ResponseBody
    public HistogramVo getData() {
        // ....做个假数据
        HistogramVo ret = new HistogramVo();
        ret.setTitle("我的堆叠区域图");
        String[] legendArr = new String[]{"汽车票", "火车票", "船票", "飞机票"};
        ret.setLegend(Arrays.asList(legendArr));
        String[] xAxisArr = new String[]{"1月份", "2月份", "3月份", "4月份", "5月份", "6月份"};
        ret.setXAxis(Arrays.asList(xAxisArr));
        // 成员内部类
        HistogramVo.SeriesItem item1 = ret.new SeriesItem("汽车票", "line", "总量", Arrays.asList(13, 345, 456, 24, 356, 709), "");
        HistogramVo.SeriesItem item2 = ret.new SeriesItem("火车票", "line", "总量", Arrays.asList(23, 45, 46, 234, 36, 789), "");
        HistogramVo.SeriesItem item3 = ret.new SeriesItem("船票", "line", "总量", Arrays.asList(123, 245, 456, 234, 356, 79), "");
        HistogramVo.SeriesItem item4 = ret.new SeriesItem("飞机票", "line", "总量", Arrays.asList(323, 345, 46, 334, 356, 989), "{normal:{show:true,position:'top'}}");
        List<HistogramVo.SeriesItem> seriesItemList = new ArrayList<>(4);
        seriesItemList.add(item1);
        seriesItemList.add(item2);
        seriesItemList.add(item3);
        seriesItemList.add(item4);
        ret.setSeries(seriesItemList);
        return ret;
    }
}
