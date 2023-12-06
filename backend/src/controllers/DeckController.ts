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
    const file = req.file
    const updateDeckDTO = JSON.parse(req.body.updateDeckDTO)
    console.log('file' + file)
    const deckData = {
      id: updateDeckDTO.deckId,
      title: updateDeckDTO.title || 'Novo Deck',
      imgSrc: req.body.imgSrc || '',
      newCardsCount: updateDeckDTO.newCardsCount || 0,
      learnCardsCount: updateDeckDTO.learnCardsCount || 0,
      reviewCardsCount: updateDeckDTO.reviewCardsCount || 0,
      userId: parseInt(req.userId),
    }

    try {
      const deck = await DeckService.updateDeck(
        parseInt(updateDeckDTO.deckId),
        deckData,
        file,
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
