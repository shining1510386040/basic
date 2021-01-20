package com.demo.springboot.web.vo.echarts;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description 柱状图Vo（堆叠区域图）
 * @date 2021/1/20 9:45
 * @see
 */
@Data
public class HistogramVo {
    /**
     * 图表名称
     */
    private String title;

    /**
     * 图例
     */
    private List<String> legend;

    /**
     * X轴坐标
     */
    private List<String> xAxis;

    /**
     * 展示数据
     */
    private List<SeriesItem> series;

    /**
     * series系列数据的每一项
     */
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public class SeriesItem {
        private String name;
        private String type;
        private String stack;
        private List<Number> data;
        // {
        //                    normal: {
        //                        show: true,
        //                        position: 'top'
        //                    }
        //                }
        private String label;
    }
}
