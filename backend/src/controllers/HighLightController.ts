import { Request, Response } from 'express'
import HighlightsService from '../services/HighlightsService'

class HighLightController {
  async createHighlight(req: Request, res: Response) {
    try {
      const newHighlight = await HighlightsService.createHighlight(
        parseInt(req.body.CardId),
        req.body.color,
        parseInt(req.body.startPosition),
        parseInt(req.body.endPosition),
      )

      return res.status(201).json(newHighlight)
    } catch (error) {
      console.error('Error to create HighLight:', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async updateHighlight(req: Request, res: Response) {
    try {
      const update = await HighlightsService.updateHighlight(
        parseInt(req.body.highlightId),
        req.body.color,
        parseInt(req.body.startPosition),
        parseInt(req.body.endPosition),
      )

      return res.status(201).json(update)
    } catch (error) {
      console.error('Error to update HighLight:', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }

  async getAllHighlights(req: Request, res: Response) {
    try {
      const allHighlights = await HighlightsService.getAllHighlights(
        parseInt(req.body.cardId),
      )

      return res.status(201).json(allHighlights)
    } catch (error) {
      console.error('Error to get AllHighLight:', error)
      return res.status(500).json({ error: 'Internal Server Error' })
    }
  }
}

export default new HighLightController()
