package com.ming.elasticsearch.dao;

import com.ming.elasticsearch.model.User;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface UserDAO extends ElasticsearchRepository<User, Long> {
}