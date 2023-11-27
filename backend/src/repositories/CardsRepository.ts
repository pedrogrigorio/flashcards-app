import { Card } from '@prisma/client'
import { prisma } from '../lib/prisma'
import deck from '../routes/deck'

class CardRepository {
  async createCard(Card: Card) {
    const card = await prisma.card.create({
      data: Card,
    })

    return card
  }

  async updateCard(Card: Card) {
    const card = await prisma.card.update({
      where: {
        id: Card.id,
      },
      data: Card,
    })

    return card
  }

  async deleteCard(cardId: number) {
    const card = await prisma.card.delete({
      where: {
        id: cardId,
      },
    })

    return card
  }

  async getAllCards(deckId: number) {
    const cards = await prisma.deck.findMany({
      where: {
        id: deckId,
      },
      include: {
        cards: true,
      },
    })

    return cards
  }

  async getCardById(cardId: number) {
    const card = await prisma.card.findUnique({
      where: {
        id: cardId,
      },
    })

    return card
  }

  async getCardsForToday(date: Date) {
    const cards = await prisma.card.findMany({
      where: {
        nextReview: {
          lte: date,
        },
      },
    })

    return cards
  }

  async updateCardsReviewed(cardId: number, date: Date) {
    const card = await prisma.card.update({
      where: {
        id: cardId,
      },
      data: {
        reviewedAt: date,
      },
    })

    return card
  }
}
