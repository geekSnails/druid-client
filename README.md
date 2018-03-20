# druid-client
提供一个使用Java访问Druid的便利客户端，主要通过拼接访问Drui的JSON，来达到访问Druid的目的。
#Installation
To install this library, run mvn install. You can then include it in projects with Maven by using the dependency:

<dependency>
  <groupId>com.qiyi.search</groupId>
  <artifactId>druid-client</artifactId>
  <version>1.0-SNAPSHOT</version>
</dependency>

#Example
see the package com.qiyi.search.druidclient.test, use TimeSeries search like below:

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


主要参考：https://github.com/LinxTeng/druid-java-client
