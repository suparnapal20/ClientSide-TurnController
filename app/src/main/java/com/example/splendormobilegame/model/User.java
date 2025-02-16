package com.example.splendormobilegame.model;

import com.github.splendor_mobile_game.game.enums.TokenType;

import java.sql.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class User {

    private final String name;

    private UUID uuid;

    private final HashMap<TokenType, Integer> tokens;

    private ArrayList<Card> cards;

    private ArrayList<Noble> nobles;


    public User(UUID uuid, String name) {
        this.uuid = uuid;
        this.name = name;
        this.tokens = new HashMap<>();
        this.cards = new ArrayList<>();
        this.nobles = new ArrayList<>();

        tokens.put(TokenType.DIAMOND, 0);
        tokens.put(TokenType.EMERALD, 0);
        tokens.put(TokenType.GOLD_JOKER, 0);
        tokens.put(TokenType.ONYX, 0);
        tokens.put(TokenType.RUBY, 0);
        tokens.put(TokenType.SAPPHIRE, 0);
    }


    public String getName() {
        return name;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUuid(UUID uuid) {
        this.uuid = uuid;
    }

    public void addTokens(TokenType tokenType, int amount) {
        if (tokens.get(tokenType) == null) return;
        int val = tokens.get(tokenType);
        tokens.replace(tokenType, val + amount);
    }

    public void removeTokens(TokenType tokenType, int amount) {
        if (tokens.get(tokenType) == null) return;
        int val = tokens.get(tokenType);
        if (amount > val) return; // Not enough tokens in user's inventory
        tokens.replace(tokenType, val - amount);
    }


    public void addCard(Card card) {
        if (cards.contains(card)) return;
        cards.add(card);
    }

    public void removeCard(Card card) {
        if (!cards.contains(card)) return;
        cards.remove(card);
    }


    public void addNoble(Noble noble) {
        if (nobles.contains(noble)) return;
        nobles.add(noble);
    }

    public void removeNoble(Noble noble) {
        if (!nobles.contains(noble)) return;
        nobles.remove(noble);
    }


    public HashMap<TokenType, Integer> getTokens() {
        return tokens;
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public ArrayList<Noble> getNobles() {
        return nobles;
    }

    public void setNobles(ArrayList<Noble> nobles) {
        this.nobles = nobles;
    }


    public int getAllTokensCount() {
        int suma = 0;
        for (int val : tokens.values())
            suma += val;

        return suma;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return uuid.equals(user.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, uuid);
    }

}
