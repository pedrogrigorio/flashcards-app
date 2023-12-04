import { Request, Response } from 'express'
import DeckService from '../services/DeckService'
import { Deck } from '@prisma/client'

class DeckController {
  async createDeck(req: Request, res: Response) {
    try {
      console.log(req.body.userId)
      const deck = await DeckService.createDeck(parseInt(req.userId))

      return res.json(deck)
    } catch (error) {
      return res.status(400).json(error)
    }
  }

  async updateDeck(req: Request, res: Response) {
    const deckData: Deck = {
      id: req.body.deckId,
      title: req.body.title || 'Novo Deck',
      imgSrc: req.body.imgSrc || '',
      newCardsCount: req.body.newCardsCount || 0,
      learnCardsCount: req.body.learnCardsCount || 0,
      reviewCardsCount: req.body.reviewCardsCount || 0,
      userId: parseInt(req.userId),
    }

    try {
      const deck = await DeckService.updateDeck(
        parseInt(req.body.deckId),
        deckData,
      )

      return res.json(deck)
    } catch (error) {
      return res.status(400).json(error)
    }
  }

  async deleteDeck(req: Request, res: Response) {
    try {
      const deck = await DeckService.deleteDeck(parseInt(req.params.deckId))
      return res.json(deck)
    } catch (error) {
      return res.status(400).json(error)
    }
  }

  async getAllDeck(req: Request, res: Response) {
    try {
      const userId = parseInt(req.userId)
      const deck = await DeckService.getAllDeck(userId)
      return res.json(deck)
    } catch (error) {
      return res.status(400).json(error)
    }
  }

  async getDeckById(req: Request, res: Response) {
    try {
      const deck = await DeckService.getDeckById(parseInt(req.body.deckId))
      return res.json(deck)
    } catch (error) {
      return res.status(400).json(error)
    }
  }
}

export default new DeckController()
