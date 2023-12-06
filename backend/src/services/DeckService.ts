import { Deck } from '@prisma/client'
import DeckRepository from '../repositories/DeckRepository'
import fs from 'fs'
import path from 'path'

class DeckService {
  async createDeck(userId: number) {
    return await DeckRepository.createDeck(userId)
  }

  async updateDeck(
    deckId: number,
    upadateDeck: Deck,
    file: Express.Multer.File | undefined,
  ) {
    const deck = await DeckRepository.getDeckById(deckId)

    let imgSrc = deck?.imgSrc

    if (file) {
      if (imgSrc !== '') {
        const previousImagePath = path.join(
          __dirname,
          `../../uploads/${imgSrc}`,
        )

        fs.unlinkSync(previousImagePath)
      }

      imgSrc = file.filename
    }
    console.log('UPDATE DECK: ' + upadateDeck)

    const deckUpdated = await DeckRepository.upadateDeck(
      deckId,
      upadateDeck,
      imgSrc,
    )

    return deckUpdated
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
