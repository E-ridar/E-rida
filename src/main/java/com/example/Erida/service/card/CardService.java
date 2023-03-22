package com.example.Erida.service.card;

import com.example.Erida.data.models.Card;
import com.example.Erida.dtos.request.AddCardRequest;
import com.example.Erida.dtos.request.EditCardRequest;
import com.example.Erida.dtos.request.VerifyCardRequest;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.PathVariable;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;


public interface CardService {

    Card addCard(AddCardRequest addCardRequest) throws ParseException, IOException;
    String deleteCard(Long cardId);
    Object verifyCard(VerifyCardRequest verifyCardRequest) throws IOException;

    void editCard(Long cardId, EditCardRequest editCardRequest);

    Card viewCardById(Long cardId);
    void deleteUserCards(Long userid);
    List<Card> viewCard(Long userId);
}
