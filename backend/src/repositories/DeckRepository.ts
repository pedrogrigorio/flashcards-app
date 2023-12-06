import { Deck } from '@prisma/client'
import { prisma } from '../lib/prisma'

class DeckRepository {
  async createDeck(userId: number) {
    const deck = await prisma.deck.create({
      data: {
        title: 'Novo deck',
        imgSrc: '',
        newCardsCount: 0,
        learnCardsCount: 0,
        reviewCardsCount: 0,
        User: {
          connect: { id: userId },
        },
      },
    })

    return deck
  }

  async upadateDeck(
    deckId: number,
    updateDeck: Deck,
    imgSrc: string | undefined,
  ) {
    const deck = await prisma.deck.update({
      where: {
        id: deckId,
      },
      data: {
        title: updateDeck.title,
        newCardsCount: updateDeck.newCardsCount,
        learnCardsCount: updateDeck.learnCardsCount,
        reviewCardsCount: updateDeck.reviewCardsCount,
        imgSrc,
      },
    })
    return deck
  }

  async deleteDeck(deckId: number) {
    const deck = await prisma.deck.delete({
      where: {
        id: deckId,
      },
    })

    return deck.id
  }

  async getAllDeck(userId: number) {
    const deck = await prisma.deck.findMany({
      where: {
        User: {
          id: userId,
        },
      },
    })
    return deck
  }

  async getDeckById(deckId: number) {
    const deck = await prisma.deck.findUnique({
      where: {
        id: deckId,
      },
    })
    return deck
  }
}

export default new DeckRepository()
