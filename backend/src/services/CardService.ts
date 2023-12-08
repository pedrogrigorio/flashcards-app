import CardRepository from '../repositories/CardsRepository'
import SpacedRepetitionSystem from '../utils/SpacedRepetitionSystem'

class CardService {
  async createCard(deckId: number, frontText: string, backText: string) {
    const card = await CardRepository.createCard(deckId, frontText, backText)
    return card
  }

  async updateCard(
    deckId: number,
    cardId: number,
    frontText: string,
    backText: string,
  ) {
    const card = await CardRepository.updateCard(
      deckId,
      cardId,
      frontText,
      backText,
    )
    return card
  }

  async deleteCard(deckId: number, cardId: number) {
    const card = await CardRepository.deleteCard(deckId, cardId)
    return card
  }

  async getAllCards(deckId: number) {
    const cards = await CardRepository.getAllCards(deckId)
    return cards
  }

  async getCardById(deckId: number, cardId: number) {
    const card = await CardRepository.getCardById(deckId, cardId)
    return card
  }

  async getCardsForToday(deckId: number) {
    const dateToday = new Date()
    const dateOnly = new Date(
      dateToday.getFullYear(),
      dateToday.getMonth(),
      dateToday.getDate(),
    )

    const cards = await CardRepository.getCardsForToday(deckId, dateOnly)
    return cards
  }

  async updateCardsReviewed(
    deckId: number,
    CardId: number,
    stampLevel: number,
    startingEasy: number,
    lastSuccessfulReview: Date,
  ) {
    const newReview = SpacedRepetitionSystem.nextReview(
      lastSuccessfulReview,
      stampLevel,
      startingEasy,
    )
    const cards = await CardRepository.updateCardsReviewed(
      deckId,
      CardId,
      stampLevel,
      newReview.newStartEasy,
      newReview.newDate,
      newReview.newSuccessfulReview,
    )

    return cards
  }
}

export default new CardService()
