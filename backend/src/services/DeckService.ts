import { Deck } from '@prisma/client'
import DeckRepository from '../repositories/DeckRepository'

class DeckService {
  async createDeck(userId: number) {
    return await DeckRepository.createDeck(userId)
  }

  async updateDeck(deckId: number, upadateDeck: Deck) {
    return await DeckRepository.upadateDeck(deckId, upadateDeck)
  }

  async deleteDeck(deckId: number) {
    return await DeckRepository.deleteDeck(deckId)
  }

  async getAllDeck(userId: number) {
    return await DeckRepository.getAllDeck(userId)
  }

  async getDeckById(deckId: number) {
    return await DeckRepository.getDeckById(deckId)
  }
}

export default new DeckService()
