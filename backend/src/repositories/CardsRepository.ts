import { prisma } from '../lib/prisma'

class CardRepository {
  async createCard(deckId: number, frontText: string, backText: string) {
    console.log(deckId)
    const card = await prisma.card.create({
      data: {
        frontText,
        backText,
        Deck: {
          connect: { id: deckId },
        },
      },
    })

    return card
  }

  async updateCard(
    deckId: number,
    cardId: number,
    frontText: string,
    backText: string,
  ) {
    const card = await prisma.card.update({
      where: {
        id: cardId,
      },
      data: {
        frontText,
        backText,
        Deck: {
          connect: { id: deckId },
        },
      },
    })

    return card
  }

  async deleteCard(deckId: number, cardId: number) {
    const card = await prisma.card.delete({
      where: {
        id: cardId,
        Deck: {
          id: deckId,
        },
      },
    })

    return card
  }

  async getAllCards(deckId: number) {
    const cards = await prisma.card.findMany({
      where: {
        Deck: {
          id: deckId,
        },
      },
    })

    return cards
  }

  async getCardById(deckId: number, cardId: number) {
    const card = await prisma.card.findUnique({
      where: {
        id: cardId,
        Deck: {
          id: deckId,
        },
      },
    })

    return card
  }

  async getCardsForToday(deckId: number, date: Date) {
    const cards = await prisma.card.findMany({
      where: {
        AND: [
          {
            OR: [{ nextReview: null }, { nextReview: { lte: date } }],
            Deck: {
              id: deckId,
            },
          },
          {
            OR: [
              { stampLevel: null },
              { stampLevel: undefined }, // Adicione esta condição se null e undefined devem ser tratados da mesma forma
            ],
          },
        ],
      },
    })

    return cards
  }

  async updateCardsReviewed(
    DeckId: number,
    CardId: number,
    StampLevel: number,
    StartEasy: number,
    NextReview: Date | null,
    newLastSucessfulReview: Date | null,
  ) {
    const card = await prisma.card.update({
      where: {
        id: CardId,
        Deck: {
          id: DeckId,
        },
      },
      data: {
        stampLevel: StampLevel,
        startingEasy: StartEasy,
        nextReview: NextReview,
        lastSuccessfulReview: newLastSucessfulReview,
      },
    })

    return card
  }
}

export default new CardRepository()
