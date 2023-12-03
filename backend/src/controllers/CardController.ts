import { Request, Response } from 'express'
import CardService from '../services/CardService'

class CardController {
  async createCard(req: Request, res: Response) {
    try {
      const newCard = await CardService.createCard(
        parseInt(req.body.deckId),
        req.body.frontText,
        req.body.backText,
      )

      return res.status(201).json(newCard)
    } catch (error) {
      console.error('Error to create newCard:', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async updateCard(req: Request, res: Response) {
    try {
      const updateCard = await CardService.updateCard(
        parseInt(req.body.deckId),
        parseInt(req.body.cardId),
        req.body.frontText,
        req.body.backText,
      )
      return res.status(201).json(updateCard)
    } catch (error) {
      console.error('Error to update card: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async deleteCard(req: Request, res: Response) {
    try {
      const deleteCard = await CardService.deleteCard(
        parseInt(req.body.deckId),
        parseInt(req.body.cardId),
      )
      return res.status(201).json(deleteCard)
    } catch (error) {
      console.error('Error to delete card: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async getAllCards(req: Request, res: Response) {
    try {
      const allCards = await CardService.getAllCards(parseInt(req.body.deckId))
      return res.status(201).json(allCards)
    } catch (error) {
      console.error('Error to delete card: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async getCardById(req: Request, res: Response) {
    try {
      const cardById = await CardService.getCardById(
        parseInt(req.body.deckId),
        parseInt(req.body.cardId),
      )
      return res.status(201).json(cardById)
    } catch (error) {
      console.error('Error to get card by ID: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async getCardsForToday(req: Request, res: Response) {
    try {
      const cardsForToday = await CardService.getCardsForToday(
        parseInt(req.body.deckId),
      )
      return res.status(201).json(cardsForToday)
    } catch (error) {
      console.error('Error to get card for today: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async updateCardsReviewed(req: Request, res: Response) {
    try {
      const cardsUpdateReviewed = await CardService.updateCardsReviewed(
        parseInt(req.body.deckId),
        parseInt(req.body.cardId),
        parseInt(req.body.stampLevel),
        parseInt(req.body.startingEasy),
        new Date(req.body.lastSuccessfulReview),
      )
      return res.status(201).json(cardsUpdateReviewed)
    } catch (error) {
      console.error('Error to get update cards Reviewed: ', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }
}

export default new CardController()
