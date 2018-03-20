package com.qiyi.search.druidclient.test;

import com.qiyi.search.druidclient.entity.Aggregator;
import com.qiyi.search.druidclient.entity.AndFilter;
import com.qiyi.search.druidclient.entity.DruidResponse;
import com.qiyi.search.druidclient.entity.NotFilter;
import com.qiyi.search.druidclient.entity.SelectorFilter;
import com.qiyi.search.druidclient.entity.SumAggregator;
import com.qiyi.search.druidclient.query.TimeseriesQuery;
import com.qiyi.search.druidclient.util.DruidClient;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

public class TimeseriesQueryTest {

    public static void main(String[] args){

        TimeseriesQuery timeseriesQuery = new TimeseriesQuery();
        timeseriesQuery.setDataSource("search_pingback_base_realtime_log");
        timeseriesQuery.setGranularity("FIVE_MINUTE");
        timeseriesQuery.setIntervals("2018-03-13T18:10:41+08:00/2018-03-13T18:15:41+08:00");
        timeseriesQuery.setFilter(new AndFilter().add(new SelectorFilter().dimension("query").value("偶像练习生"))
                                          .add(new NotFilter().field(new SelectorFilter().dimension("platform1Id").value("1"))));
        timeseriesQuery.setAggregations(new Aggregator[] {new SumAggregator("sum__impressionCount").fieldName("impressionCount").longSum()});
        CloseableHttpClient httpClient = HttpClients.createDefault();
        DruidClient druidClient = new DruidClient("http://node19-64-10-bdyf.qiyi.hadoop:8082", httpClient);
        DruidResponse aa = druidClient.execute(timeseriesQuery);
        System.out.println(aa);
    }

}
