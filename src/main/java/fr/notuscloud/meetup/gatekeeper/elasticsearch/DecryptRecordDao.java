package fr.notuscloud.meetup.gatekeeper.elasticsearch;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.elasticsearch.ElasticsearchException;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.Map;
import java.util.UUID;

@Repository
public class DecryptRecordDao {

    private static final Logger LOG = LoggerFactory.getLogger(ElasticsearchConfiguration.class);

    private final String INDEX = "meetup";
    private final String TYPE = "decryptrecord";
    private IndexResponse response;

    private RestHighLevelClient restHighLevelClient;
    private ObjectMapper objectMapper;

    // Constructor
    public DecryptRecordDao(RestHighLevelClient restHighLevelClient, ObjectMapper objectMapper) {
        this.restHighLevelClient = restHighLevelClient;
        this.objectMapper = objectMapper;
    }

    public String indexDecryptRecord(DecryptRecord decryptRecord){

        // Generate a UUID
        decryptRecord.setId(UUID.randomUUID().toString());
        // Create a JSON objectMap from our class
        Map dataMap = objectMapper.convertValue(decryptRecord, Map.class);
        // Create an IndexRequest that will be submitted to Elasticsearch
        IndexRequest indexRequest = new IndexRequest(INDEX, TYPE, decryptRecord.getId()).source(dataMap);
//        RequestOptions.Builder requestOptions = RequestOptions.DEFAULT.toBuilder();

        try {

            response = restHighLevelClient.index(indexRequest, RequestOptions.DEFAULT);
            LOG.info("ECHO:" + response.toString());

        }catch(ElasticsearchException e) {
            LOG.error(e.getDetailedMessage());
        } catch (IOException ex){
            LOG.error(ex.getLocalizedMessage());
        }

        return "done";
    }
}
