import { Card } from '@prisma/client'
import CardRepository from '../repositories/CardsRepository'

class CardService {
  async createCard(Card: Card) {
    const card = await CardRepository.createCard(Card)
    return card
  }

  async updateCard(Card: Card) {
    const card = await CardRepository.updateCard(Card)
    return card
  }

  async deleteCard(cardId: number) {
    const card = await CardRepository.deleteCard(cardId)
    return card
  }

  async getAllCards(deckId: number) {
    const cards = await CardRepository.getAllCards(deckId)
    return cards
  }

  async getCardById(cardId: number) {
    const card = await CardRepository.getCardById(cardId)
    return card
  }

  async getCardsForToday(date: Date) {
    const cards = await CardRepository.getCardsForToday(date)
    return cards
  }

  async updateCardsReviewed() {}
}

export default new CardService()
