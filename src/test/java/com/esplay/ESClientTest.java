package com.esplay;
/*
 * Created by nvishwarupe
 */

import com.esplay.ElasticSearchStarter;
import com.esplay.dao.QueryDAO;
import com.esplay.entity.Document;
import com.esplay.entity.Movie;
import com.esplay.prop.ConfigProps;

import lombok.extern.slf4j.Slf4j;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ElasticSearchStarter.class)
@ActiveProfiles("test")
@Slf4j
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ESClientTest {

    
	private RestHighLevelClient client = new RestHighLevelClient(
            RestClient.builder(
                    new HttpHost("localhost", 9200, "http")) );


    @Autowired
    private ConfigProps props;

    @Autowired
    private QueryDAO dao;

    private Document doc = new Document(null, "Hüseyin", "Akdoğan", "Hello!");

    @Test
    public void testA() throws IOException {
        assertNotNull(dao.createIndex(doc));
        flush();
    }

    @Test
    public void testD() throws IOException {
        List<Document> documentList = dao.wildcardQuery("akd");
        documentList.forEach(doc -> dao.deleteDocument(doc.getId()));
        flush();
        assertTrue(dao.matchAllQuery().isEmpty());
    }

    @Test
    public void testB(){
        assertFalse(dao.matchAllQuery().isEmpty());
    }

    @Test
    public void testC(){
        assertFalse(dao.wildcardQuery("akd").isEmpty());
    }

    private void flush() throws IOException {
        String endPoint = String.join("/", props.getIndex().getName(), "_flush");
        client.getLowLevelClient().performRequest("POST", endPoint);
    }
    
    
    @Test
    public void testStoreMovie() throws IOException {
    	

        List<String> genres = new ArrayList();
        genres.add("test genre1");
        genres.add("test genre2");
        

        Movie movie = new Movie(null,1,"Test movie title", 1,genres ,2000 );
    	assertNotNull(dao.createIndex(movie, "movies", "movie"));
        flush();
    }
    
}
