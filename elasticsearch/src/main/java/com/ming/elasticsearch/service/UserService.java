package com.ming.elasticsearch.service;

import com.ming.elasticsearch.dao.UserDAO;
import com.ming.elasticsearch.model.User;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.elasticsearch.index.query.functionscore.FunctionScoreQueryBuilder;
import org.elasticsearch.index.query.functionscore.ScoreFunctionBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserDAO userDao;

    public boolean insert(User user) {
        boolean falg = false;
        try {
            userDao.save(user);
            falg = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return falg;
    }

    public List<User> search(String searchContent) {
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
        System.out.println("查询的语句:" + builder);
        Iterable<User> searchResult = userDao.search(builder);
        Iterator<User> iterator = searchResult.iterator();
        List<User> list = new ArrayList<User>();
        while (iterator.hasNext()) {
            list.add(iterator.next());
        }
        return list;
    }


    public List<User> searchUser(Integer pageNumber, Integer pageSize, String searchContent) {
        // 分页参数
//        Pageable pageable = new PageRequest(pageNumber, pageSize);
//        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(searchContent);
//        SearchQuery searchQuery = new NativeSearchQueryBuilder().withPageable(pageable).withQuery(builder).build();
//        System.out.println("查询的语句:" + searchQuery.getQuery().toString());
//        Page<User> searchPageResults = userDao.search(searchQuery);
//        return searchPageResults.getContent();
        return null;
    }


    public List<User> searchUserByWeight(String searchContent) {
        // 根据权重进行查询
//        FunctionScoreQueryBuilder functionScoreQueryBuilder = QueryBuilders.functionScoreQuery()
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("name", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(10))
//                .add(QueryBuilders.boolQuery().should(QueryBuilders.matchQuery("description", searchContent)),
//                        ScoreFunctionBuilders.weightFactorFunction(100)).setMinScore(2);
//        System.out.println("查询的语句:" + functionScoreQueryBuilder.toString());
//        Iterable<User> searchResult = userDao.search(functionScoreQueryBuilder);
//        Iterator<User> iterator = searchResult.iterator();
//        List<User> list = new ArrayList<User>();
//        while (iterator.hasNext()) {
//            list.add(iterator.next());
//        }
//        return list;
        return null;
    }
}
