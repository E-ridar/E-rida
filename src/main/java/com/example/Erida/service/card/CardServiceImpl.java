package com.example.Erida.service.card;

import com.example.Erida.data.models.Card;
import com.example.Erida.dtos.request.AddCardRequest;
import com.example.Erida.dtos.request.EditCardRequest;
import com.example.Erida.dtos.request.VerifyCardRequest;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class CardServiceImpl implements CardService{
    @Override
    public Card addCard(AddCardRequest addCardRequest) throws ParseException, IOException {
        return null;
    }

    @Override
    public String deleteCard(Long cardId) {
        return null;
    }

    @Override
    public Object verifyCard(VerifyCardRequest verifyCardRequest) throws IOException {
        return null;
    }

    @Override
    public void editCard(Long cardId, EditCardRequest editCardRequest) {

    }

    @Override
    public Card viewCardById(Long cardId) {
        return null;
    }

    @Override
    public void deleteUserCards(Long userid) {

    }

    @Override
    public List<Card> viewCard(Long userId) {
        return null;
    }
}
