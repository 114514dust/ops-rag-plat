package com.opsrag.backend.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.opsrag.backend.common.constent.AiConstent;
import com.opsrag.backend.mapper.KbFaqMapper;
import com.opsrag.backend.pojo.Entity.KbFaq;
import com.opsrag.backend.service.RagSyncService;
import dev.langchain4j.data.document.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RagSyncServiceImpl implements RagSyncService {

    @Autowired
    KbFaqMapper faqMapper;
    public List<Document> syncDbToVector() {

        List<KbFaq> faqList = faqMapper.selectList(new LambdaQueryWrapper<KbFaq>()
                .eq(KbFaq::getStatus, 1)
                .eq(KbFaq::getIsDeleted, 0));
        List<Document>documents=faqList.stream().map(
                faq->{
                    String data = AiConstent.VALUE_QUESTION+faq.getQuestion()+"\n"
                            +AiConstent.VALUE_SIMILAR_QUESTION+faq.getSimilarQuestions()+"\n"
                            +AiConstent.VALUE_QUESTION_KEY+faq.getKeywords()+"\n"
                            +AiConstent.VALUE_QUESTION_TYPE+faq.getFaqType();
                    return Document.from(data);
                }
        ).toList();
        return  documents;
    }
}
