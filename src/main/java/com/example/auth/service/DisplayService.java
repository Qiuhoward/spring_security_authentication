package com.example.auth.service;


import com.example.auth.dto.ChoicePageResponse;
import com.example.auth.dto.MkdirRequest;
import com.example.auth.entity.group.GroupHandoutDao;
import com.example.auth.entity.personalHandout.PersonalHandoutDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Collections;


@Service
@Slf4j
public class DisplayService {

    private final PersonalHandoutDao personalHandoutDao;
    private final GroupHandoutDao groupHandoutDao;

    public DisplayService(PersonalHandoutDao personalHandoutDao, GroupHandoutDao groupHandoutDao) {
        this.personalHandoutDao = personalHandoutDao;
        this.groupHandoutDao = groupHandoutDao;
    }

    public ChoicePageResponse findAll(int pageId) {
        log.info("選擇哪展示哪一頁 (1)個人(2)團體(3)素材(4)垃圾區 page-id:{}",pageId);
        ChoicePageResponse response = null;
        switch (pageId) {

            case 1 -> response = ChoicePageResponse.builder()
                    .list(Collections.singletonList(personalHandoutDao.findAll())).build();

            case 2 -> response = ChoicePageResponse.builder()
                    .list(Collections.singletonList(groupHandoutDao.findAll())).build();
        }

        return response;
    }

    //新增資料夾
    public String mkdir(MkdirRequest request) {
        int pageId=request.getPageId();



        return "修改成功";
    }


}
