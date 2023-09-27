package com.example.kafkastutdy.config.kafka;

import com.example.kafkastutdy.repository.MemberRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Slf4j
public class KafkaConsumer {

    MemberRepository memberRepository;

    @Autowired
    public KafkaConsumer(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @KafkaListener(topics = "my-topic-member")
    public void updateGender(String kafkaMessage) {
        log.info("Kafka Message: ->" + kafkaMessage);

        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(kafkaMessage, new TypeReference<>() {
            });

            Integer memberId = (Integer) map.get("idx");
            String newGender = (String) map.get("gender");

            memberRepository.findById(Long.valueOf(memberId)).ifPresent(member -> {
                member.setGender(newGender);
                memberRepository.save(member);
            });

        } catch (JsonProcessingException exception) {
            log.error("Error deserializing Kafka message: " + exception.getMessage());
        } catch (Exception e) {
            log.error("Error updating member gender: " + e.getMessage());
        }
    }

}
