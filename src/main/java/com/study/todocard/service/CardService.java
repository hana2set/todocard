package com.study.todocard.service;

import com.study.todocard.dto.CardRequestDto;
import com.study.todocard.dto.CardResponseDto;
import com.study.todocard.entity.Card;
import com.study.todocard.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;

    public CardResponseDto createCard(CardRequestDto requestDto) {
        Card saveCard = cardRepository.save(new Card(requestDto));
        return new CardResponseDto(saveCard);
    }

    public List<CardResponseDto> getCards() {
        // DB 조회
        return cardRepository.findAllByOrderByModifiedAtDesc().stream().map(CardResponseDto::new).toList();
    }

    public CardResponseDto getCard(Long id) {
        Card card = cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 카드는 존재하지 않습니다."));
        return new CardResponseDto(card);
    }

    @Transactional
    public Long updateCard(Long id, CardRequestDto requestDto) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 card는 존재하지 않습니다."));

        card.update(requestDto);
        return id;
    }

    public Long deleteCard(Long id) {
        // DB에 존재하는지 확인
        Card card = cardRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("선택한 카드는 존재하지 않습니다."));

        cardRepository.delete(card);
        return id;
    }


}