package com.demo.springboot.springlucence.service.impl;

import com.alibaba.fastjson.JSON;
import com.demo.springboot.springlucence.entity.WorkOrderRecord;
import com.demo.springboot.springlucence.service.LuceneService;
import com.demo.springboot.springlucence.utils.CommonUtils;
import com.demo.springboot.springlucence.vo.PageQuery;
import com.github.pagehelper.PageInfo;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Wenyi Cao
 * @version 1.0
 * @link
 * @description lucence 全文搜索实现
 * @date 2021/4/26 10:42
 * @see
 */
@Service
public class LuceneServiceImpl implements LuceneService {

    @Autowired
    private IndexWriter indexWriter;

    @Autowired
    private Analyzer analyzer;

    @Autowired
    private SearcherManager searcherManager;


    @Override
    public void createWorkOrderRecordIndex(List<LinkedHashMap> list) throws IOException {
        List<Document> docs = new ArrayList<>();
        for (Map p : list) {
            // lucence 的文档
            Document doc = new Document();
            doc.add(new StringField("id", p.get("id") + "", Field.Store.YES));
            doc.add(new TextField("orgId", p.get("orgId") + "", Field.Store.YES));
            doc.add(new TextField("orgName", p.get("orgName") + "", Field.Store.YES));
            doc.add(new TextField("workOrderNo", p.get("workOrderNo") + "", Field.Store.YES));
            doc.add(new TextField("currentHandler", p.get("currentHandler") + "", Field.Store.YES));
            doc.add(new TextField("currentHandlerLabel", p.get("currentHandlerLabel") + "", Field.Store.YES));
            doc.add(new TextField("currentHandlerAvatar", p.get("currentHandlerAvatar") + "", Field.Store.YES));
            doc.add(new TextField("submitAccount", p.get("submitAccount") + "", Field.Store.YES));
            // 存储数字、日期(毫秒数)
            doc.add(new IntPoint("workOrderStatus", (int) p.get("workOrderStatus")));
            // 关键字1
            doc.add(new TextField("content", p.get("content") + "", Field.Store.YES));
            // map-->_mXXX list-->_lXXx  k-v k-[v1,v2]
            doc.add(new TextField("_lassignList", JSON.toJSONString(p.get("assignList")), Field.Store.YES));
            doc.add(new TextField("_lreplayList", JSON.toJSONString(p.get("replayList")), Field.Store.YES));
            doc.add(new TextField("_lfeedbackList", JSON.toJSONString(p.get("feedbackList")), Field.Store.YES));
            // 关键字2
            docs.add(doc);
        }
        indexWriter.addDocuments(docs);
        // indexWriter.updateDocument(docs);
        indexWriter.commit();
        //  indexWriter.close();
    }


    @Override
    public PageQuery searchWorkOrderRecord(PageQuery pageQuery) throws Exception, ParseException {
        searcherManager.maybeRefresh();
        IndexSearcher indexSearcher = searcherManager.acquire();
//        Analyzer analyzer = new IKAnalyzer(true);
//        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(Paths.get(LuceneConfig.LUCENEINDEXPATH)));
//        //索引查询器
//        IndexSearcher indexSearcher = new IndexSearcher(directoryReader);

        WorkOrderRecord params = pageQuery.getParams();
        Map<String, String> queryParam = pageQuery.getQueryParam();
        BooleanQuery.Builder builder = new BooleanQuery.Builder();
        Sort sort = new Sort();
        // 排序规则
        pageQuery.getSort();
        // todo ...

        // 模糊匹配,匹配词
        String keyStr = queryParam.get("keyword");
        if (!"".equals(keyStr.replaceAll(" ", ""))) {
            builder.add(new QueryParser("content", analyzer).parse(keyStr), BooleanClause.Occur.MUST)
                    .add(new QueryParser("_lfeedbackList", analyzer).parse(keyStr), BooleanClause.Occur.MUST)
                    .add(new QueryParser("_lreplayList", analyzer).parse(keyStr), BooleanClause.Occur.MUST);
        }
//        // 精确查询
//        if (params != null && params.getCid1() != null) {
//            builder.add(new TermQuery(new Term("cid1", String.valueOf(params.getCid1()))), BooleanClause.Occur.MUST);
//        }
//        if (params != null && params.getGetEndTimeForL() != null) {
//            builder.add(new TermQuery(new Term("cid1", String.valueOf(params.getCid1()))), BooleanClause.Occur.MUST);
//        }
//        if (queryParam.get("lowerPrice") != null && queryParam.get("upperPrice") != null) {
//            // 价格范围查询
//            builder.add(FloatPoint.newRangeQuery("price", Float.parseFloat(queryParam.get("lowerPrice")),
//                    Float.parseFloat(queryParam.get("upperPrice"))), BooleanClause.Occur.MUST);
//        }
        PageInfo pageInfo = pageQuery.getPageInfo();
        TopDocs topDocs = indexSearcher.search(builder.build(), pageInfo.getPageNum() * pageInfo.getPageSize(), sort);

        pageInfo.setTotal(topDocs.totalHits.value);
        ScoreDoc[] hits = topDocs.scoreDocs;
        List<WorkOrderRecord> pList = new ArrayList<>();
        for (int i = 0; i < hits.length; i++) {
            Document doc = indexSearcher.doc(hits[i].doc);
            WorkOrderRecord record = new WorkOrderRecord();
            record.setId(doc.get("id"));
            record.setWorkOrderNo(doc.get("workOrderNo"));
            String lreplayList = doc.get("_lreplayList");
            // json -> list
//            record.setReplayList();
            // todo
            pList.add(record);
        }
        pageQuery.setPageInfo(pageInfo);
        pageQuery.setResults(pList);
        return pageQuery;
    }

    @Override
    public void deleteWorkOrderRecordIndexById(String id) throws IOException {
        indexWriter.deleteDocuments(new Term("id", id));
        indexWriter.commit();
    }
}
