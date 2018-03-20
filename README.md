# druid-client
This is a client that can be used to make Druid queries easily, through generating JSON.  

## Installation  
To install this library, run mvn install. You can then include it in projects with Maven by using the dependency:  

···jar包引用
<dependency>
  <groupId>com.qiyi.search</groupId>
  <artifactId>druid-client</artifactId>  
  <version>1.0-SNAPSHOT</version>  
</dependency>  

## Example   
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
        DruidClient druidClient = new DruidClient("****此处需要设置****", httpClient);
        DruidResponse aa = druidClient.execute(timeseriesQuery);
        System.out.println(aa);
    }

}


主要参考：https://github.com/LinxTeng/druid-java-client
