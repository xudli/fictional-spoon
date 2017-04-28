package org.deepinfo.proton.util;


/**
 * Created by lixudong on 2017/4/26.
 */
public class EsUtils {
//
//    public static final String CLUSTER_NAME = "andlinks_es"; //实例名称
//    private static final String IP = "192.168.1.21";
//    private static final int PORT = 9300;  //端口
//    //1.设置集群名称：默认是elasticsearch，并设置client.transport.sniff为true，使客户端嗅探整个集群状态，把集群中的其他机器IP加入到客户端
//    //对ES2.0有效
//    private static Settings settings = Settings
//            .settingsBuilder()
//            .put("cluster.name", CLUSTER_NAME)
//            .put("client.transport.sniff", true)
//            .build();
//
//    //创建私有对象
//    private static TransportClient client;
//
//
//    static {
//        try {
//            client = TransportClient.builder().settings(settings).build()
//                    .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName(IP), PORT));
//        } catch (UnknownHostException e) {
//            e.printStackTrace();
//        }
//    }
//
//    /**
//     * 通过prepareCount求索引库文档总数
//     */
//    public static long getCount(String index) {
//        long count = client.prepareCount(index).get().getCount();
//        return count;
//    }
//
//    /**
//     * 通过prepareSearch查询索引库
//     * setQuery(QueryBuilders.matchQuery("name","jack"))
//     * setSearchType(SearchType.QUERY_THEN_FETCH)
//     */
//    public static String listAll() {
//        SearchResponse searchResponse = client.prepareSearch("wb")
//                .setTypes("wb").setQuery(QueryBuilders.matchAllQuery()).get();
//        SearchHits hits = searchResponse.getHits();
//        long total = hits.getTotalHits();
//        System.out.println(total);
//
//        JSONArray arr = new JSONArray();
//        SearchHit[] searchHits = hits.hits();
//        for (SearchHit searchHit : searchHits) {
//            System.out.println(searchHit.getSourceAsString());
//            arr.put(searchHit);
//        }
//        return arr.toString();
//    }
//
//    public static String filter(){
//        SearchResponse searchResponse = client.prepareSearch("wb")
//                .setTypes("wb").setQuery(QueryBuilders.matchAllQuery())//查询所有
//                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                //.setPostFilter(FilterBuilders.rangeFilter("age").from(0).to(19).includeLower(true).includeUpper(true))
//                .setPostFilter(QueryBuilders.rangeQuery("lasttime").from("1492049945222").to("1492049945222"))
//                .setExplain(true)//explain为true表示根据数据相关度排序，和关键字匹配最高的排在前面
//                .get();
//        SearchHits hits = searchResponse.getHits();
//        long total = hits.getTotalHits();
//        System.out.println(total);
//
//        JSONArray arr = new JSONArray();
//        SearchHit[] searchHits = hits.hits();
//        for (SearchHit searchHit : searchHits) {
//            System.out.println(searchHit.getSourceAsString());
//            arr.put(searchHit);
//        }
//        return arr.toString();
//    }
//
//    public static List<Bucket> groupEs(){
//        SearchResponse searchResponse = client.prepareSearch("wb").setTypes("wb")
//                .setQuery(QueryBuilders.matchAllQuery())
//                .setSearchType(SearchType.QUERY_THEN_FETCH)
//                .addAggregation(AggregationBuilders.terms("group_type").field("wbtype").size(0))//wbtype，默认返回10，size(0)也是10
//                .get();
//        Terms terms = searchResponse.getAggregations().get("group_type");
//        List<Bucket> buckets = terms.getBuckets();
////        for (Bucket bucket : buckets) {
////            System.out.println(bucket.getKey() + " " + bucket.getDocCount());
////        }
//        return buckets;
//    }
//
//    /**
//     * 指定分片 查询
//     *
//     * 分片查询
//     Es会将数据均衡的存储在分片中，我们可以指定es去具体的分片或节点钟查询从而进一步的实现es极速查询。
//     1：randomizeacross shards
//     随机选择分片查询数据，es的默认方式
//     2：_local
//     优先在本地节点上的分片查询数据然后再去其他节点上的分片查询，本地节点没有IO问题但有可能造成负载不均问题。数据量是完整的。
//     3：_primary
//     只在主分片中查询不去副本查，一般数据完整。
//     4：_primary_first
//     优先在主分片中查，如果主分片挂了则去副本查，一般数据完整。
//     5：_only_node
//     只在指定id的节点中的分片中查询，数据可能不完整。
//     6：_prefer_node
//     优先在指定你给节点中查询，一般数据完整。
//     7：_shards
//     在指定分片中查询，数据可能不完整。
//     8：_only_nodes
//     可以自定义去指定的多个节点查询，es不提供此方式需要改源码。
//     *
//     */
//    public static String preferenceEs(){
//        SearchResponse searchResponse = client.prepareSearch("wb")
//                .setTypes("wb")
//                //.setPreference("_local")
//                //.setPreference("_primary")
//                //.setPreference("_primary_first")
//                //.setPreference("_only_node:ZYYWXGZCSkSL7QD0bDVxYA")
//                //.setPreference("_prefer_node:ZYYWXGZCSkSL7QD0bDVxYA")
//                .setPreference("_shards:0,1,2")
//                .setQuery(QueryBuilders.matchAllQuery()).setExplain(true).get();
//
//        SearchHits hits = searchResponse.getHits();
//        long total = hits.getTotalHits();
//        System.out.println(total);
//
//        JSONArray arr = new JSONArray();
//        SearchHit[] searchHits = hits.hits();
//        for (SearchHit searchHit : searchHits) {
//            System.out.println(searchHit.getSourceAsString());
//            arr.put(searchHit);
//        }
//        return arr.toString();
//    }
//
//
//
//    public static JSONObject getUserInfoList(String id, String startdate, String stopdate) throws Exception {
//
//        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
//        boolQueryBuilder.must(QueryBuilders.matchQuery("wid", id));
//        //       boolQueryBuilder.must(QueryBuilders.rangeQuery("lasttime").from(startdate).to(stopdate));
//
//
//        SearchResponse response = client.prepareSearch("wb")
//                .setTypes("wb")
//                .setQuery(boolQueryBuilder)
//                /* .addAggregation(
//                        AggregationBuilders.terms("agg").field("user_id").size(20)
//                )*/
//                .addAggregation(
//                        AggregationBuilders.cardinality("agg1").field("wbtype")
//                )
//                //               .addAggregation(AggregationBuilders.dateRange("agg2").field("lasttime").addRange(startdate,stopdate))
//                .addSort("lasttime", SortOrder.DESC)
////                .setSize(2000)
//                .execute()
//                .actionGet();
//        System.out.print(response.toString());
//
//        JSONObject object = new JSONObject();
//        object.put("total", response.getAggregations().getProperty("agg1.value").toString());
////
//        JSONArray arr = new JSONArray();
//        //取前20条不重复的userid
//        List<String> wids = new ArrayList<String>();
//        SearchHit[] hits = response.getHits().getHits();
//        for (SearchHit hit : hits) {
//            if (wids.size() < 20) {
//                String wid = hit.getSource().get("wid").toString();
//                int flag = 1;
//                for (String s : wids) {
//                    if (s.equals(wid)) {
//                        flag = 0;
//                        break;
//                    }
//                }
//                if (flag == 1)//flag为1表示还没有记录该用户
//                {
//                    wids.add(wid);
//                    arr.put(hit.getSourceAsString());
//                }
//            }
//        }
//
//        object.put("idList", arr);
//        System.out.println(object);
//        return object;
//    }
}
