package com.demo.hazeltest.server1.controller;

import com.demo.hazeltest.server1.data.FileInfo;
import com.hazelcast.cluster.ClusterState;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.map.IMap;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@org.springframework.web.bind.annotation.RestController
@Slf4j
public class RestController {
    @Autowired
    HazelcastInstance hazelcastInstance;

    @PostMapping("/setkey")
    public String setKey(@RequestBody FileInfo fileInfo){
        //HazelCast Key Set
        IMap<String, String> map = hazelcastInstance.getMap("onCMS");
        map.put(fileInfo.getFileName(), fileInfo.getFilePath());
        log.info("Set Hazelcast = key : "+ fileInfo.getFileName() + " / value " + map.get(fileInfo.getFileName()));

        return "ok";
    }

    @PostMapping("/getvalue")
    public String getValue(@RequestBody FileInfo fileInfo){
        //HazelCast Key Get
        IMap<String, String> map = hazelcastInstance.getMap("onCMS");
        //map.put(fileInfo.getFileName(), fileInfo.getFilePath());
        log.info("Get Hazelcast = key : "+ fileInfo.getFileName() + " / value " + map.get(fileInfo.getFileName()));



        return map.get(fileInfo.getFileName());
    }

    @PostMapping("/cluster_state")
    public String getClusterState(){
        ClusterState clusterState = hazelcastInstance.getCluster().getClusterState();
        return clusterState.toString();
    }

}
