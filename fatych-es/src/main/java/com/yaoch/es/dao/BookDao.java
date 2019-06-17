package com.yaoch.es.dao;

import com.yaoch.es.entity.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends ElasticsearchRepository<Book,String> {

}
